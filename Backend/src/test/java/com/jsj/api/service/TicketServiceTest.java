package com.jsj.api.service;

import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.PrioridadDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

  @Mock
  private TicketDAO dao;

  @Mock
  private ComentarioDAO comentarioDao;

  @Mock
  private UsuarioDAO usuarioDao;

  @InjectMocks
  private TicketService service;

  private Long agenteValido;
  private LocalDate fechaActual;

  @BeforeEach
  void setUp() {
    agenteValido = null;
    fechaActual = null;
  }

  // ============================================================
  // SECCIÓN 1: findTicketsFiltrados()
  // ============================================================

  // CASO 1: Prioridad inválida
  @Test
  void findTicketsFiltrados_DeberiaLanzarPrioridadInvalidaException_CuandoPrioridadEsInvalida() {
    // Arrange
    String prioridad = "SUPER_IMPORTANTE"; // no existe en constantes
    String estado = "ABIERTO";

    // Act & Assert
    assertThrows(PrioridadInvalidaException.class, () ->
            service.findTicketsFiltrados(estado, prioridad, agenteValido, fechaActual)
    );

    // Verifica que no se llegó a consultar el DAO
    verify(dao, never()).findTicketsFiltrados(any(), any(), any(), any());
  }

  // CASO 2: Estado inválido
  @Test
  void findTicketsFiltrados_DeberiaLanzarEstadoInvalidoException_CuandoEstadoEsInvalido() {
    // Arrange
    String estado = "DESCONOCIDO";
    String prioridad = "BAJA";

    // Act & Assert
    assertThrows(EstadoInvalidoException.class, () ->
            service.findTicketsFiltrados(estado, prioridad, agenteValido, fechaActual)
    );

    verify(dao, never()).findTicketsFiltrados(any(), any(), any(), any());
  }

  // CASO 3: Agente inexistente
  @Test
  void findTicketsFiltrados_DeberiaLanzarAgenteInexistenteException_CuandoAgenteNoExiste() {
    // Arrange
    String prioridad = "BAJA";
    String estado = "ABIERTO";
    Long agenteInvalido = 99L;

    // Simula que el agente no existe
    when(usuarioDao.isAgente(agenteInvalido)).thenReturn(0);

    // Act & Assert
    assertThrows(AgenteInexistenteException.class, () ->
            service.findTicketsFiltrados(estado, prioridad, agenteInvalido, fechaActual)
    );

    verify(dao, never()).findTicketsFiltrados(any(), any(), any(), any());
  }

  // CASO 4: Caso exitoso
  @Test
  void findTicketsFiltrados_DeberiaRetornarListaDeTickets_CuandoParametrosSonValidos() throws Exception {
        // Arrange
        String prioridad = "ALTA";
        String estado = "ABIERTO";
        TicketDTO ticket = new TicketDTO();
        ticket.setTitulo("Ticket de prueba");
        List<TicketDTO> listaTicketsValida = new ArrayList<>();
        listaTicketsValida.add(ticket);

        when(usuarioDao.isAgente(agenteValido)).thenReturn(1);
        when(dao.findTicketsFiltrados(anyString(), anyString(), eq(agenteValido), eq(fechaActual)))
                .thenReturn(listaTicketsValida);

        // Act
        List<TicketDTO> result = service.findTicketsFiltrados(estado, prioridad, agenteValido, fechaActual);

        // Assert
        assertNotNull(result, "La lista de tickets no debe ser nula");
        assertEquals(1, result.size(), "Debe retornar un ticket");
        assertEquals("Ticket de prueba", result.get(0).getTitulo());
        verify(dao, times(1)).findTicketsFiltrados(anyString(), anyString(), eq(agenteValido), eq(fechaActual));
    }

  // ============================================================
  // SECCIÓN 2: addComentario()
  // ============================================================

    // CASO 1: Ticket inexistente
    @Test
    void addComentario_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        when(dao.existsById(idTicketValido)).thenReturn(false);

        assertThrows(TicketInexistenteException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(dao, times(1)).existsById(idTicketValido);
        verifyNoInteractions(comentarioDao);
    }

    // CASO 2: Usuario inexistente
    @Test
    void addComentario_DeberiaLanzarUsuarioInexistenteException_CuandoElUsuarioNoExiste() {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(false);

        assertThrows(UsuarioInexistenteException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(usuarioDao, times(1)).existsById(idUsuarioValido);
        verifyNoInteractions(comentarioDao);
    }

    // CASO 3: Comenterio nulo
    @Test
    void addComentario_DeberiaLanzarCampoInvalidoException_CuandoComentarioEsNulo() {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        comentarioValido.setComentario(null);

        assertThrows(CampoInvalidoException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(comentarioDao, never()).save(any());
    }

    // CASO 4: Comentario muy largo
    @Test
    void addComentario_DeberiaLanzarCampoInvalidoException_CuandoComentarioEsDemasiadoLargo() {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        comentarioValido.setComentario("A".repeat(1200));

        assertThrows(CampoInvalidoException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(comentarioDao, never()).save(any());
    }

    // CASO 5: Dao indica falta de permisos
    @Test
    void addComentario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoElDaoLoIndica() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(comentarioDao.save(any())).thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        assertThrows(InsufficientSavingPermissionsException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(comentarioDao, times(1)).save(any());
    }

    // CASO 6: Caso exitoso
    @Test
    void addComentario_DeberiaRetornarComentarioDTO_CuandoTodoEsValido() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(comentarioDao.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ComentarioDTO resultado = service.addComentario(idTicketValido, comentarioValido);

        assertNotNull(resultado);
        assertEquals(idTicketValido, resultado.getTicketId());
        assertNull(resultado.getId());
        assertNull(resultado.getFechaCreacion());
        assertEquals("Este es un comentario válido.", resultado.getComentario());
        verify(comentarioDao, times(1)).save(any(ComentarioDTO.class));
    }

  // ============================================================
  // SECCIÓN 3: updatePrioridad()
  // ============================================================

    // CASO 1: Prioridad inválida
    @Test
    void updatePrioridad_DeberiaLanzarPrioridadInvalidaException_CuandoLaPrioridadNoEsValida() {
        PrioridadDTO prioridadInvalida = new PrioridadDTO();
        prioridadInvalida.setPrioridad("SUPER-ALTA");

        assertThrows(PrioridadInvalidaException.class, () ->
                service.updatePrioridad(idTicketValido, prioridadInvalida)
        );

        verify(dao, never()).updatePrioridad(anyLong(), anyString());
    }

    // CASO 2: Ticket inexistente
    @Test
    void updatePrioridad_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        when(dao.existsById(idTicketValido)).thenReturn(false);

        assertThrows(TicketInexistenteException.class, () ->
                service.updatePrioridad(idTicketValido, prioridadValida)
        );

        verify(dao, times(1)).existsById(idTicketValido);
        verify(dao, never()).updatePrioridad(anyLong(), anyString());
    }

    // CASO 3: Caso exitoso
    @Test
    void updatePrioridad_DeberiaRetornarTicketDTO_CuandoTodoEsValido() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.updatePrioridad(idTicketValido, "ALTA")).thenReturn(ticketActualizado);

        TicketDTO resultado = service.updatePrioridad(idTicketValido, prioridadValida);

        assertNotNull(resultado);
        assertEquals(idTicketValido, resultado.getId());
        assertEquals("ALTA", resultado.getPrioridad());
        verify(dao, times(1)).updatePrioridad(idTicketValido, "ALTA");
    }

  // ============================================================
  // SECCIÓN 4: cerrarTicket()
  // ============================================================

    // CASO 1: Ticket inexistente
    @Test
    void cerrarTicket_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        when(dao.existsById(idTicketValido)).thenReturn(false);

        assertThrows(TicketInexistenteException.class, () ->
                service.cerrarTicket(idTicketValido)
        );

        verify(dao, times(1)).existsById(idTicketValido);
        verify(dao, never()).cerrarTicket(anyLong());
    }

    // CASO 2: Caso exitoso
    @Test
    void cerrarTicket_DeberiaRetornarTicketDTO_CuandoElTicketExiste() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.cerrarTicket(idTicketValido)).thenReturn(ticketCerrado);

        TicketDTO resultado = service.cerrarTicket(idTicketValido);

        assertNotNull(resultado);
        assertEquals(idTicketValido, resultado.getId());
        assertEquals("CERRADO", resultado.getEstado());
        verify(dao, times(1)).cerrarTicket(idTicketValido);
    }
}
