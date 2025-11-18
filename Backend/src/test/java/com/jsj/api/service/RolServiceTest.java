/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsj.api.service;

import com.jsj.api.entity.Rol;
import com.jsj.api.entity.dao.RolDAO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Juan José Molano Franco
 */
@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolDAO dao;

    @InjectMocks
    private RolService service;

    private Rol rolUsuario;
    private Rol rolAgente;
    private Long validRolId;
    private Long invalidRolId;

    @BeforeEach
    void setUp() {
        rolUsuario = new Rol();
        rolUsuario.setId(1L);
        rolUsuario.setNombre("USUARIO");

        rolAgente = new Rol();
        rolAgente.setId(2L);
        rolAgente.setNombre("AGENTE");

        validRolId = 1L;
        invalidRolId = 99L;
    }

    // Prueba para getRolUsuario
    @Test
    void getRolUsuario_DeberiaRetornarRolUsuario_CuandoDaoDevuelveRol() {
        // ---------- ARRANGE ----------
        when(dao.getRolUsuario()).thenReturn(rolUsuario);

        // ---------- ACT ----------
        Rol result = service.getRolUsuario();

        // ---------- ASSERT ----------
        assertNotNull(result, "El rol no debe ser nulo");
        assertEquals("USUARIO", result.getNombre(), "El nombre del rol debe ser USUARIO");
        assertEquals(1L, result.getId(), "El ID del rol debe ser 1");
        verify(dao, times(1)).getRolUsuario();
    }

    // Prueba para getRolAgente
    @Test
    void getRolAgente_DeberiaRetornarRolAgente_CuandoDaoDevuelveRol() {
        // ---------- ARRANGE ----------
        when(dao.getRolAgente()).thenReturn(rolAgente);

        // ---------- ACT ----------
        Rol result = service.getRolAgente();

        // ---------- ASSERT ----------
        assertNotNull(result, "El rol no debe ser nulo");
        assertEquals("AGENTE", result.getNombre(), "El nombre del rol debe ser AGENTE");
        assertEquals(2L, result.getId(), "El ID del rol debe ser 2");
        verify(dao, times(1)).getRolAgente();
    }

    // Prueba para findRolNombreById con ID válido
    @Test
    void findRolNombreById_DeberiaRetornarNombreRol_CuandoIdEsValido() {
        // ---------- ARRANGE ----------
        when(dao.findRolNombreById(validRolId)).thenReturn(Optional.of("USUARIO"));

        // ---------- ACT ----------
        Optional<String> result = service.findRolNombreById(validRolId);

        // ---------- ASSERT ----------
        assertTrue(result.isPresent(), "El resultado debe estar presente");
        assertEquals("USUARIO", result.get(), "El nombre del rol debe ser USUARIO");
        verify(dao, times(1)).findRolNombreById(validRolId);
    }

    // Prueba para findRolNombreById con ID inválido
    @Test
    void findRolNombreById_DeberiaRetornarOptionalVacio_CuandoIdEsInvalido() {
        // ---------- ARRANGE ----------
        when(dao.findRolNombreById(invalidRolId)).thenReturn(Optional.empty());

        // ---------- ACT ----------
        Optional<String> result = service.findRolNombreById(invalidRolId);

        // ---------- ASSERT ----------
        assertFalse(result.isPresent(), "El resultado debe ser un Optional vacío");
        verify(dao, times(1)).findRolNombreById(invalidRolId);
    }
}
