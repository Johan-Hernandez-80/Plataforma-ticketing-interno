package com.jsj.api.service;


import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceTest.class);
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private NotificacionDAO notificacionDAO;
    @Mock
    private RolDAO rolDAO;
    @Mock
    private UsuarioDAO dao;

    @InjectMocks
    UsuarioService service;

    // ============================================================
    // SECCIÓN 1: updateUsuario()
    // ============================================================

    // CASO 1: Usuario se actualiza correctamente
    @Test
    public void updateUsuario_DeberiaRetornarUsuarioDTOConNuevosDatos_CuandoTodoEsValido() throws Exception{
        //Arrange
        Long idUsuarioValido = 1L;
        UsuarioDTO usuarioDTOValido = new UsuarioDTO();
        usuarioDTOValido.setEmailPersonal("email@email.com");
        usuarioDTOValido.setContrasena("123456");
        when(dao.updateUsuario(any(Long.class), any(UsuarioDTO.class))).thenReturn(usuarioDTOValido);

        //Act
        UsuarioDTO resultado = service.updateUsuario(idUsuarioValido, usuarioDTOValido);

        //Assert
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals("email@email.com", resultado.getEmailPersonal(), "El email personal del usuario debe coincidir");
        assertEquals("123456", resultado.getContrasena(), "La contrasena del usuario debe coincidir");
        verify(dao, times(1)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }

    // CASO 2: Usuario inexistente
    @Test
    public void updateUsuario_DeberiaLanzarUsuarioInexistenteException_CuandoUsuarioNoExiste() throws Exception {
        // ---------- ARRANGE ----------
        Long idInexistente = 99L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmailPersonal("noexiste@email.com");
        usuarioDTO.setContrasena("123456");

        when(dao.updateUsuario(any(Long.class), any(UsuarioDTO.class)))
                .thenThrow(new UsuarioInexistenteException("Usuario no encontrado"));

        // ---------- ACT & ASSERT ----------
        UsuarioInexistenteException exception = assertThrows(
                UsuarioInexistenteException.class,
                () -> service.updateUsuario(idInexistente, usuarioDTO),
                "Debe lanzar UsuarioInexistenteException si el usuario no existe"
        );

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(dao, times(1)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }

    // CASO 3: Sin los permisos necesarios
    @Test
    public void updateUsuario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoNoTienePermisos() throws Exception {
        Long id = 1L;
        UsuarioDTO dto = new UsuarioDTO();
        when(dao.updateUsuario(any(), any()))
                .thenThrow(new InsufficientSavingPermissionsException("No tiene permisos"));

        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                () -> service.updateUsuario(id, dto)
        );

        assertEquals("No tiene permisos", ex.getMessage());
        verify(dao, times(1)).updateUsuario(any(), any());
    }

    // CASO 4: Rol inexistente
    @Test
    public void updateUsuario_DeberiaLanzarRolInexistenteException_CuandoRolNoExiste() throws Exception {
        Long id = 1L;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setRolId(999L);
        when(rolDAO.existsById(any())).thenReturn(false);

        RolInexistenteException ex = assertThrows(
                RolInexistenteException.class,
                () -> service.updateUsuario(id, dto)
        );

        verify(rolDAO, times(1)).existsById(any());
    }

    // CASO 5: ID inválida
    @Test
    public void updateUsuario_DeberiaLanzarIdInvalidaException_CuandoElIdEsInvalido() throws Exception {
        Long id = -2L;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(id);

        IdInvalidaException ex = assertThrows(
                IdInvalidaException.class,
                () -> service.updateUsuario(id, dto)
        );

        verify(dao, times(0)).updateUsuario(any(), any());
    }

    // CASO 6: Email personal inválido
    @Test
    public void updateUsuario_DeberiaLanzarEmailInvalidoException_CuandoEmailPersonalEsInvalido() throws Exception {
        Long id = 1L;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmailPersonal("email-existente");
        when(dao.existsByEmailPersonal(any())).thenReturn(true);

        EmailInvalidoException ex = assertThrows(
                EmailInvalidoException.class,
                () -> service.updateUsuario(id, dto)
        );

        verify(dao, times(1)).existsByEmailPersonal(any());
    }

    // CASO 7: Email corporativo inválido
    @Test
    public void updateUsuario_DeberiaLanzarEmailInvalidoException_CuandoEmailCorporativoEsInvalido() throws Exception {
        Long id = 1L;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmailPersonal("email-existente");
        when(dao.existsByEmailPersonal(any())).thenReturn(true);

        EmailInvalidoException ex = assertThrows(
                EmailInvalidoException.class,
                () -> service.updateUsuario(id, dto)
        );

        verify(dao, times(1)).existsByEmailPersonal(any());
    }

    // CASO 8: Campo inmutable modificado
    @Test
    public void updateUsuario_DeberiaLanzarImmutableFieldException_CuandoCampoInmutableSeModifica() throws Exception {
        Long id = 1L;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(1L);
        dto.setEmailCorporativo("nuevo@email.com");

        doThrow(new ImmutableFieldException("Campo inmutable modificado"))
                .when(dao).updateUsuario(any(), any());

        ImmutableFieldException ex = assertThrows(
                ImmutableFieldException.class,
                () -> service.updateUsuario(id, dto)
        );

        assertEquals("Campo inmutable modificado", ex.getMessage());
        verify(dao, times(1)).updateUsuario(any(), any());
    }
}
