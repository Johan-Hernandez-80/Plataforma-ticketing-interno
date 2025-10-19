package com.jsj.api.service;

import com.jsj.api.constants.TicketConstants;
import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.PrioridadDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
  private Long idTicketValido;
  private Long idUsuarioValido;
  private ComentarioDTO comentarioValido;
  private  PrioridadDTO prioridadValida;
  private TicketDTO ticketActualizado;
  private TicketDTO ticketCerrado;
  List<TicketDTO> listaTicketsValida;

  @BeforeEach
  void setUp() {
    agenteValido = 23L;
    fechaActual = LocalDate.now();
    idTicketValido = 1L;
    idUsuarioValido = 2L;
    comentarioValido = new ComentarioDTO();
    prioridadValida = new PrioridadDTO("Importante");
    ticketActualizado = new TicketDTO();
    ticketCerrado = new TicketDTO();
    listaTicketsValida = new ArrayList<>();
  }

  // ============================================================
  // SECCIÓN 1: findTicketsFiltrados()
  // ============================================================

  // CASO 1: Prioridad inválida
  @Test
  void findTicketsFiltrados_DeberiaLanzarPrioridadInvalidaException_CuandoPrioridadEsInvalida() {
      // ---------- ARRANGE ----------
      String prioridadInvalida = "SUPER_IMPORTANTE";
      String estadoValido = "PENDIENTE";

      // ---------- ACT ----------
      Executable accion = () -> service.findTicketsFiltrados(estadoValido, prioridadInvalida, agenteValido, fechaActual);

      // ---------- ASSERT ----------
      PrioridadInvalidaException ex = assertThrows(
              PrioridadInvalidaException.class,
              accion,
              "Debe lanzar PrioridadInvalidaException si la prioridad es inválida"
      );

      verify(dao, never()).findTicketsFiltrados(any(String.class), any(String.class), any(), any());
  }

    // CASO 2: Estado inválido
    @Test
    void findTicketsFiltrados_DeberiaLanzarEstadoInvalidoException_CuandoEstadoEsInvalido() {
        // ---------- ARRANGE ----------
        String estadoInvalido = "DESCONOCIDO";
        String prioridadValida = "URGENTE";

        // ---------- ACT ----------
        Executable accion = () -> service.findTicketsFiltrados(
                estadoInvalido, prioridadValida, agenteValido, fechaActual
        );

        // ---------- ASSERT ----------
        EstadoInvalidoException ex = assertThrows(
                EstadoInvalidoException.class,
                accion,
                "Debe lanzar EstadoInvalidoException si el estado no es válido"
        );

        verify(dao, never()).findTicketsFiltrados(
                any(String.class), any(String.class), any(Long.class), any(LocalDate.class)
        );
    }

    // CASO 3: Agente inexistente
    @Test
    void findTicketsFiltrados_DeberiaLanzarAgenteInexistenteException_CuandoAgenteNoExiste() {
        // ---------- ARRANGE ----------
        String prioridadValida = "Urgente";
        String estadoValido = "Pendiente";
        Long agenteInvalido = 99L;

        when(usuarioDao.isAgente(agenteInvalido)).thenReturn(0);

        // ---------- ACT ----------
        Executable accion = () -> service.findTicketsFiltrados(
                estadoValido, prioridadValida, agenteInvalido, fechaActual
        );

        // ---------- ASSERT ----------
        AgenteInexistenteException ex = assertThrows(
                AgenteInexistenteException.class,
                accion,
                "Debe lanzar AgenteInexistenteException si el agente no existe"
        );

        verify(dao, never()).findTicketsFiltrados(
                any(String.class), any(String.class), any(Long.class), any(LocalDate.class)
        );
    }

    // CASO 4: Caso exitoso
    @Test
    void findTicketsFiltrados_DeberiaRetornarListaDeTickets_CuandoParametrosSonValidos() throws Exception {
        // ---------- ARRANGE ----------
        String prioridadValida = "Urgente";
        String estadoValido = "Pendiente";
        Long agenteValido = 99L;

        ticketActualizado.setTitulo("Ticket de prueba");
        listaTicketsValida.add(ticketActualizado);

        when(usuarioDao.isAgente(agenteValido)).thenReturn(1);
        when(dao.findTicketsFiltrados(
                any(String.class), any(String.class), eq(agenteValido), eq(fechaActual)
        )).thenReturn(listaTicketsValida);

        // ---------- ACT ----------
        List<TicketDTO> resultado = service.findTicketsFiltrados(
                estadoValido, prioridadValida, agenteValido, fechaActual
        );

        // ---------- ASSERT ----------
        assertNotNull(resultado, "La lista de tickets no debe ser nula");
        assertEquals(1, resultado.size(), "Debe retornar un ticket");
        assertEquals("Ticket de prueba", resultado.get(0).getTitulo());

        verify(dao, times(1)).findTicketsFiltrados(
                any(String.class), any(String.class), eq(agenteValido), eq(fechaActual)
        );
    }


  // ============================================================
  // SECCIÓN 2: addComentario()
  // ============================================================

    // CASO 1: Ticket inexistente
    @Test
    void addComentario_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        // ---------- ARRANGE ----------
        when(dao.existsById(idTicketValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        TicketInexistenteException ex = assertThrows(
                TicketInexistenteException.class,
                accion,
                "Debe lanzar TicketInexistenteException si el ticket no existe"
        );
        verify(dao, times(1)).existsById(idTicketValido);
        verifyNoInteractions(comentarioDao);
    }


    // CASO 2: Usuario inexistente
    @Test
    void addComentario_DeberiaLanzarUsuarioInexistenteException_CuandoElUsuarioNoExiste() {
        // ---------- ARRANGE ----------
        comentarioValido.setUsuarioId(idUsuarioValido);
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        UsuarioInexistenteException ex = assertThrows(
                UsuarioInexistenteException.class,
                accion,
                "Debe lanzar UsuarioInexistenteException si el usuario no existe"
        );

        verify(usuarioDao, times(1)).existsById(idUsuarioValido);
        verifyNoInteractions(comentarioDao);
    }


    // CASO 3: Comenterio nulo
    @Test
    void addComentario_DeberiaLanzarCampoInvalidoException_CuandoComentarioEsNulo() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        comentarioValido.setComentario(null);

        assertThrows(CampoInvalidoException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(comentarioDao, never()).save(any(ComentarioDTO.class));
    }

    // CASO 4: Comentario muy largo
    @Test
    void addComentario_DeberiaLanzarCampoInvalidoException_CuandoComentarioEsDemasiadoLargo() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        comentarioValido.setComentario("A".repeat(1200));

        assertThrows(CampoInvalidoException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(comentarioDao, never()).save(any(ComentarioDTO.class));
    }

    // CASO 5: Dao indica falta de permisos
    @Test
    void addComentario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoElDaoLoIndica() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(comentarioDao.save(any(ComentarioDTO.class))).thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        assertThrows(InsufficientSavingPermissionsException.class, () ->
                service.addComentario(idTicketValido, comentarioValido)
        );

        verify(comentarioDao, times(1)).save(any(ComentarioDTO.class));
    }

    // CASO 6: Caso exitoso
    @Test
    void addComentario_DeberiaRetornarComentarioDTO_CuandoTodoEsValido() throws Exception {
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(comentarioDao.save(any(ComentarioDTO.class))).thenAnswer(invocation -> invocation.getArgument(0));

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
        PrioridadDTO prioridadInvalida = new PrioridadDTO("Tardio");
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
