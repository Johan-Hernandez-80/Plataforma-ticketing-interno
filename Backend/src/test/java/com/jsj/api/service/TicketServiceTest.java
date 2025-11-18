package com.jsj.api.service;

import com.jsj.api.constants.TicketConstants;
import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.PrioridadDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.exception.*;
import com.jsj.api.security.CurrentUser;
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
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketDAO dao;

    @Mock
    private ComentarioDAO comentarioDao;

    @Mock
    private UsuarioDAO usuarioDao;

    @Mock
    private NotificacionService notificacionService;

    @Mock
    private CategoriaDAO categoriaDao;

    @InjectMocks
    private TicketService service;

    private Long agenteValido;
    private LocalDate fechaActual;
    private Long idTicketValido;
    private Long idUsuarioValido;
    private ComentarioDTO comentarioValido;
    private PrioridadDTO prioridadValida;
    private TicketDTO ticketActualizado;
    private TicketDTO ticketCerrado;
    private Ticket ticket;
    private List<TicketDTO> listaTicketsValida;
    private MockedStatic<CurrentUser> currentUserMock;

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
        ticket = new Ticket();
        ticket.setId(idTicketValido);
        ticket.setUsuarioId(idUsuarioValido);
        ticket.setTitulo("Ticket de prueba");
        listaTicketsValida = new ArrayList<>();
        currentUserMock = mockStatic(CurrentUser.class);
        currentUserMock.when(CurrentUser::getUserId).thenReturn(String.valueOf(idUsuarioValido));
    }

    @AfterEach
    void tearDown() {
        currentUserMock.close();
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
        Executable accion = () -> service.findTicketsFiltrados(estadoValido, prioridadInvalida, agenteValido,
                fechaActual);

        // ---------- ASSERT ----------
        PrioridadInvalidaException ex = assertThrows(
                PrioridadInvalidaException.class,
                accion,
                "Debe lanzar PrioridadInvalidaException si la prioridad es inválida");

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
                estadoInvalido, prioridadValida, agenteValido, fechaActual);

        // ---------- ASSERT ----------
        EstadoInvalidoException ex = assertThrows(
                EstadoInvalidoException.class,
                accion,
                "Debe lanzar EstadoInvalidoException si el estado no es válido");

        verify(dao, never()).findTicketsFiltrados(
                any(String.class), any(String.class), any(Long.class), any(LocalDate.class));
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
                estadoValido, prioridadValida, agenteInvalido, fechaActual);

        // ---------- ASSERT ----------
        AgenteInexistenteException ex = assertThrows(
                AgenteInexistenteException.class,
                accion,
                "Debe lanzar AgenteInexistenteException si el agente no existe");

        verify(dao, never()).findTicketsFiltrados(
                any(String.class), any(String.class), any(Long.class), any(LocalDate.class));
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
                any(String.class), any(String.class), eq(agenteValido), eq(fechaActual)))
                .thenReturn(listaTicketsValida);

        // ---------- ACT ----------
        List<TicketDTO> resultado = service.findTicketsFiltrados(
                estadoValido, prioridadValida, agenteValido, fechaActual);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "La lista de tickets no debe ser nula");
        assertEquals(1, resultado.size(), "Debe retornar un ticket");
        assertEquals("Ticket de prueba", resultado.get(0).getTitulo());

        verify(dao, times(1)).findTicketsFiltrados(
                any(String.class), any(String.class), eq(agenteValido), eq(fechaActual));
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
                "Debe lanzar TicketInexistenteException si el ticket no existe");
        verify(dao, times(1)).existsById(idTicketValido);
        verifyNoInteractions(comentarioDao);
        verifyNoInteractions(notificacionService);
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
                "Debe lanzar UsuarioInexistenteException si el usuario no existe");

        verify(usuarioDao, times(1)).existsById(idUsuarioValido);
        verifyNoInteractions(comentarioDao);
        verifyNoInteractions(notificacionService);
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
                "Debe lanzar CampoInvalidoException si el comentario es nulo");

        verify(comentarioDao, never()).save(any(ComentarioDTO.class));
        verifyNoInteractions(notificacionService);
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
                "Debe lanzar CampoInvalidoException si el comentario supera el tamaño máximo permitido");

        verify(comentarioDao, never()).save(any(ComentarioDTO.class));
        verifyNoInteractions(notificacionService);
    }

    // CASO 5: Dao indica falta de permisos
    @Test
    void addComentario_DeberiaLanzarInsufficientSavingPermissionsException_CuandoElDaoLoIndica() throws Exception {
        // ---------- ARRANGE ----------
        comentarioValido.setComentario("Comentario de prueba");
        comentarioValido.setUsuarioId(idUsuarioValido);

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        when(usuarioDao.existsById(any())).thenReturn(true);
        when(comentarioDao.save(any(ComentarioDTO.class)))
                .thenThrow(new InsufficientSavingPermissionsException("Sin permisos"));

        // ---------- ACT ----------
        Executable accion = () -> service.addComentario(idTicketValido, comentarioValido);

        // ---------- ASSERT ----------
        InsufficientSavingPermissionsException ex = assertThrows(
                InsufficientSavingPermissionsException.class,
                accion,
                "Debe lanzar InsufficientSavingPermissionsException si el DAO indica falta de permisos");

        verify(dao, times(1)).findById(idTicketValido);
        verify(comentarioDao, times(1)).save(any(ComentarioDTO.class));
        verify(notificacionService, times(1)).notifyComentario(idTicketValido, idUsuarioValido, "Ticket de prueba");
    }

    // CASO 6: Caso exitoso
    @Test
    void addComentario_DeberiaRetornarComentarioDTO_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        comentarioValido.setComentario("Comentario de prueba");
        comentarioValido.setUsuarioId(idUsuarioValido);

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
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

        verify(dao, times(1)).findById(idTicketValido);
        verify(comentarioDao, times(1)).save(any(ComentarioDTO.class));
        verify(notificacionService, times(1)).notifyComentario(idTicketValido, idUsuarioValido, "Ticket de prueba");
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
                "Debe lanzar PrioridadInvalidaException si la prioridad no es válida");

        verify(dao, never()).updatePrioridad(anyLong(), anyString());
        verifyNoInteractions(notificacionService);
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
                "Debe lanzar TicketInexistenteException si el ticket no existe");

        verify(dao, times(1)).existsById(idTicketValido);
        verify(dao, never()).updatePrioridad(anyLong(), anyString());
        verifyNoInteractions(notificacionService);
    }

    // CASO 3: Caso exitoso
    @Test
    void updatePrioridad_DeberiaRetornarTicketDTO_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        ticketActualizado.setId(idTicketValido);
        ticketActualizado.setPrioridad("Importante");
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        when(dao.updatePrioridad(idTicketValido, "Importante")).thenReturn(ticketActualizado);

        // ---------- ACT ----------
        TicketDTO resultado = service.updatePrioridad(idTicketValido, prioridadValida);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("Importante", resultado.getPrioridad(), "La prioridad debe actualizarse correctamente");

        verify(dao, times(1)).findById(idTicketValido);
        verify(dao, times(1)).updatePrioridad(idTicketValido, "Importante");
        verify(notificacionService, times(1)).notifyPrioridad(idTicketValido, idUsuarioValido, "Ticket de prueba",
                "Importante");
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
                "Debe lanzar TicketInexistenteException si el ticket no existe");

        verify(dao, times(1)).existsById(idTicketValido);
        verify(dao, never()).cerrarTicket(anyLong());
        verifyNoInteractions(notificacionService);
    }

    // CASO 2: Caso exitoso
    @Test
    void cerrarTicket_DeberiaRetornarTicketDTO_CuandoElTicketExiste() throws Exception {
        // ---------- ARRANGE ----------
        ticketCerrado.setId(idTicketValido);
        ticketCerrado.setEstado("CERRADO");

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        when(dao.cerrarTicket(idTicketValido)).thenReturn(ticketCerrado);

        // ---------- ACT ----------
        TicketDTO resultado = service.cerrarTicket(idTicketValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("CERRADO", resultado.getEstado(), "El estado del ticket debe actualizarse a CERRADO");

        verify(dao, times(1)).findById(idTicketValido);
        verify(dao, times(1)).cerrarTicket(idTicketValido);
        verify(notificacionService, times(1)).notifyCierre(idTicketValido, idUsuarioValido, "Ticket de prueba");
    }

    // ============================================================
    // SECCIÓN 5: save()
    // ============================================================
    // CASO 1: Usuario inexistente
    @Test
    void save_DeberiaLanzarUsuarioInexistenteException_CuandoUsuarioNoExiste() {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        UsuarioInexistenteException ex = assertThrows(
                UsuarioInexistenteException.class,
                accion,
                "Debe lanzar UsuarioInexistenteException si el usuario no existe");

        verify(usuarioDao, times(1)).existsById(idUsuarioValido);
        verifyNoInteractions(dao, comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 2: Agente inexistente
    @Test
    void save_DeberiaLanzarAgenteInexistenteException_CuandoAgenteNoExiste() {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        AgenteInexistenteException ex = assertThrows(
                AgenteInexistenteException.class,
                accion,
                "Debe lanzar AgenteInexistenteException si el agente no existe");

        verify(usuarioDao, times(1)).existsById(idUsuarioValido);
        verify(usuarioDao, times(1)).existsById(agenteValido);
        verifyNoInteractions(dao, comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 3: Categoría inexistente
    @Test
    void save_DeberiaLanzarCategoriaInexistenteException_CuandoCategoriaNoExiste() {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        ticketDTO.setCategoriaId(1L);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(true);
        when(categoriaDao.existsById(1L)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        CategoriaInexistenteException ex = assertThrows(
                CategoriaInexistenteException.class,
                accion,
                "Debe lanzar CategoriaInexistenteException si la categoría no existe");

        verify(categoriaDao, times(1)).existsById(1L);
        verifyNoInteractions(dao, comentarioDao, notificacionService);
    }

    // CASO 4: Prioridad inválida
    @Test
    void save_DeberiaLanzarPrioridadInvalidaException_CuandoPrioridadEsInvalida() throws Exception {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        ticketDTO.setCategoriaId(1L);
        ticketDTO.setPrioridad("INVALIDO");
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(true);
        when(categoriaDao.existsById(1L)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        PrioridadInvalidaException ex = assertThrows(
                PrioridadInvalidaException.class,
                accion,
                "Debe lanzar PrioridadInvalidaException si la prioridad es inválida");

        verify(dao, never()).save(any(TicketDTO.class));
        verifyNoInteractions(comentarioDao, notificacionService);
    }

    // CASO 5: Estado inválido
    @Test
    void save_DeberiaLanzarEstadoInvalidoException_CuandoEstadoEsInvalido() throws Exception {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        ticketDTO.setCategoriaId(1L);
        ticketDTO.setPrioridad("Urgente");
        ticketDTO.setEstado("DESCONOCIDO");
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(true);
        when(categoriaDao.existsById(1L)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        EstadoInvalidoException ex = assertThrows(
                EstadoInvalidoException.class,
                accion,
                "Debe lanzar EstadoInvalidoException si el estado es inválido");

        verify(dao, never()).save(any(TicketDTO.class));
        verifyNoInteractions(comentarioDao, notificacionService);
    }

    // CASO 6: Título nulo
    @Test
    void save_DeberiaLanzarTituloInvalidoException_CuandoTituloEsNulo() throws Exception {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        ticketDTO.setCategoriaId(1L);
        ticketDTO.setPrioridad("Urgente");
        ticketDTO.setEstado("Pendiente");
        ticketDTO.setTitulo(null);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(true);
        when(categoriaDao.existsById(1L)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        TituloInvalidoException ex = assertThrows(
                TituloInvalidoException.class,
                accion,
                "Debe lanzar TituloInvalidoException si el título es nulo");

        verify(dao, never()).save(any(TicketDTO.class));
        verifyNoInteractions(comentarioDao, notificacionService);
    }

    // CASO 7: Descripción nula
    @Test
    void save_DeberiaLanzarDescripcionInvalidaException_CuandoDescripcionEsNula() throws Exception {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        ticketDTO.setCategoriaId(1L);
        ticketDTO.setPrioridad("Urgente");
        ticketDTO.setEstado("Pendiente");
        ticketDTO.setTitulo("Problema de red");
        ticketDTO.setDescripcion(null);
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(true);
        when(categoriaDao.existsById(1L)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.save(ticketDTO);

        // ---------- ASSERT ----------
        DescripcionInvalidaException ex = assertThrows(
                DescripcionInvalidaException.class,
                accion,
                "Debe lanzar DescripcionInvalidaException si la descripción es nula");

        verify(dao, never()).save(any(TicketDTO.class));
        verifyNoInteractions(comentarioDao, notificacionService);
    }

    // CASO 8: Caso exitoso
    @Test
    void save_DeberiaRetornarTicketDTO_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setUsuarioId(idUsuarioValido);
        ticketDTO.setAgenteId(agenteValido);
        ticketDTO.setCategoriaId(1L);
        ticketDTO.setPrioridad("Urgente");
        ticketDTO.setEstado("Pendiente");
        ticketDTO.setTitulo("Problema de red");
        ticketDTO.setDescripcion("No hay conexión a internet");
        when(usuarioDao.existsById(idUsuarioValido)).thenReturn(true);
        when(usuarioDao.existsById(agenteValido)).thenReturn(true);
        when(categoriaDao.existsById(1L)).thenReturn(true);
        when(dao.save(any(TicketDTO.class))).thenReturn(ticketDTO);

        // ---------- ACT ----------
        TicketDTO resultado = service.save(ticketDTO);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idUsuarioValido, resultado.getUsuarioId(), "El ID del usuario debe coincidir");
        assertEquals(agenteValido, resultado.getAgenteId(), "El ID del agente debe coincidir");
        assertEquals("Urgente", resultado.getPrioridad(), "La prioridad debe coincidir");
        assertEquals("Pendiente", resultado.getEstado(), "El estado debe coincidir");
        verify(dao, times(1)).save(any(TicketDTO.class));
        verifyNoInteractions(comentarioDao, notificacionService);
    }

    // ============================================================
    // SECCIÓN 6: findTickets()
    // ============================================================
    // CASO 1: Usuario no autorizado
    @Test
    void findTickets_DeberiaRetornarNull_CuandoUsuarioNoEsAdminNiPropietario() throws Exception {
        // ---------- ARRANGE ----------
        String estadoValido = "Pendiente";
        String prioridadValida = "Urgente";
        Long usuarioId = idUsuarioValido;
        Long currentUserId = 999L;
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(currentUserId));
        when(usuarioDao.isAdmin(currentUserId)).thenReturn(0);

        // ---------- ACT ----------
        List<TicketDTO> resultado = service.findTickets(estadoValido, prioridadValida, usuarioId);

        // ---------- ASSERT ----------
        assertNull(resultado, "El resultado debe ser nulo si el usuario no está autorizado");
        verifyNoInteractions(dao, comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 2: Usuario inexistente
    @Test
    void findTickets_DeberiaLanzarUsuarioInexistenteException_CuandoUsuarioNoExiste() {
        // ---------- ARRANGE ----------
        String estadoValido = "Pendiente";
        String prioridadValida = "Urgente";
        Long usuarioId = idUsuarioValido;
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(usuarioId));
        when(usuarioDao.isAdmin(usuarioId)).thenReturn(0);
        when(usuarioDao.existsById(usuarioId)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.findTickets(estadoValido, prioridadValida, usuarioId);

        // ---------- ASSERT ----------
        UsuarioInexistenteException ex = assertThrows(
                UsuarioInexistenteException.class,
                accion,
                "Debe lanzar UsuarioInexistenteException si el usuario no existe");

        verify(usuarioDao, times(1)).existsById(usuarioId);
        verifyNoInteractions(dao, comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 3: Prioridad inválida
    @Test
    void findTickets_DeberiaLanzarPrioridadInvalidaException_CuandoPrioridadEsInvalida() {
        // ---------- ARRANGE ----------
        String estadoValido = "Pendiente";
        String prioridadInvalida = "INVALIDO";
        Long usuarioId = idUsuarioValido;
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(usuarioId));
        when(usuarioDao.isAdmin(usuarioId)).thenReturn(0);
        when(usuarioDao.existsById(usuarioId)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.findTickets(estadoValido, prioridadInvalida, usuarioId);

        // ---------- ASSERT ----------
        PrioridadInvalidaException ex = assertThrows(
                PrioridadInvalidaException.class,
                accion,
                "Debe lanzar PrioridadInvalidaException si la prioridad es inválida");

        verify(dao, never()).findTickets(anyString(), anyString(), anyLong());
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 4: Estado inválido
    @Test
    void findTickets_DeberiaLanzarEstadoInvalidoException_CuandoEstadoEsInvalido() {
        // ---------- ARRANGE ----------
        String estadoInvalido = "DESCONOCIDO";
        String prioridadValida = "Urgente";
        Long usuarioId = idUsuarioValido;
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(usuarioId));
        when(usuarioDao.isAdmin(usuarioId)).thenReturn(0);
        when(usuarioDao.existsById(usuarioId)).thenReturn(true);

        // ---------- ACT ----------
        Executable accion = () -> service.findTickets(estadoInvalido, prioridadValida, usuarioId);

        // ---------- ASSERT ----------
        EstadoInvalidoException ex = assertThrows(
                EstadoInvalidoException.class,
                accion,
                "Debe lanzar EstadoInvalidoException si el estado es inválido");

        verify(dao, never()).findTickets(anyString(), anyString(), anyLong());
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 5: Caso exitoso
    @Test
    void findTickets_DeberiaRetornarListaDeTickets_CuandoParametrosSonValidos() throws Exception {
        // ---------- ARRANGE ----------
        String estadoValido = "Pendiente";
        String prioridadValida = "Urgente";
        Long usuarioId = idUsuarioValido;
        ticketActualizado.setTitulo("Ticket de prueba");
        listaTicketsValida.add(ticketActualizado);
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(usuarioId));
        when(usuarioDao.isAdmin(usuarioId)).thenReturn(0);
        when(usuarioDao.existsById(usuarioId)).thenReturn(true);
        when(dao.findTickets(estadoValido, prioridadValida, usuarioId)).thenReturn(listaTicketsValida);

        // ---------- ACT ----------
        List<TicketDTO> resultado = service.findTickets(estadoValido, prioridadValida, usuarioId);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "La lista de tickets no debe ser nula");
        assertEquals(1, resultado.size(), "Debe retornar un ticket");
        assertEquals("Ticket de prueba", resultado.get(0).getTitulo());
        verify(dao, times(1)).findTickets(estadoValido, prioridadValida, usuarioId);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // ============================================================
    // SECCIÓN 7: findById()
    // ============================================================
    // CASO 1: No autorizado (ni admin ni agente asignado ni propietario)
    @Test
    void findById_DeberiaLanzarNotAuthorizedException_CuandoUsuarioNoEstaAutorizado() {
        // ---------- ARRANGE ----------
        Long idUsuario = idUsuarioValido;
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(idUsuario));
        when(usuarioDao.isAdmin(idUsuario)).thenReturn(0);
        when(usuarioDao.isAgente(idUsuario)).thenReturn(0);
        when(usuarioDao.isTicketBelongsToUsuario(idUsuario, idTicketValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.findById(idTicketValido);

        // ---------- ASSERT ----------
        NotAuthorizedException ex = assertThrows(
                NotAuthorizedException.class,
                accion,
                "Debe lanzar NotAuthorizedException si el usuario no está autorizado");

        verify(dao, never()).findTicketById(idTicketValido);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 2: Caso exitoso (admin)
    @Test
    void findById_DeberiaRetornarTicketDTO_CuandoUsuarioEsAdmin() throws Exception {
        // ---------- ARRANGE ----------
        Long idUsuario = idUsuarioValido;
        ticketActualizado.setId(idTicketValido);
        ticketActualizado.setTitulo("Ticket de prueba");
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(idUsuario));
        when(usuarioDao.isAdmin(idUsuario)).thenReturn(1);
        when(dao.findTicketById(idTicketValido)).thenReturn(ticketActualizado);

        // ---------- ACT ----------
        TicketDTO resultado = service.findById(idTicketValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("Ticket de prueba", resultado.getTitulo(), "El título debe coincidir");
        verify(dao, times(1)).findTicketById(idTicketValido);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 3: Caso exitoso (agente asignado)
    @Test
    void findById_DeberiaRetornarTicketDTO_CuandoUsuarioEsAgenteAsignado() throws Exception {
        // ---------- ARRANGE ----------
        Long idUsuario = idUsuarioValido;
        ticketActualizado.setId(idTicketValido);
        ticketActualizado.setTitulo("Ticket de prueba");
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(idUsuario));
        when(usuarioDao.isAdmin(idUsuario)).thenReturn(0);
        when(usuarioDao.isAgente(idUsuario)).thenReturn(1);
        when(usuarioDao.isAgenteAssignedToTicket(idUsuario, idTicketValido)).thenReturn(true);
        when(dao.findTicketById(idTicketValido)).thenReturn(ticketActualizado);

        // ---------- ACT ----------
        TicketDTO resultado = service.findById(idTicketValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("Ticket de prueba", resultado.getTitulo(), "El título debe coincidir");
        verify(dao, times(1)).findTicketById(idTicketValido);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 4: Caso exitoso (propietario del ticket)
    @Test
    void findById_DeberiaRetornarTicketDTO_CuandoUsuarioEsPropietario() throws Exception {
        // ---------- ARRANGE ----------
        Long idUsuario = idUsuarioValido;
        ticketActualizado.setId(idTicketValido);
        ticketActualizado.setTitulo("Ticket de prueba");
        when(CurrentUser.getUserId()).thenReturn(String.valueOf(idUsuario));
        when(usuarioDao.isAdmin(idUsuario)).thenReturn(0);
        when(usuarioDao.isAgente(idUsuario)).thenReturn(0);
        when(usuarioDao.isTicketBelongsToUsuario(idUsuario, idTicketValido)).thenReturn(true);
        when(dao.findTicketById(idTicketValido)).thenReturn(ticketActualizado);

        // ---------- ACT ----------
        TicketDTO resultado = service.findById(idTicketValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("Ticket de prueba", resultado.getTitulo(), "El título debe coincidir");
        verify(dao, times(1)).findTicketById(idTicketValido);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // ============================================================
    // SECCIÓN 8: reasignarTicket()
    // ============================================================
    // CASO 1: Ticket inexistente
    @Test
    void reasignarTicket_DeberiaLanzarTicketInexistenteException_CuandoElTicketNoExiste() {
        // ---------- ARRANGE ----------
        when(dao.existsById(idTicketValido)).thenReturn(false);

        // ---------- ACT ----------
        Executable accion = () -> service.reasignarTicket(idTicketValido, agenteValido);

        // ---------- ASSERT ----------
        TicketInexistenteException ex = assertThrows(
                TicketInexistenteException.class,
                accion,
                "Debe lanzar TicketInexistenteException si el ticket no existe");

        verify(dao, times(1)).existsById(idTicketValido);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 2: Agente inexistente
    @Test
    void reasignarTicket_DeberiaLanzarAgenteInexistenteException_CuandoAgenteNoExiste() {
        // ---------- ARRANGE ----------
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.isAgente(agenteValido)).thenReturn(0);

        // ---------- ACT ----------
        Executable accion = () -> service.reasignarTicket(idTicketValido, agenteValido);

        // ---------- ASSERT ----------
        AgenteInexistenteException ex = assertThrows(
                AgenteInexistenteException.class,
                accion,
                "Debe lanzar AgenteInexistenteException si el agente no existe");

        verify(dao, times(1)).existsById(idTicketValido);
        verify(usuarioDao, times(1)).isAgente(agenteValido);
        verifyNoInteractions(comentarioDao, notificacionService, categoriaDao);
    }

    // CASO 3: Caso exitoso
    @Test
    void reasignarTicket_DeberiaRetornarTicketDTO_CuandoTodoEsValido() throws Exception {
        // ---------- ARRANGE ----------
        Usuario usuario = new Usuario();
        usuario.setId(agenteValido);
        usuario.setNombre("Agente Juan");
        usuario.setEmailCorporativo("juan@empresa.com");
        ticketActualizado.setId(idTicketValido);
        ticketActualizado.setAgenteId(agenteValido);
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        when(usuarioDao.isAgente(agenteValido)).thenReturn(1);
        when(usuarioDao.findById(agenteValido)).thenReturn(Optional.of(usuario));
        when(dao.reasignar(agenteValido, idTicketValido)).thenReturn(ticketActualizado);

        // ---------- ACT ----------
        TicketDTO resultado = service.reasignarTicket(idTicketValido, agenteValido);

        // ---------- ASSERT ----------
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals(agenteValido, resultado.getAgenteId(), "El ID del agente debe coincidir");
        verify(dao, times(1)).findById(idTicketValido);
        verify(usuarioDao, times(1)).findById(agenteValido);
        verify(dao, times(1)).reasignar(agenteValido, idTicketValido);
        verify(notificacionService, times(1)).notifyReasignacion(idTicketValido, idUsuarioValido, "Ticket de prueba",
                "Agente Juan", "juan@empresa.com");
        verifyNoInteractions(comentarioDao, categoriaDao);
    }

    // ============================================================
    // SECCIÓN X: tests adicionales (CP-44, CP-46, CP-48, CP-50)
    // ============================================================
    // CP-44: Solicitud inválida al cerrar el ticket (ej. ya cerrado)
    @Test
    void cerrarTicket_DeberiaTratarTicketYaCerrado_CuandoTicketYaEstaCerrado() throws Exception {
        // ARRANGE
        ticket.setEstado("CERRADO");
        ticketCerrado.setId(idTicketValido);
        ticketCerrado.setEstado("CERRADO");

        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        // el DAO.cerrarTicket devuelve el DTO con estado actualizado (mockeado)
        when(dao.cerrarTicket(idTicketValido)).thenReturn(ticketCerrado);

        // ACT
        TicketDTO resultado = service.cerrarTicket(idTicketValido);

        // ASSERT
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(idTicketValido, resultado.getId(), "El ID del ticket debe coincidir");
        assertEquals("CERRADO", resultado.getEstado(), "El estado del ticket debe ser CERRADO (resultado del DAO)");

        verify(dao, times(1)).findById(idTicketValido);
        verify(dao, times(1)).cerrarTicket(idTicketValido);
        verify(notificacionService, times(1)).notifyCierre(idTicketValido, idUsuarioValido, "Ticket de prueba");
    }

    // CP-46: Error del servidor al cerrar el ticket (DAO falla)
    @Test
    void cerrarTicket_DeberiaPropagarRuntimeException_CuandoDaoFallaEnCerrar()
            throws InsufficientSavingPermissionsException {
        // ARRANGE
        ticket.setEstado("Pendiente");
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        // cerrarTicket devuelve TicketDTO en el DAO, por eso usamos
        // when(...).thenThrow(...)
        when(dao.cerrarTicket(idTicketValido)).thenThrow(new RuntimeException("DB error"));

        // ACT & ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.cerrarTicket(idTicketValido));
        assertEquals("DB error", ex.getMessage());

        verify(dao, times(1)).cerrarTicket(idTicketValido);
        verify(notificacionService, times(1)).notifyCierre(eq(idTicketValido), eq(ticket.getUsuarioId()),
                eq(ticket.getTitulo()));
    }

    // CP-48: Datos inválidos al reasignar ticket (agenteId nulo)
    @Test
    void reasignarTicket_DeberiaLanzarAgenteInexistenteException_CuandoAgenteIdEsNulo() {
        // ARRANGE
        when(dao.existsById(idTicketValido)).thenReturn(true);
        // según la implementación validarAgenteIdExistance llama a usuarioDao.isAgente(agenteId)
        when(usuarioDao.isAgente(null)).thenReturn(0);

        // ACT
        Executable accion = () -> service.reasignarTicket(idTicketValido, null);

        // ASSERT
        AgenteInexistenteException ex = assertThrows(
                AgenteInexistenteException.class,
                accion,
                "Debe lanzar AgenteInexistenteException si el agenteId es nulo o no corresponde a un agente");

        verify(dao, times(1)).existsById(idTicketValido);
        verify(usuarioDao, times(1)).isAgente(null);
        verify(dao, never()).reasignar(anyLong(), anyLong());
        verifyNoInteractions(notificacionService);
    }

    // CP-50: Error del servidor al reasignar ticket (DAO falla)
    @Test
    void reasignarTicket_DeberiaPropagarRuntimeException_CuandoDaoFallaEnReasignar()
            throws InsufficientSavingPermissionsException {
        // ARRANGE
        when(dao.existsById(idTicketValido)).thenReturn(true);
        when(usuarioDao.isAgente(agenteValido)).thenReturn(1); // indica que el id corresponde a un agente
        when(dao.findById(idTicketValido)).thenReturn(Optional.of(ticket));
        Usuario usuario = new Usuario();
        usuario.setId(agenteValido);
        usuario.setNombre("Agente Test");
        usuario.setEmailCorporativo("agente@test.com");
        when(usuarioDao.findById(agenteValido)).thenReturn(Optional.of(usuario));
        // reasignar devuelve TicketDTO en el DAO, por eso usamos
        // when(...).thenThrow(...)
        when(dao.reasignar(agenteValido, idTicketValido)).thenThrow(new RuntimeException("DB failure"));

        // ACT & ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.reasignarTicket(idTicketValido, agenteValido));
        assertEquals("DB failure", ex.getMessage());

        verify(dao, times(1)).reasignar(agenteValido, idTicketValido);
        verify(notificacionService, times(1)).notifyReasignacion(eq(idTicketValido), eq(ticket.getUsuarioId()),
                eq(ticket.getTitulo()), eq(usuario.getNombre()), eq(usuario.getEmailCorporativo()));
    }
}
