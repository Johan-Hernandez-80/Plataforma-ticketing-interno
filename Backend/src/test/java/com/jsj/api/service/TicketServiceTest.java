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


    // CASO 3: Comentario nulo
    @Test
    void addComentario_DeberiaLanzarCampoInvalidoException_CuandoComentarioEsNulo() throws Exception {
        // ---------- ARRANGE ----------
        comentarioValido.setComentario(null);
        comentarioValido.setUsuarioId(idUsuarioValido);
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        CampoInvalidoException ex = assertThrows(
                CampoInvalidoException.class,
                accion,
                "Debe lanzar CampoInvalidoException si el comentario es nulo"
        );

        verify(comentarioDao, never()).save(any(ComentarioDTO.class));
    }


    // CASO 4: Comentario muy largo
    @Test
    void addComentario_DeberiaLanzarCampoInvalidoException_CuandoComentarioEsDemasiadoLargo() throws Exception {
        // ---------- ARRANGE ----------
        comentarioValido.setComentario("A".repeat(1200));
        comentarioValido.setUsuarioId(idUsuarioValido);
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(any())).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        CampoInvalidoException ex = assertThrows(
                CampoInvalidoException.class,
                accion,
                "Debe lanzar CampoInvalidoException si el comentario supera el tamaño máximo permitido"
        );

        verify(comentarioDao, never()).save(any(ComentarioDTO.class));
    }


    // CASO 5: Dao indica falta de permisos
    @Test
    void addComentario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoElDaoLoIndica() throws Exception {
        // ---------- ARRANGE ----------
        comentarioValido.setComentario("Comentario de prueba");
        comentarioValido.setUsuarioId(idUsuarioValido);

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(any())).thenReturn(true);
        when(comentarioDao.save(any(ComentarioDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si el DAO indica falta de permisos"
        );

        verify(comentarioDao, times(1)).save(any(ComentarioDTO.class));
    }


    // CASO 6: Caso exitoso
    @Test
    void addComentario_DeberiaRetornarComentarioDTO_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        comentarioValido.setComentario("Comentario de prueba");
        comentarioValido.setUsuarioId(idUsuarioValido);

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.existsById(any())).thenReturn(true);
        when(comentarioDao.save(any(ComentarioDTO.class)))
                .thenReturn(comentarioValido);

        // ---------- ACT ----------
        ComentarioDTO resultado = service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getTicketId(), "Debe mantener el ID del ticket");
        assertNull(resultado.getId(), "El ID del comentario debe ser nulo antes de persistirlo");
        assertNull(resultado.getFechaCreacion(), "La fecha de creación debe ser nula antes de persistir");
        assertEquals("Comentario de prueba", resultado.getComentario(), "El comentario debe coincidir con el original");

        verify(comentarioDao, times(1)).save(any(ComentarioDTO.class));
    }


  // ============================================================
  // SECCIÓN 3: updatePrioridad()
  // ============================================================

    // CASO 1: Prioridad inválida
    @Test
    void updatePrioridad_DeberiaLanzarPrioridadInvalidaException_CuandoLaPrioridadNoEsValida() {
        // ---------- ARRANGE ----------
        PrioridadDTO prioridadInvalida = new PrioridadDTO("Tardio");

        // ---------- ACT ----------
        Executable accion = () -> service.updatePrioridad(idTicketValido, prioridadInvalida);

        // ---------- ASSERT ----------
        PrioridadInvalidaException ex = assertThrows(
                PrioridadInvalidaException.class,
                accion,
                "Debe lanzar PrioridadInvalidaException si la prioridad no es válida"
        );

        verify(dao, never()).updatePrioridad(anyLong(), anyString());
    }


    // CASO 2: Ticket inexistente
    @Test
    void updatePrioridad_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        // ---------- ARRANGE ----------
        when(dao.existsById(idTicketValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.updatePrioridad(idTicketValido, prioridadValida);

        // ---------- ASSERT ----------
        TicketInexistenteException ex = assertThrows(
                TicketInexistenteException.class,
                accion,
                "Debe lanzar TicketInexistenteException si el ticket no existe"
        );

        verify(dao, times(1)).existsById(idTicketValido);
        verify(dao, never()).updatePrioridad(anyLong(), anyString());
    }

    // CASO 3: Caso exitoso
    @Test
    void updatePrioridad_DeberiaRetornarTicketDTO_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        ticketActualizado.setId(idTicketValido);
        ticketActualizado.setPrioridad("Importante");
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.updatePrioridad(idTicketValido, "Importante")).thenReturn(ticketActualizado);

        // ---------- ACT ----------
        TicketDTO resultado = service.updatePrioridad(idTicketValido, prioridadValida);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("Importante", resultado.getPrioridad(), "La prioridad debe actualizarse correctamente");

        verify(dao, times(1)).updatePrioridad(idTicketValido, "Importante");
    }


  // ============================================================
  // SECCIÓN 4: cerrarTicket()
  // ============================================================

    // CASO 1: Ticket inexistente
    @Test
    void cerrarTicket_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        // ---------- ARRANGE ----------
        when(dao.existsById(idTicketValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.cerrarTicket(idTicketValido);

        // ---------- ASSERT ----------
        TicketInexistenteException ex = assertThrows(
                TicketInexistenteException.class,
                accion,
                "Debe lanzar TicketInexistenteException si el ticket no existe"
        );

        verify(dao, times(1)).existsById(idTicketValido);
        verify(dao, never()).cerrarTicket(anyLong());
    }

    // CASO 2: Caso exitoso
    @Test
    void cerrarTicket_DeberiaRetornarTicketDTO_CuandoElTicketExiste() throws Exception {
        // ---------- ARRANGE ----------
        ticketCerrado.setId(idTicketValido);
        ticketCerrado.setEstado("CERRADO");

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.cerrarTicket(idTicketValido)).thenReturn(ticketCerrado);

        // ---------- ACT ----------
        TicketDTO resultado = service.cerrarTicket(idTicketValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("CERRADO", resultado.getEstado(), "El estado del ticket debe actualizarse a CERRADO");

        verify(dao, times(1)).cerrarTicket(idTicketValido);
    }

}
