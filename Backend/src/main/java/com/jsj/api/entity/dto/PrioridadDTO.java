package com.jsj.api.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        description = "DTO para la prioridad de un ticket."
)
public class PrioridadDTO {

  String prioridad;

  public PrioridadDTO(String prioridad) {
    this.prioridad = prioridad;
  }

  public String getPrioridad() {
    return this.prioridad;
  }

  public void setPrioridad(String prioridad) {
    this.prioridad = prioridad;
  }
}
