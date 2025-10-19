package com.jsj.api.service;


import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
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

    Long idValido;
    Long idInvalido;
    UsuarioDTO usuario;

    @BeforeEach
    void setUp() {
        idValido = 1L;
        idInvalido = -99L;
        usuario = new UsuarioDTO();
    }

    // ============================================================
    // SECCIÓN 1: updateUsuario()
    // ============================================================

    // CASO 1: Usuario inexistente
    @Test
    public void updateUsuario_DeberiaLanzarUsuarioInexistenteException_CuandoUsuarioNoExiste() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailPersonal("emailNoExiste@email.com");
        usuario.setContrasena("123456");

        when(dao.updateUsuario(any(Long.class), any(UsuarioDTO.class)))
                .thenThrow(new UsuarioInexistenteException("Usuario no encontrado"));

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        UsuarioInexistenteException exception = assertThrows(
                UsuarioInexistenteException.class,
                accion,
                "Debe lanzar UsuarioInexistenteException si el usuario no existe"
        );

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(dao, times(1)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }

    // CASO 2: Sin los permisos necesarios
    @Test
    public void updateUsuario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoNoTienePermisos() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailPersonal("emailNoExiste@email.com");
        usuario.setContrasena("123456");

        when(dao.updateUsuario(any(Long.class), any(UsuarioDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("No tiene permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si no tiene permisos"
        );

        assertEquals("No tiene permisos", ex.getMessage());
        verify(dao, times(1)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }

    // CASO 3: Rol inexistente
    @Test
    public void updateUsuario_DeberiaLanzarRolInexistenteException_CuandoRolNoExiste() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailPersonal("emailExiste@email.com");
        usuario.setContrasena("123456");
        usuario.setRolId(99L);

        when(rolDAO.existsById(any(Long.class))).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        RolInexistenteException ex = assertThrows(
                RolInexistenteException.class,
                accion,
                "Debe lanzar RolInexistenteException si el rol no existe"
        );

        verify(rolDAO, times(1)).existsById(any(Long.class));
    }

    // CASO 4: ID inválida
    @Test
    public void updateUsuario_DeberiaLanzarIdInvalidaException_CuandoElIdEsInvalido() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idInvalido);
        usuario.setEmailPersonal("emailExiste@email.com");
        usuario.setContrasena("123456");
        usuario.setRolId(99L);

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        IdInvalidaException ex = assertThrows(
                IdInvalidaException.class,
                accion,
                "Debe lanzar IdInvalidaException si el ID es inválido"
        );

        verify(dao, times(0)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }

    // CASO 5: Email personal inválido
    @Test
    public void updateUsuario_DeberiaLanzarEmailInvalidoException_CuandoEmailPersonalEsInvalido() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailPersonal("emailExiste@email.com");
        usuario.setContrasena("123456");
        usuario.setRolId(99L);

        when(dao.existsByEmailPersonal(any(String.class))).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        EmailInvalidoException ex = assertThrows(
                EmailInvalidoException.class,
                accion,
                "Debe lanzar EmailInvalidoException si el email personal es inválido"
        );

        verify(dao, times(1)).existsByEmailPersonal(any(String.class));
    }

    @Test
    public void updateUsuario_DeberiaLanzarEmailInvalidoException_CuandoEmailCorporativoEsInvalido() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailCorporativo("emailCorporativo@empresa.com");
        usuario.setContrasena("123456");
        usuario.setRolId(99L);

        when(dao.existsByEmailCorporativo(any(String.class))).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        EmailInvalidoException ex = assertThrows(
                EmailInvalidoException.class,
                accion,
                "Debe lanzar EmailInvalidoException si el email corporativo es inválido"
        );

        verify(dao, times(1)).existsByEmailCorporativo(any(String.class));
    }


    @Test
    public void updateUsuario_DeberiaLanzarImmutableFieldException_CuandoCampoInmutableSeModifica() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailCorporativo("emailCorporativo@empresa.com");
        usuario.setContrasena("123456");
        usuario.setRolId(99L);

        when(rolDAO.existsById(any(Long.class))).thenReturn(true);
        when(dao.updateUsuario(any(Long.class), any(UsuarioDTO.class)))
                .thenThrow(new ImmutableFieldException("Campo inmutable modificado"));

        // ---------- ACT ----------
        Executable accion = () -> service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        ImmutableFieldException ex = assertThrows(
                ImmutableFieldException.class,
                accion,
                "Debe lanzar ImmutableFieldException si se modifica un campo inmutable"
        );

        assertEquals("Campo inmutable modificado", ex.getMessage());
        verify(dao, times(1)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }


    @Test
    public void updateUsuario_DeberiaRetornarUsuarioDTOConNuevosDatos_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        usuario.setId(idValido);
        usuario.setEmailCorporativo("emailCorporativo@empresa.com");
        usuario.setEmailPersonal("email@email.com");
        usuario.setContrasena("123456");
        usuario.setRolId(99L);

        when(dao.updateUsuario(any(Long.class), any(UsuarioDTO.class))).thenReturn(usuario);
        when(rolDAO.existsById(any(Long.class))).thenReturn(true);

        // ---------- ACT ----------
        UsuarioDTO resultado = service.updateUsuario(idValido, usuario);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals("email@email.com", resultado.getEmailPersonal(), "El email personal del usuario debe coincidir");
        assertEquals("123456", resultado.getContrasena(), "La contraseña del usuario debe coincidir");

        verify(dao, times(1)).updateUsuario(any(Long.class), any(UsuarioDTO.class));
    }
}
