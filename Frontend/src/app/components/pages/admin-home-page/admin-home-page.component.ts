import { Component, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { Router } from "@angular/router";
import { ApiService, DisplayTicket } from "../../../services/api.service";

@Component({
  selector: "app-admin-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, TableComponent],
  templateUrl: "./admin-home-page.component.html",
  styleUrl: "./admin-home-page.component.css",
})
export class AdminHomePageComponent {
  router = inject(Router);

  constructor(private api: ApiService) { }

  tickets: DisplayTicket[] = [
    {
      id: 4,
      titulo: "dhkjdksafslfjasñldkf",
      categoria: "fdfd",
      estado: "pendiente",
      prioridad: "programado",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "en progreso",
      prioridad: "urgente",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },

    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
    },
  ];

}
