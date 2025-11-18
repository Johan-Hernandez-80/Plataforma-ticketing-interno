package com.jsj.api.entity.dao;

import com.jsj.api.entity.Categoria;
import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.entity.filter.CategoriaFilter;
import com.jsj.api.entity.mapper.CategoriaMapper;
import com.jsj.api.exception.CategoriaExistenteException;
import com.jsj.api.exception.CategoriaNoEncontradaException;
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * DAO centrado en DTOs y uso de filter/mapper
 */
@Repository
public class CategoriaDAO extends BaseDAO<Categoria, Long, CategoriaDTO, CategoriaMapper, CategoriaFilter, CategoriaRepository> {

    public CategoriaDAO(CategoriaMapper mapper, CategoriaFilter filter, CategoriaRepository repo) {
        super(mapper, filter, repo);
    }

    public CategoriaDTO save(CategoriaDTO dto) throws InsufficientSavingPermissionsException {
        // Validación: nombre único
        if (dto.getNombre() != null && repo.existsByNombre(dto.getNombre())) {
            throw new CategoriaExistenteException("Ya existe una categoría con ese nombre");
        }

        dto.setId(null);
        Categoria entity = mapper.toEntity(dto);
        entity = filter.filterEntityToSave(entity);
        Categoria saved = repo.save(entity);
        return filter.filterDTO(mapper.toDTO(saved));
    }

    public List<CategoriaDTO> findAllDTO() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .map(filter::filterDTO)
                .toList();
    }

    public CategoriaDTO findCategoriaById(Long id) {
        return repo.findById(id)
                .map(mapper::toDTO)
                .map(filter::filterDTO)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada con id: " + id));
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) throws InsufficientSavingPermissionsException, ImmutableFieldException {
        Categoria entity = repo.findById(id).orElseThrow(() -> new CategoriaNoEncontradaException("Categoría no encontrada con id: " + id));

        // Si se intenta cambiar el nombre, validar unicidad
        if (dto.getNombre() != null && !dto.getNombre().equals(entity.getNombre())) {
            if (repo.existsByNombre(dto.getNombre())) {
                throw new CategoriaExistenteException("Ya existe una categoría con ese nombre");
            }
        }

        mapper.updateEntityFromDTO(dto, entity);
        entity = filter.filterEntityToUpdate(entity, dto);
        Categoria saved = repo.save(entity);
        return filter.filterDTO(mapper.toDTO(saved));
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new CategoriaNoEncontradaException("Categoría no encontrada con id: " + id);
        }
        repo.deleteById(id);
    }
}