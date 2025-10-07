/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.filter.TicketFilter;
import com.jsj.api.entity.mapper.TicketMapper;
import com.jsj.api.repository.TicketRepository;
import com.jsj.api.security.CurrentUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class TicketDAO extends BaseDAO<Ticket, Long, TicketDTO, TicketMapper, TicketFilter, TicketRepository> {

    public TicketDAO(TicketMapper mapper, TicketFilter filter, TicketRepository repo) {
        super(mapper, filter, repo);
    }

    public TicketDTO save(TicketDTO dto) {
        Set<String> perms = CurrentUser.getPermissions();

        Ticket entity = mapper.toEntity(dto);

        filter.filterEntity(entity, perms);

        return filter.filterDTO(mapper.toDTO(repo.save(entity)), perms);
    }

    public List<TicketDTO> findTickets(String estado, String prioridad, Long usuarioId) {
        Set<String> perms = CurrentUser.getPermissions();

        return repo.findTickets(estado, prioridad, usuarioId)
                .stream()
                .map(mapper::toDTO)
                .map(dto -> filter.filterDTO(dto, perms))
                .toList();
    }

    public TicketDTO findTicketById(Long idTicket) {
        Set<String> perms = CurrentUser.getPermissions();

        return findById(idTicket)
                .map(mapper::toDTO)
                .map(dto -> filter.filterDTO(dto, perms))
                .orElse(null);
    }

}
