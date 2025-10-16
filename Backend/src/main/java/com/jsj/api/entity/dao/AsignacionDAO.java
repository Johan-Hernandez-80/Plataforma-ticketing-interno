/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Asignacion;
import com.jsj.api.entity.dto.AsignacionDTO;
import com.jsj.api.entity.filter.AsignacionFilter;
import com.jsj.api.entity.mapper.AsignacionMapper;
import com.jsj.api.repository.AsignacionRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class AsignacionDAO extends BaseDAO<Asignacion, Long, AsignacionDTO, AsignacionMapper, AsignacionFilter, AsignacionRepository> {

    public AsignacionDAO(AsignacionMapper mapper, AsignacionFilter filter, AsignacionRepository repo) {
        super(mapper, filter, repo);
    }
    
    public Optional<Asignacion> findByAgenteIdAndTicketId(Long agenteId, Long ticketId) {
        return repo.findByAgenteIdAndTicketId(agenteId, ticketId);
    }

    public boolean existsByAgenteIdAndTicketId(Long agenteId, Long idTicket) {
        return false;
    }

}
