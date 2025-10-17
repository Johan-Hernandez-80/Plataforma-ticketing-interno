package com.jsj.api.entity.dto;

public class PrioridadDTO {

  String prioridad;

  PrioridadDTO(String prioridad) {
    this.prioridad = prioridad;
  }

  public String getPrioridad() {
    return this.prioridad;
  }

  public void setPrioridad(String prioridad) {
    this.prioridad = prioridad;
  }
}
