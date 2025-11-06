import { Component } from "@angular/core";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { TicketManageCardComponent } from "../../shared/molecules/ticket-manage-card/ticket-manage-card.component";

@Component({
  selector: "app-ticket-management-page",
  standalone: true,
  imports: [HeaderComponent, BackgroundComponent, TicketManageCardComponent],
  templateUrl: "./ticket-management-page.component.html",
  styleUrl: "./ticket-management-page.component.css",
})
export class TicketManagementPageComponent {}
