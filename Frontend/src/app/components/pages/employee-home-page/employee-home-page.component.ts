import { Component, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { Router } from "@angular/router";
import { DisplayTicket } from "../../../services/api.service";

@Component({
  selector: "app-employee-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, TableComponent],
  templateUrl: "./employee-home-page.component.html",
  styleUrl: "./employee-home-page.component.css",
})
export class EmployeeHomePageComponent {
  router = inject(Router);
  tickets: DisplayTicket[] = [
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "pendiente",
      prioridad: "programado",
      fechaCreacion: "dfd",
      fechaCierre: "dfd",
      descripcion: "",
      usuarioId: 6,
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "en progreso",
      prioridad: "urgente",
      fechaCreacion: "dfd",
      fechaCierre: "dfd",
      descripcion: "",
      usuarioId: 7,
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fechaCreacion: "dfd",
      fechaCierre: "dfd",
      descripcion: "",
      usuarioId: 5,
    },
  ];

  onClickTable() {
    this.router.navigate(["/ticket/management"]);
  }
}
