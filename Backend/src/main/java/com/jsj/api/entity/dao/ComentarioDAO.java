/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Comentario;
import com.jsj.api.entity.dto.ComentarioDTO;
import com.jsj.api.entity.filter.ComentarioFilter;
import com.jsj.api.entity.mapper.ComentarioMapper;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.repository.ComentarioRepository;
import com.jsj.api.security.CurrentUser;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class ComentarioDAO extends BaseDAO<Comentario, Long, ComentarioDTO, ComentarioMapper, ComentarioFilter, ComentarioRepository> {

    public ComentarioDAO(ComentarioMapper mapper, ComentarioFilter filter, ComentarioRepository repo) {
        super(mapper, filter, repo);
    }

    public List<ComentarioDTO> findComentariosByTicketId(Long idTicket) {
        return repo.findByTicketIdOrderByFechaCreacionAsc(idTicket)
                .stream()
                .map(mapper::toDTO)
                .map(dto -> filter.filterDTO(dto))
                .toList();
    }

    public ComentarioDTO save(ComentarioDTO dto) throws InsufficientSavingPermissionsException {
        Comentario entity = repo.save(filter.filterEntityToSave(mapper.toEntity(dto)));

        return filter.filterDTO(mapper.toDTO(entity));
    }

}
