package com.jsj.api.controller;

import com.jsj.api.entity.dto.CategoriaDTO;
import com.jsj.api.exception.CategoriaExistenteException;
import com.jsj.api.exception.CategoriaNoEncontradaException;
import com.jsj.api.exception.ImmutableFieldException;
import com.jsj.api.exception.InsufficientSavingPermissionsException;
import com.jsj.api.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Gestión de Categorías (CRUD) - Documentación similar a TicketController
 */
@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorías")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría. El nombre debe ser único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoría creada correctamente", content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o categoría existente", content = @Content(schema = @Schema(example = "{ \"error\": \"Nombre ya existe o datos inválidos\" }"))),
        @ApiResponse(responseCode = "401", description = "Permisos insuficientes", content = @Content(schema = @Schema(example = "{ \"error\": \"No autorizado\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor.\" }")))
    })
    @PostMapping
    public ResponseEntity<?> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la categoría a crear", required = true, content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
        @Valid @RequestBody CategoriaDTO dto) {

        try {
            CategoriaDTO created = service.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (CategoriaExistenteException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Nombre de categoría ya existe", "detalle", ex.getMessage()));
        } catch (InsufficientSavingPermissionsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Permisos insuficientes", "detalle", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno", "detalle", ex.getMessage()));
        }
    }

    @Operation(summary = "Listar categorías", description = "Obtiene la lista completa de categorías.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida", content = @Content(array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = CategoriaDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor.\" }")))
    })
    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<CategoriaDTO> list = service.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno", "detalle", ex.getMessage()));
        }
    }

    @Operation(summary = "Obtener categoría por ID", description = "Devuelve una categoría por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada", content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content(schema = @Schema(example = "{ \"error\": \"Categoría no encontrada\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor.\" }")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @Parameter(description = "ID de la categoría a consultar", required = true) @PathVariable Long id) {

        try {
            CategoriaDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (CategoriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Categoría no encontrada", "detalle", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno", "detalle", ex.getMessage()));
        }
    }

    @Operation(summary = "Actualizar categoría", description = "Actualiza los datos de una categoría existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente", content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o nombre ya en uso", content = @Content(schema = @Schema(example = "{ \"error\": \"Datos inválidos\" }"))),
        @ApiResponse(responseCode = "401", description = "Permisos insuficientes", content = @Content(schema = @Schema(example = "{ \"error\": \"No autorizado\" }"))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content(schema = @Schema(example = "{ \"error\": \"Categoría no encontrada\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor.\" }")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @Parameter(description = "ID de la categoría a actualizar", required = true) @PathVariable Long id,
        @Valid @RequestBody CategoriaDTO dto) {

        try {
            CategoriaDTO updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (CategoriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Categoría no encontrada", "detalle", ex.getMessage()));
        } catch (CategoriaExistenteException | ImmutableFieldException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Datos inválidos", "detalle", ex.getMessage()));
        } catch (InsufficientSavingPermissionsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Permisos insuficientes", "detalle", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno", "detalle", ex.getMessage()));
        }
    }

    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content(schema = @Schema(example = "{ \"error\": \"Categoría no encontrada\" }"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(example = "{ \"error\": \"Error interno del servidor.\" }")))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @Parameter(description = "ID de la categoría a eliminar", required = true) @PathVariable Long id) {

        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (CategoriaNoEncontradaException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Categoría no encontrada", "detalle", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno", "detalle", ex.getMessage()));
        }
    }
}