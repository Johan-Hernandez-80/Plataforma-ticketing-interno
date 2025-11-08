import { Component, Input } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { ComboBoxComponent } from "../../atoms/combo-box/combo-box.component";
import { TextBoxComponent } from "../../atoms/text-box/text-box.component";
import { RouterModule } from "@angular/router";
import { ValidationCardComponent } from "../validation-card/validation-card.component";
import { FileContainerComponent } from "../../atoms/file-container/file-container.component";

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
export class TicketManageCardComponent {
  @Input() data: {
    id: string;
    categoria: string;
    fechaCreacion: string;
    usuarioId: string;
    estado: string;
    fechaCierre: string;
    evidencia: string;
    prioridad: string;
    description: string;
  } = {
    id: "aa",
    categoria: "bb",
    fechaCreacion: "cc",
    usuarioId: "dd",
    estado: "ee",
    fechaCierre: "ff",
    evidencia: "gg",
    prioridad: "Programado",
    description: "sssssssssss",
  };
  prioridadOptions = ["Urgente", "Importante", "Programado"];
  isPriorityValidation = false;

  setIsPriorityValidation(state: boolean, result?: boolean) {
    this.isPriorityValidation = state;
    if (result == undefined) {
      return;
    }

    alert("resultado: " + result);
  }
}
