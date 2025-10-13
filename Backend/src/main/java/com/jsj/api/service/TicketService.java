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
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.entity.dao.ComentarioDAO;
import com.jsj.api.entity.dao.TicketDAO;
import com.jsj.api.entity.dao.UsuarioDAO;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.exception.AgenteInexistenteException;
import com.jsj.api.exception.TicketInexistenteException;
import com.jsj.api.repository.TicketRepository;
import com.jsj.api.security.CurrentUser;
import java.time.LocalDate;
import java.util.List;
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

    public TicketService(UsuarioDAO usuarioDao, CategoriaDAO categoriaDao, ComentarioDAO comentarioDao, TicketDAO dao) {
        super(dao);
        this.usuarioDao = usuarioDao;
        this.categoriaDao = categoriaDao;
        this.comentarioDao = comentarioDao;
    }

    public TicketDTO save(TicketDTO dto) throws CategoriaInexistenteException, UsuarioInexistenteException, PrioridadInvalidaException, EstadoInvalidoException {
        Long usuarioId = dto.getUsuarioId();
        validarUsuario(usuarioId);

        Long categoriaId = dto.getCategoriaId();
        validarCategoria(categoriaId);

        dto.setPrioridad(validarPrioridad(dto.getPrioridad()));

        dto.setEstado(validarEstado(dto.getEstado()));

        return dao.save(dto);
    }

    public List<TicketDTO> findTickets(String estado, String prioridad, Long usuarioId) throws UsuarioInexistenteException, PrioridadInvalidaException, EstadoInvalidoException {
        Long currentUserId = Long.parseLong(CurrentUser.getUserId());

        if (!usuarioDao.isAdmin(currentUserId)) {
            if (currentUserId != usuarioId) {
                return null;
            }
        }

        validarUsuario(usuarioId);

        estado = validarEstado(estado);

        prioridad = validarPrioridad(prioridad);

        return dao.findTickets(estado, prioridad, usuarioId);
    }

    public TicketDTO findById(Long idTicket) throws NotAuthorizedException {
        Long idUsuario = Long.parseLong(CurrentUser.getUserId());

        if (!usuarioDao.isAdmin(idUsuario)) {
            if (usuarioDao.isAgente(idUsuario)) {
                if (!usuarioDao.isAgenteAssignedToTicket(idUsuario, idTicket)) {
                    throw new NotAuthorizedException("Not authorized");
                }
            } else if (!usuarioDao.isTicketBelongsToUsuario(idUsuario, idTicket)) {
                throw new NotAuthorizedException("Not authorized");

            }
        }

        return dao.findTicketById(idTicket);
    }

    public List<ComentarioDTO> findComentariosByTicketId(Long idTicket) throws TicketInexistenteException {
        validarTicket(idTicket);
        return comentarioDao.findComentariosByTicketId(idTicket);
    }

    public ComentarioDTO addComentario(Long idTicket, ComentarioDTO comentarioDTO) throws TicketInexistenteException {
        validarTicket(idTicket);
        return comentarioDao.save(comentarioDTO);
    }

    public TicketDTO updatePrioridad(Long idTicket, String prioridad) throws PrioridadInvalidaException, TicketInexistenteException {
        prioridad = validarPrioridad(prioridad);
        validarTicket(idTicket);
        return dao.updatePrioridad(idTicket, prioridad);
    }

    public TicketDTO cerrarTicket(Long idTicket) throws TicketInexistenteException {
        validarTicket(idTicket);
        return dao.cerrarTicket(idTicket);
    }

    public TicketDTO reasignarTicket(Long idTicket, Long agenteId) throws TicketInexistenteException, AgenteInexistenteException {
        validarTicket(idTicket);
        validarAgente(agenteId);
        return dao.reasignarTicket(idTicket, usuarioDao.findById(agenteId).get());
    }

    public List<TicketDTO> findTicketsFiltrados(String estado, String prioridad, Long agenteId, LocalDate fecha) throws PrioridadInvalidaException, EstadoInvalidoException, AgenteInexistenteException {
        String prioridadVer = validarPrioridad(prioridad);
        String estadoVer = validarEstado(estado);
        validarAgente(agenteId);

        return dao.findTicketsFiltrados(estadoVer, prioridadVer, agenteId, fecha);
    }

    private String validarPrioridad(String prioridad) throws PrioridadInvalidaException {
        if (!TicketConstants.isPrioridadIgnoringCaps(prioridad)) {
            throw new PrioridadInvalidaException(prioridad);
        }
        return TicketConstants.getPrioridad(prioridad);
    }

    private String validarEstado(String estado) throws EstadoInvalidoException {
        if (!TicketConstants.isEstadoIgnoringCaps(estado)) {
            throw new EstadoInvalidoException(estado);
        }
        return TicketConstants.getEstado(estado);
    }

    private void validarAgente(Long agenteId) throws AgenteInexistenteException {
        if (!usuarioDao.isAgente(agenteId)) {
            throw new AgenteInexistenteException(agenteId);
        }
    }

    private void validarTicket(Long idTicket) throws TicketInexistenteException {
        if (!dao.existsById(idTicket)) {
            throw new TicketInexistenteException(idTicket);
        }
    }

    private void validarUsuario(Long usuarioId) throws UsuarioInexistenteException {
        if (usuarioId == null || !usuarioDao.existsById(usuarioId)) {
            throw new UsuarioInexistenteException(usuarioId);
        }
    }

    private void validarCategoria(Long categoriaId) throws CategoriaInexistenteException {
        if (categoriaId == null || !categoriaDao.existsById(categoriaId)) {
            throw new CategoriaInexistenteException(categoriaId);
        }
    }

}
