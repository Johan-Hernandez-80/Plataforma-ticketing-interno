/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.constants.TicketConstants;
import com.jsj.api.entity.Ticket;
import com.jsj.api.entity.Usuario;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.dto.TicketDTO;
import com.jsj.api.entity.filter.TicketFilter;
import com.jsj.api.entity.mapper.TicketMapper;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.repository.TicketRepository;
import com.jsj.api.security.CurrentUser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class TicketDAO extends BaseDAO<Ticket, Long, TicketDTO, TicketMapper, TicketFilter, TicketRepository> {

  public TicketDAO(TicketMapper mapper, TicketFilter filter, TicketRepository repo) {
    super(mapper, filter, repo);
  }

  public TicketDTO save(TicketDTO dto) throws InsufficientSavingPermissionsException {
    dto.setId(null);
    Ticket entity = mapper.toEntity(dto);

    filter.filterEntityToSave(entity);

    return filter.filterDTO(mapper.toDTO(repo.save(entity)));
  }

  public List<TicketDTO> findTickets(String estado, String prioridad, Long usuarioId) {
    return repo.findTickets(estado, prioridad, usuarioId)
        .stream()
        .map(mapper::toDTO)
        .map(dto -> filter.filterDTO(dto))
        .toList();
  }

  public TicketDTO findTicketById(Long idTicket) {
    return findById(idTicket)
        .map(mapper::toDTO)
        .map(dto -> filter.filterDTO(dto))
        .orElse(null);
  }

  public TicketDTO updatePrioridad(Long idTicket, String prioridad) {
    Ticket ticket = repo.findById(idTicket).get();
    ticket.setPrioridad(prioridad);
    return filter.filterDTO(mapper.toDTO(repo.save(ticket)));

  }

  public TicketDTO cerrarTicket(Long idTicket) {
    Ticket ticket = repo.findById(idTicket).get();
    ticket.setEstado(TicketConstants.getEstado("Cerrado"));
    ticket.setFechaCierre(LocalDateTime.now());
    return filter.filterDTO(mapper.toDTO(repo.save(ticket)));
  }

  public TicketDTO reasignarTicket(Long idTicket, Usuario agente) {
    Ticket ticket = repo.findById(idTicket).get();
    ticket.setUsuarioId(agente.getId());
    return filter.filterDTO(mapper.toDTO(repo.save(ticket)));
  }

  public List<TicketDTO> findTicketsFiltrados(String estadoVer, String prioridadVer, Long agenteId, LocalDate fecha) {
    return repo.findTickets(estadoVer, prioridadVer, agenteId, fecha)
        .stream()
        .map(mapper::toDTO)
        .map(dto -> filter.filterDTO(dto))
        .toList();
  }

  public TicketDTO reasignar(Long agenteId, Long idTicket) {
    Ticket ticket = repo.findById(idTicket).get();
    ticket.setAgenteId(agenteId);
    return filter.filterDTO(mapper.toDTO(repo.save(ticket)));
  }

  public long countTicketsAbiertos() {
    return repo.countTicketsAbiertos();
  }

  public long countTicketsCerrados() {
    return repo.countTicketsCerrados();
  }

    public List<TicketDTO> findAllTickets() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public long countTicketsTotales() {
        return repo.countTicketsTotales();
    }

}
