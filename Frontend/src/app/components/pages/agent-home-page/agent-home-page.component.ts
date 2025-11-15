import { Component, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { Router } from "@angular/router";

interface Ticket {
  id: number;
  titulo: string;
  categoria: string;
  estado: string;
  prioridad: string;
  fecha: string;
}

@Component({
  selector: "app-agent-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, TableComponent],
  templateUrl: "./agent-home-page.component.html",
  styleUrl: "./agent-home-page.component.css",
})
export class AgentHomePageComponent {
  router = inject(Router);
  tickets: Ticket[] = [
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "pendiente",
      prioridad: "programado",
      fecha: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "en progreso",
      prioridad: "urgente",
      fecha: "dfd",
    },
    {
      id: 4,
      titulo: "dd",
      categoria: "fdfd",
      estado: "cerrado",
      prioridad: "importante",
      fecha: "dfd",
    },
  ];

  onClickTable() {
    this.router.navigate(["/ticket/management"]);
  }
}
