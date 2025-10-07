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
import com.jsj.api.repository.TicketRepository;
import com.jsj.api.security.CurrentUser;
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

    public TicketDTO save(TicketDTO dto) throws CategoriaInexistenteException, UsuarioInexistenteException {
        Long usuarioId = dto.getUsuarioId();
        if (usuarioId == null || !usuarioDao.existsById(usuarioId)) {
            throw new UsuarioInexistenteException(usuarioId);
        }

        Long categoriaId = dto.getCategoriaId();
        if (categoriaId == null || !categoriaDao.existsById(categoriaId)) {
            throw new CategoriaInexistenteException(categoriaId);
        }

        return dao.save(dto);
    }

    public List<TicketDTO> findTickets(String estado, String prioridad, Long usuarioId) throws UsuarioInexistenteException, PrioridadInvalidaException, EstadoInvalidoException {
        Long currentUserId = Long.parseLong(CurrentUser.getUserId());

        if (!usuarioDao.isAdmin(currentUserId)) {
            if (currentUserId != usuarioId) {
                return null;
            }
        }

        if (usuarioId != null && !usuarioDao.existsById(usuarioId)) {
            throw new UsuarioInexistenteException(usuarioId);
        }

        if (estado != null && !TicketConstants.ESTADOS_VALIDOS.contains(estado)) {
            throw new EstadoInvalidoException(estado);
        }

        if (prioridad != null && !TicketConstants.PRIORIDADES_VALIDAS.contains(prioridad)) {
            throw new PrioridadInvalidaException(prioridad);
        }

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

    public List<ComentarioDTO> findComentariosByTicketId(Long idTicket) {
        if (!dao.existsById(idTicket)) {
            return null;
        }
        return comentarioDao.findComentariosByTicketId(idTicket);
    }

    public ComentarioDTO addComentario(Long idTicket, ComentarioDTO comentarioDTO) {
        if (!dao.existsById(idTicket)) {
            return null;
        }
        return comentarioDao.save(comentarioDTO);
    }

}
