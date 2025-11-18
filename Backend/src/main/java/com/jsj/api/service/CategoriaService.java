package com.jsj.api.service;

import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Servicio para lógica de negocio simple sobre categorías (delegación al DAO).
 */
@Service
public class CategoriaService extends BaseService<com.jsj.api.entity.Categoria, Long, CategoriaDTO, CategoriaDAO> {

    private final CategoriaDAO dao;

    public CategoriaService(CategoriaDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public CategoriaDTO save(CategoriaDTO dto) throws InsufficientSavingPermissionsException {
        return dao.save(dto);
    }

    public List<CategoriaDTO> findAll() {
        return dao.findAllDTO();
    }

    public CategoriaDTO findById(Long id) {
        return dao.findCategoriaById(id);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) throws InsufficientSavingPermissionsException, ImmutableFieldException {
        return dao.update(id, dto);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}