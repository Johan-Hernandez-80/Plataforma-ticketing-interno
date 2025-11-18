import { Component } from "@angular/core";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { TicketManageAdminCardComponent } from "../../shared/molecules/ticket-manage-admin-card/ticket-manage-admin-card.component";

@Component({
  selector: 'app-ticket-management-admin-page',
  standalone: true,
  imports: [HeaderComponent, BackgroundComponent, TicketManageAdminCardComponent],
  templateUrl: './ticket-management-admin-page.component.html',
  styleUrl: './ticket-management-admin-page.component.css'
})
export class TicketManagementAdminPageComponent {

}
