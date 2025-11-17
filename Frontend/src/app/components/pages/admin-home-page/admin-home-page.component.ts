import { Component, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { Router } from "@angular/router";
import { ApiService, DisplayTicket, TicketDTO } from "../../../services/api.service";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { NewCategoriaModalComponent } from "../../shared/molecules/new-categoria-modal/new-categoria-modal.component";

@Component({
  selector: "app-admin-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, TableComponent, MainButtonComponent, NewCategoriaModalComponent],
  templateUrl: "./admin-home-page.component.html",
  styleUrl: "./admin-home-page.component.css",
})
export class AdminHomePageComponent {
  router = inject(Router);
  showModal = false;
  modalType: 'categoria' = 'categoria';
  modalValue = '';
  modalMessage = '';
  showNewCategoria = false;

  constructor(private api: ApiService) { }

  tickets: TicketDTO[] = [];

  ngOnInit() {
    this.api.getTickets().subscribe(data => {
      this.tickets = data;
    });
  }

  onRequestConfirm() {
    this.modalType = 'categoria';
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }
}
