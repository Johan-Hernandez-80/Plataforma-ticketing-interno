package com.jsj.api.service;

import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.entity.dao.CategoriaDAO;
import com.jsj.api.exception.CategoriaExistenteException;
import com.jsj.api.exception.CategoriaNoEncontradaException;
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaDAO dao;

    @InjectMocks
    private CategoriaService service;

    private CategoriaDTO dtoInput;
    private CategoriaDTO dtoSaved;

    @BeforeEach
    void setUp() {
        dtoInput = new CategoriaDTO();
        dtoInput.setNombre("Electrónica");
        dtoInput.setDescripcion("Dispositivos electrónicos");

        dtoSaved = new CategoriaDTO();
        dtoSaved.setId(1L);
        dtoSaved.setNombre(dtoInput.getNombre());
        dtoSaved.setDescripcion(dtoInput.getDescripcion());
    }

    // save ----------------------------------------------------------------
    @Test
    void save_DeberiaRetornarCategoriaDTO_CuandoTodoEsValido() throws Exception {
        when(dao.save(any(CategoriaDTO.class))).thenReturn(dtoSaved);

        CategoriaDTO result = service.save(dtoInput);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Electrónica", result.getNombre());
        verify(dao, times(1)).save(any(CategoriaDTO.class));
    }

    @Test
    void save_DeberiaLanzarCategoriaExistenteException_CuandoNombreExiste() throws Exception {
        when(dao.save(any(CategoriaDTO.class))).thenThrow(new CategoriaExistenteException("Ya existe"));

        CategoriaExistenteException ex = assertThrows(CategoriaExistenteException.class, () -> service.save(dtoInput));
        assertEquals("Ya existe", ex.getMessage());
        verify(dao, times(1)).save(any(CategoriaDTO.class));
    }

    @Test
    void save_DeberiaLanzarInsufficientSavingPermissionsException_CuandoNoTienePermisos() throws Exception {
        when(dao.save(any(CategoriaDTO.class))).thenThrow(new InsufficientSavingPermissionsException("No permisos"));

        InsufficientSavingPermissionsException ex = assertThrows(InsufficientSavingPermissionsException.class, () -> service.save(dtoInput));
        assertEquals("No permisos", ex.getMessage());
        verify(dao, times(1)).save(any(CategoriaDTO.class));
    }

    // findAll -------------------------------------------------------------
    @Test
    void findAll_DeberiaRetornarListaDeCategorias_CuandoExisten() {
        when(dao.findAllDTO()).thenReturn(List.of(dtoSaved));

        var list = service.findAll();
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("Electrónica", list.get(0).getNombre());
        verify(dao, times(1)).findAllDTO();
    }

    // findById ------------------------------------------------------------
    @Test
    void findById_DeberiaRetornarCategoria_CuandoExiste() {
        when(dao.findCategoriaById(1L)).thenReturn(dtoSaved);

        var result = service.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(dao, times(1)).findCategoriaById(1L);
    }

    @Test
    void findById_DeberiaLanzarCategoriaNoEncontradaException_CuandoNoExiste() {
        when(dao.findCategoriaById(99L)).thenThrow(new CategoriaNoEncontradaException("No encontrada"));

        CategoriaNoEncontradaException ex = assertThrows(CategoriaNoEncontradaException.class, () -> service.findById(99L));
        assertEquals("No encontrada", ex.getMessage());
        verify(dao, times(1)).findCategoriaById(99L);
    }

    // update --------------------------------------------------------------
    @Test
    void update_DeberiaRetornarCategoriaActualizada_CuandoTodoEsValido() throws Exception {
        CategoriaDTO updateDto = new CategoriaDTO();
        updateDto.setNombre("Electrónica Actualizada");
        CategoriaDTO updated = new CategoriaDTO();
        updated.setId(1L);
        updated.setNombre(updateDto.getNombre());

        when(dao.update(eq(1L), any(CategoriaDTO.class))).thenReturn(updated);

        var result = service.update(1L, updateDto);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Electrónica Actualizada", result.getNombre());
        verify(dao, times(1)).update(eq(1L), any(CategoriaDTO.class));
    }

    @Test
    void update_DeberiaLanzarCategoriaNoEncontradaException_CuandoNoExiste() throws Exception {
        when(dao.update(eq(99L), any(CategoriaDTO.class))).thenThrow(new CategoriaNoEncontradaException("No encontrada"));

        CategoriaNoEncontradaException ex = assertThrows(CategoriaNoEncontradaException.class, () -> service.update(99L, dtoInput));
        assertEquals("No encontrada", ex.getMessage());
        verify(dao, times(1)).update(eq(99L), any(CategoriaDTO.class));
    }

    @Test
    void update_DeberiaLanzarCategoriaExistenteException_CuandoNombreEnUso() throws Exception {
        when(dao.update(eq(1L), any(CategoriaDTO.class))).thenThrow(new CategoriaExistenteException("Nombre en uso"));

        CategoriaExistenteException ex = assertThrows(CategoriaExistenteException.class, () -> service.update(1L, dtoInput));
        assertEquals("Nombre en uso", ex.getMessage());
        verify(dao, times(1)).update(eq(1L), any(CategoriaDTO.class));
    }

    @Test
    void update_DeberiaLanzarImmutableFieldException_CuandoCampoInmutableSeModifica() throws Exception {
        when(dao.update(eq(1L), any(CategoriaDTO.class))).thenThrow(new ImmutableFieldException("Id inmutable"));

        ImmutableFieldException ex = assertThrows(ImmutableFieldException.class, () -> service.update(1L, dtoInput));
        assertEquals("Id inmutable", ex.getMessage());
        verify(dao, times(1)).update(eq(1L), any(CategoriaDTO.class));
    }

    // delete --------------------------------------------------------------
    @Test
    void deleteById_DeberiaInvocarDaoDelete_CuandoExiste() {
        doNothing().when(dao).deleteById(1L);

        service.deleteById(1L);

        verify(dao, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_DeberiaLanzarCategoriaNoEncontradaException_CuandoNoExiste() {
        doThrow(new CategoriaNoEncontradaException("No encontrada")).when(dao).deleteById(99L);

        CategoriaNoEncontradaException ex = assertThrows(CategoriaNoEncontradaException.class, () -> service.deleteById(99L));
        assertEquals("No encontrada", ex.getMessage());
        verify(dao, times(1)).deleteById(99L);
    }
}