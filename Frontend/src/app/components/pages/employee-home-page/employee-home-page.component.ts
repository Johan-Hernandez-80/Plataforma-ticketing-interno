import { Component, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { Router } from "@angular/router";
import { SAMPLE_TICKETS_DTO } from "../../../data/sample-tickets";
import { TicketDTO } from "../../../services/api.service";

interface Ticket {
  id: number;
  titulo: string;
  categoria: string;
  estado: string;
  prioridad: string;
  fecha: string;
}

@Component({
  selector: "app-employee-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, TableComponent],
  templateUrl: "./employee-home-page.component.html",
  styleUrl: "./employee-home-page.component.css",
})
export class EmployeeHomePageComponent {
  router = inject(Router);
  tickets: TicketDTO[] = SAMPLE_TICKETS_DTO;
}
