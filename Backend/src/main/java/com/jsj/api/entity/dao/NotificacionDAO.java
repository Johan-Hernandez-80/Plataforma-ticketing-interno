/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.entity.dao;

import com.jsj.api.entity.Notificacion;
import com.jsj.api.entity.dto.NotificacionDTO;
import com.jsj.api.entity.filter.BaseFilter;
import com.jsj.api.entity.filter.NotificacionFilter;
import com.jsj.api.entity.mapper.BaseMapper;
import com.jsj.api.entity.mapper.NotificacionMapper;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.repository.NotificacionRepository;
import com.jsj.api.security.CurrentUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan José Molano Franco
 */
@Repository
public class NotificacionDAO extends BaseDAO<Notificacion, Long, NotificacionDTO, NotificacionMapper, NotificacionFilter, NotificacionRepository> {

    public NotificacionDAO(NotificacionMapper mapper, NotificacionFilter filter, NotificacionRepository repo) {
        super(mapper, filter, repo);
    }

    public List<NotificacionDTO> getNotificacionesByIdUsuario(Long idUsuario) {
        return repo.findByUsuarioId(idUsuario)
                .stream()
                .map(mapper::toDTO)
                .map(filter::filterDTO)
                .toList();
    }

    public NotificacionDTO save(NotificacionDTO dto) throws InsufficientSavingPermissionsException {
        return filter.filterDTO(mapper.toDTO(repo.save(filter.filterEntityToSave(mapper.toEntity(dto)))));
    }

}
