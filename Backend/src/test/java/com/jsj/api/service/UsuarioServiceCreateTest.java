package com.jsj.api.service;

import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.entity.dao.RolDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.UsuarioDTO;
import com.jsj.api.exception.EmailInvalidoException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests para creación de usuarios (CP-54, CP-55, CP-56)
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceCreateTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private NotificacionDAO notificacionDAO;

    @Mock
    private RolDAO rolDAO;

    @Mock
    private UsuarioDAO dao;

    @InjectMocks
    private UsuarioService service;

    private UsuarioDTO input;
    private UsuarioDTO saved;

    @BeforeEach
    void setUp() {
        input = new UsuarioDTO();
        input.setNombre("Carlos Gómez");
        input.setEmailCorporativo("carlos.gomez@empresa.com");
        input.setContrasena("Password123");
        input.setRolId(1L);

        saved = new UsuarioDTO();
        saved.setId(101L);
        saved.setNombre(input.getNombre());
        saved.setEmailCorporativo(input.getEmailCorporativo());
        saved.setRolId(input.getRolId());
    }

    // CP-54: Crear cuenta - caso exitoso
    @Test
    void save_DeberiaRetornarUsuarioDTO_CuandoTodoEsValido() throws Exception {
        when(rolDAO.existsById(anyLong())).thenReturn(true);
        when(dao.save(any(UsuarioDTO.class))).thenReturn(saved);

        UsuarioDTO result = service.save(input);

        assertNotNull(result);
        assertEquals(101L, result.getId());
        assertEquals("Carlos Gómez", result.getNombre());
        verify(dao, times(1)).save(any(UsuarioDTO.class));
    }

    // CP-55: Datos inválidos al crear cuenta (DAO o validación lanza EmailInvalidoException)
    @Test
    void save_DeberiaLanzarEmailInvalidoException_CuandoDatosSonInvalidos() throws Exception {
        when(rolDAO.existsById(anyLong())).thenReturn(true);
        doAnswer(invocation -> { throw new EmailInvalidoException("Datos inválidos"); })
            .when(dao).save(any(UsuarioDTO.class));

        EmailInvalidoException ex = assertThrows(EmailInvalidoException.class, () -> service.save(input));
        assertEquals("Datos inválidos", ex.getMessage());
        verify(dao, times(1)).save(any(UsuarioDTO.class));
    }

    // CP-56: Error del servidor al registrar cuenta (DAO falla)
    @Test
    void save_DeberiaPropagarRuntimeException_CuandoDaoFalla() throws InsufficientSavingPermissionsException {
        when(rolDAO.existsById(anyLong())).thenReturn(true);
        when(dao.save(any(UsuarioDTO.class))).thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.save(input));
        assertEquals("DB error", ex.getMessage());
        verify(dao, times(1)).save(any(UsuarioDTO.class));
    }
}