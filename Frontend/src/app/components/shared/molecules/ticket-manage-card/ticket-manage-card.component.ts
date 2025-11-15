import { Component, Input, output, OnInit, inject } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { ComboBoxComponent } from "../../atoms/combo-box/combo-box.component";
import { TextBoxComponent } from "../../atoms/text-box/text-box.component";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { ValidationCardComponent } from "../validation-card/validation-card.component";
import { FileContainerComponent } from "../../atoms/file-container/file-container.component";
import { ApiService, DisplayTicket } from "../../../../services/api.service";
import { UsuarioService } from "../../../../services/usuario.service";
import { MapperService } from "../../../../services/mapper.service";

@Component({
  selector: "app-ticket-manage-card",
  standalone: true,
  imports: [
    CardComponent,
    MainButtonComponent,
    ComboBoxComponent,
    TextBoxComponent,
    RouterModule,
    ValidationCardComponent,
    FileContainerComponent,
  ],
  templateUrl: "./ticket-manage-card.component.html",
  styleUrl: "./ticket-manage-card.component.css",
})
export class TicketManageCardComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private navigation = this.router.getCurrentNavigation();
  private usuarioService = inject(UsuarioService);
  private apiService = inject(ApiService);
  private mapper = inject(MapperService);
  usuario = this.usuarioService.getUser();
  idTicket = Number(this.route.snapshot.paramMap.get("id"));
  data: DisplayTicket | null = null;

  @Input() showCancelButton = false;
  @Input() priorityEditable = true;
  requestCancel = output<void>();
  prioridadOptions = ["Urgente", "Importante", "Programado"];
  isPriorityValidation = false;

  setIsPriorityValidation(state: boolean, result?: boolean) {
    this.isPriorityValidation = state;
    if (result == undefined) {
      return;
    }

    alert("resultado: " + result);
  }

  ngOnInit() {
    this.apiService.getTicketById(this.idTicket!).subscribe({
      next: (response) => {
        this.data = this.mapper.mapTicketDtoToDisplay(response);
      },
      error: () => {
        alert("error inesperado");
      },
    });
  }

  navBack(): string {
    switch (this.usuario?.rolId) {
      case 1:
        return "/admin/home";
      case 2:
        return "/agent/home";
      case 3:
        return "/home";
      default:
        return "";
    }
  }
}
