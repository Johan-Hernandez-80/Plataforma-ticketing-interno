/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.exception.NotAuthorizedException;
import com.jsj.api.exception.PrioridadInvalidaException;
import com.jsj.api.exception.EstadoInvalidoException;
import com.jsj.api.constants.TicketConstants;
import com.jsj.api.exception.CategoriaInexistenteException;
import com.jsj.api.exception.UsuarioInexistenteException;
import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dto.PrioridadDTO;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.filter.UsuarioFilter;
import com.jsj.api.exception.AgenteInexistenteException;
import com.jsj.api.exception.AsignacionInexistenteException;
import com.jsj.api.exception.CampoInvalidoException;
import com.jsj.api.exception.DescripcionInvalidaException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.exception.TicketInexistenteException;
import com.jsj.api.exception.TituloInvalidoException;
import com.jsj.api.repository.TicketRepository;
import com.jsj.api.security.CurrentUser;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan José Molano Franco
 */
@Service
public class TicketService extends BaseService<Ticket, Long, TicketDTO, TicketDAO> {

    private final UsuarioDAO usuarioDao;
    private final CategoriaDAO categoriaDao;
    private final ComentarioDAO comentarioDao;
    private static final Logger log = LoggerFactory.getLogger(UsuarioFilter.class);

    public TicketService(UsuarioDAO usuarioDao, CategoriaDAO categoriaDao, ComentarioDAO comentarioDao, TicketDAO dao) {
        super(dao);
        this.usuarioDao = usuarioDao;
        this.categoriaDao = categoriaDao;
        this.comentarioDao = comentarioDao;
    }

    public TicketDTO save(TicketDTO dto) throws CategoriaInexistenteException,
            UsuarioInexistenteException, PrioridadInvalidaException,
            EstadoInvalidoException, InsufficientSavingPermissionsException,
            TituloInvalidoException, DescripcionInvalidaException, AgenteInexistenteException {

        validarUsuarioIdSave(dto.getUsuarioId());

        validarAgenteIdSave(dto.getAgenteId());

        validarCategoriaIdSave(dto.getCategoriaId());

        dto.setPrioridad(validarPrioridadSave(dto.getPrioridad()));

        dto.setEstado(validarEstadoSave(dto.getEstado()));

        validarTituloSave(dto.getTitulo());

        validarDescripcionSave(dto.getDescripcion());

        return dao.save(dto);
    }

    public List<TicketDTO> findTickets(String estado, String prioridad, Long usuarioId)
            throws UsuarioInexistenteException, PrioridadInvalidaException,
            EstadoInvalidoException {

        Long currentUserId = Long.parseLong(CurrentUser.getUserId());

        if (usuarioDao.isAdmin(currentUserId) == 0) {
            if (currentUserId != usuarioId) {
                return null;
            }
        }

        validarUsuarioIdSave(usuarioId);

        estado = validarEstadoSave(estado);

        prioridad = validarPrioridadSave(prioridad);

        return dao.findTickets(estado, prioridad, usuarioId);
    }

    public TicketDTO findById(Long idTicket) throws NotAuthorizedException {
        Long idUsuario = Long.parseLong(CurrentUser.getUserId());

        if (usuarioDao.isAdmin(idUsuario) == 0) {
            if (usuarioDao.isAgente(idUsuario) == 1) {
                if (!usuarioDao.isAgenteAssignedToTicket(idUsuario, idTicket)) {
                    throw new NotAuthorizedException("Not authorized");
                }
            } else if (!usuarioDao.isTicketBelongsToUsuario(idUsuario, idTicket)) {
                throw new NotAuthorizedException("Not authorized");

            }
        }

        return dao.findTicketById(idTicket);
    }

    public List<ComentarioDTO> findComentariosByTicketId(Long idTicket)
            throws TicketInexistenteException {

        validarTicketIdExistance(idTicket);
        return comentarioDao.findComentariosByTicketId(idTicket);
    }

    public ComentarioDTO addComentario(Long idTicket, ComentarioDTO dto)
            throws TicketInexistenteException, UsuarioInexistenteException,
            CampoInvalidoException, InsufficientSavingPermissionsException {

        validarTicketIdExistance(idTicket);
        validarUsuarioIdExistance(dto.getUsuarioId());
        validarComentarioSave(dto.getComentario());
        dto.setFechaCreacion(null);
        dto.setTicketId(idTicket);
        dto.setId(null);
        return comentarioDao.save(dto);
    }

    public TicketDTO updatePrioridad(Long idTicket, PrioridadDTO prioridadDTO)
            throws PrioridadInvalidaException, TicketInexistenteException {

        String prioridad = validarPrioridadSave(prioridadDTO.getPrioridad());
        validarTicketIdExistance(idTicket);
        return dao.updatePrioridad(idTicket, prioridad);
    }

    public TicketDTO cerrarTicket(Long idTicket)
            throws TicketInexistenteException {

        validarTicketIdExistance(idTicket);
        return dao.cerrarTicket(idTicket);
    }

    public TicketDTO reasignarTicket(Long idTicket, Long agenteId)
            throws TicketInexistenteException, AgenteInexistenteException,
            AsignacionInexistenteException {

        // validarTicketIdExistance(idTicket);
        // validarAgenteIdExistance(agenteId);
        // validarAsignacionExistance(agenteId, idTicket);
        // asignacionDao.reasignar(agenteId, idTicket);
        // return
        return null;
    }

    public List<TicketDTO> findTicketsFiltrados(String estado, String prioridad,
            Long agenteId, LocalDate fecha) throws PrioridadInvalidaException,
            EstadoInvalidoException, AgenteInexistenteException {

        String prioridadVer = validarPrioridadSave(prioridad);
        String estadoVer = validarEstadoSave(estado);
        validarAgenteIdExistance(agenteId);

        return dao.findTicketsFiltrados(estadoVer, prioridadVer, agenteId, fecha);
    }

    private String validarPrioridadSave(String prioridad)
            throws PrioridadInvalidaException {
        if (prioridad == null) {
            return null;
        }

        if (!TicketConstants.isPrioridadIgnoringCaps(prioridad)) {
            log.info("Prioridad: {}", prioridad);
            throw new PrioridadInvalidaException(String.format("La prioridad %s no es válida", prioridad));
        }
        return TicketConstants.getPrioridad(prioridad);
    }

    private String validarEstadoSave(String estado) throws EstadoInvalidoException {
        if (estado == null) {
            return null;
        }
        if (!TicketConstants.isEstadoIgnoringCaps(estado)) {
            throw new EstadoInvalidoException(String.format("El estado %s no es válido", estado));
        }
        return TicketConstants.getEstado(estado);
    }

    private void validarAgenteIdExistance(Long agenteId) throws AgenteInexistenteException {
        if (usuarioDao.isAgente(agenteId) == 0) {
            throw new AgenteInexistenteException(String.format("El agente con id %s no existe", agenteId));
        }
    }

    private void validarTicketIdExistance(Long idTicket) throws TicketInexistenteException {
        if (!dao.existsById(idTicket)) {
            throw new TicketInexistenteException(String.format("El ticket con id %s no existe", idTicket));
        }
    }

    private void validarUsuarioIdSave(Long usuarioId) throws UsuarioInexistenteException {
        if (usuarioId == null || !usuarioDao.existsById(usuarioId)) {
            throw new UsuarioInexistenteException(String.format("El usuario con id %s no existe", usuarioId));
        }
    }

    private void validarCategoriaIdSave(Long categoriaId) throws CategoriaInexistenteException {
        if (categoriaId == null || !categoriaDao.existsById(categoriaId)) {
            throw new CategoriaInexistenteException(String.format("La categoría con id %s no existe", categoriaId));
        }
    }

    private void validarTituloSave(String titulo) throws TituloInvalidoException {
        if (titulo == null) {
            throw new TituloInvalidoException("El titulo no puede ser nulo");
        }
        if (titulo.length() > 255) {
            throw new TituloInvalidoException("El titulo no puede superar los 255 caracteres");
        }

    }

    private void validarDescripcionSave(String descripcion) throws DescripcionInvalidaException {
        if (descripcion == null) {
            throw new DescripcionInvalidaException("La descripción no puede ser nula");
        }
        if (descripcion.length() > 1000) {
            throw new DescripcionInvalidaException("La descripción no puede superar los 255 caracteres");
        }
    }

    private void validarUsuarioIdExistance(Long usuarioId) throws UsuarioInexistenteException {
        if (!usuarioDao.existsById(usuarioId)) {
            throw new UsuarioInexistenteException(String.format("El usuario con id %s no existe", usuarioId));
        }
    }

    private void validarComentarioSave(String comentario) throws CampoInvalidoException {
        if (comentario == null) {
            throw new CampoInvalidoException("El comentario es nulo");
        }
        if (comentario.length() > 1000) {
            throw new CampoInvalidoException("El comentario no puede tener más de 1000 caracteres");
        }
    }

    private void validarAgenteIdSave(Long agenteId) throws AgenteInexistenteException {
        if (agenteId == null || !usuarioDao.existsById(agenteId)) {
            throw new AgenteInexistenteException(String.format("El usuario con id %s no existe", agenteId));
        }
    }

}
