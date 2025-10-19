/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jsj.api.entity.dto.NotificacionDTO;
import com.jsj.api.entity.dao.NotificacionDAO;
import com.jsj.api.exception.InsufficientSavingPermissionsException;

/**
 *
 * @author Juan José Molano Franco
 */
@ExtendWith(MockitoExtension.class)
public class NotificacionServiceTest {

    @Mock
    private NotificacionDAO dao;

    @InjectMocks
    private NotificacionService service;

    private Long idTicket;
    private Long usuarioId;
    private String titulo;
    private String prioridad;
    private String nombre;
    private String emailCorporativo;

    @BeforeEach
    void setUp() {
        idTicket = 1L;
        usuarioId = 2L;
        titulo = "Ticket de prueba";
        prioridad = "Urgente";
        nombre = "Agente Juan";
        emailCorporativo = "juan@empresa.com";
    }

    @AfterEach
    void tearDown() {
        // Placeholder for potential static mock cleanup (e.g., CurrentUser)
    }

    // ============================================================
    // SECCIÓN 1: notifyComentario()
    // ============================================================
    @Test
    void notifyComentario_DeberiaCrearYGuardarNotificacion_CuandoDatosSonValidos() throws Exception {
        // ---------- ARRANGE ----------
        NotificacionDTO expectedDTO = new NotificacionDTO();
        expectedDTO.setUsuarioId(usuarioId);
        expectedDTO.setMensaje(String.format("Nuevo comentario añadido al tiquete con id %s, \"%s\"", idTicket, titulo));

        // ---------- ACT ----------
        service.notifyComentario(idTicket, usuarioId, titulo);

        // ---------- ASSERT ----------
        ArgumentCaptor<NotificacionDTO> captor = ArgumentCaptor.forClass(NotificacionDTO.class);
        verify(dao, times(1)).save(captor.capture());
        NotificacionDTO actualDTO = captor.getValue();

        assertNotNull(actualDTO, "El DTO no debe ser nulo");
        assertEquals(usuarioId, actualDTO.getUsuarioId(), "El ID del usuario debe coincidir");
        assertEquals(expectedDTO.getMensaje(), actualDTO.getMensaje(), "El mensaje debe coincidir");
        assertNull(actualDTO.getId(), "El ID debe ser nulo antes de persistir");
        assertNull(actualDTO.getFechaCreacion(), "La fecha de creación debe ser nula antes de persistir");
    }

    @Test
    void notifyComentario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoDaoFalla() throws InsufficientSavingPermissionsException {
        // ---------- ARRANGE ----------
        when(dao.save(any(NotificacionDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.notifyComentario(idTicket, usuarioId, titulo);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si el DAO falla"
        );
        verify(dao, times(1)).save(any(NotificacionDTO.class));
    }

    // ============================================================
    // SECCIÓN 2: notifyPrioridad()
    // ============================================================
    @Test
    void notifyPrioridad_DeberiaCrearYGuardarNotificacion_CuandoDatosSonValidos() throws Exception {
        // ---------- ARRANGE ----------
        NotificacionDTO expectedDTO = new NotificacionDTO();
        expectedDTO.setUsuarioId(usuarioId);
        expectedDTO.setMensaje(String.format("El tiquete con id %s, \"%s\", ha cambiado a \"%s\"", idTicket, titulo, prioridad));

        // ---------- ACT ----------
        service.notifyPrioridad(idTicket, usuarioId, titulo, prioridad);

        // ---------- ASSERT ----------
        ArgumentCaptor<NotificacionDTO> captor = ArgumentCaptor.forClass(NotificacionDTO.class);
        verify(dao, times(1)).save(captor.capture());
        NotificacionDTO actualDTO = captor.getValue();

        assertNotNull(actualDTO, "El DTO no debe ser nulo");
        assertEquals(usuarioId, actualDTO.getUsuarioId(), "El ID del usuario debe coincidir");
        assertEquals(expectedDTO.getMensaje(), actualDTO.getMensaje(), "El mensaje debe coincidir");
        assertNull(actualDTO.getId(), "El ID debe ser nulo antes de persistir");
        assertNull(actualDTO.getFechaCreacion(), "La fecha de creación debe ser nula antes de persistir");
    }

    @Test
    void notifyPrioridad_DeberiaLanzarInsufficientSavingPermissionsException_CuandoDaoFalla() throws InsufficientSavingPermissionsException {
        // ---------- ARRANGE ----------
        when(dao.save(any(NotificacionDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.notifyPrioridad(idTicket, usuarioId, titulo, prioridad);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si el DAO falla"
        );
        verify(dao, times(1)).save(any(NotificacionDTO.class));
    }

    // ============================================================
    // SECCIÓN 3: notifyCierre()
    // ============================================================
    @Test
    void notifyCierre_DeberiaCrearYGuardarNotificacion_CuandoDatosSonValidos() throws Exception {
        // ---------- ARRANGE ----------
        NotificacionDTO expectedDTO = new NotificacionDTO();
        expectedDTO.setUsuarioId(usuarioId);
        expectedDTO.setMensaje(String.format("El tiquete con id %s, \"%s\", ha sido cerrado", idTicket, titulo));

        // ---------- ACT ----------
        service.notifyCierre(idTicket, usuarioId, titulo);

        // ---------- ASSERT ----------
        ArgumentCaptor<NotificacionDTO> captor = ArgumentCaptor.forClass(NotificacionDTO.class);
        verify(dao, times(1)).save(captor.capture());
        NotificacionDTO actualDTO = captor.getValue();

        assertNotNull(actualDTO, "El DTO no debe ser nulo");
        assertEquals(usuarioId, actualDTO.getUsuarioId(), "El ID del usuario debe coincidir");
        assertEquals(expectedDTO.getMensaje(), actualDTO.getMensaje(), "El mensaje debe coincidir");
        assertNull(actualDTO.getId(), "El ID debe ser nulo antes de persistir");
        assertNull(actualDTO.getFechaCreacion(), "La fecha de creación debe ser nula antes de persistir");
    }

    @Test
    void notifyCierre_DeberiaLanzarInsufficientSavingPermissionsException_CuandoDaoFalla() throws InsufficientSavingPermissionsException {
        // ---------- ARRANGE ----------
        when(dao.save(any(NotificacionDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.notifyCierre(idTicket, usuarioId, titulo);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si el DAO falla"
        );
        verify(dao, times(1)).save(any(NotificacionDTO.class));
    }

    // ============================================================
    // SECCIÓN 4: notifyReasignacion()
    // ============================================================
    @Test
    void notifyReasignacion_DeberiaCrearYGuardarNotificacion_CuandoDatosSonValidos() throws Exception {
        // ---------- ARRANGE ----------
        NotificacionDTO expectedDTO = new NotificacionDTO();
        expectedDTO.setUsuarioId(usuarioId);
        expectedDTO.setMensaje(String.format("El tiquete con id %s, \"%s\", ha sido reasignado al agente %s, email: %s", idTicket, titulo, nombre, emailCorporativo));

        // ---------- ACT ----------
        service.notifyReasignacion(idTicket, usuarioId, titulo, nombre, emailCorporativo);

        // ---------- ASSERT ----------
        ArgumentCaptor<NotificacionDTO> captor = ArgumentCaptor.forClass(NotificacionDTO.class);
        verify(dao, times(1)).save(captor.capture());
        NotificacionDTO actualDTO = captor.getValue();

        assertNotNull(actualDTO, "El DTO no debe ser nulo");
        assertEquals(usuarioId, actualDTO.getUsuarioId(), "El ID del usuario debe coincidir");
        assertEquals(expectedDTO.getMensaje(), actualDTO.getMensaje(), "El mensaje debe coincidir");
        assertNull(actualDTO.getId(), "El ID debe ser nulo antes de persistir");
        assertNull(actualDTO.getFechaCreacion(), "La fecha de creación debe ser nula antes de persistir");
    }

    @Test
    void notifyReasignacion_DeberiaLanzarInsufficientSavingPermissionsException_CuandoDaoFalla() throws InsufficientSavingPermissionsException {
        // ---------- ARRANGE ----------
        when(dao.save(any(NotificacionDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.notifyReasignacion(idTicket, usuarioId, titulo, nombre, emailCorporativo);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si el DAO falla"
        );
        verify(dao, times(1)).save(any(NotificacionDTO.class));
    }
}
