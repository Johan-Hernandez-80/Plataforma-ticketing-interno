import { Component } from "@angular/core";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { TicketManageCardComponent } from "../../shared/molecules/ticket-manage-card/ticket-manage-card.component";
import { Router } from '@angular/router';
import { DisplayTicket } from "../../../services/api.service";

@Component({
  selector: "app-ticket-management-page",
  standalone: true,
  imports: [HeaderComponent, BackgroundComponent, TicketManageCardComponent],
  templateUrl: "./ticket-management-page.component.html",
  styleUrl: "./ticket-management-page.component.css",
})
export class TicketManagementPageComponent {
  ticket: DisplayTicket = {
    id: 1, categoria: "Impresora", titulo: "Se me cayó la impresora",
    prioridad: "Urgente", estado: "Pendiente", fechaCreacion: "2024-12-12"
  };

  constructor() {
    const stateTicket = history.state?.ticket as DisplayTicket;
    if (stateTicket) this.ticket = stateTicket;
  }
}
