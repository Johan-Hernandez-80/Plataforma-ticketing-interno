import { Component, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { Router } from "@angular/router";
import { TicketDTO } from "../../../services/api.service";
import { SAMPLE_TICKETS_DTO } from "../../../data/sample-tickets";

@Component({
  selector: "app-user-home-page",
  standalone: true,
  imports: [
    BackgroundComponent,
    HeaderComponent,
    TableComponent,
    MainButtonComponent,
  ],
  templateUrl: "./userHomePage.component.html",
  styleUrl: "./userHomePage.component.css",
})
export class UserHomePageComponent {
  router = inject(Router);
  tickets: TicketDTO[] = SAMPLE_TICKETS_DTO;
  onClickTable() {
    this.router.navigate(["/ticket/management/user"]);
  }
}
