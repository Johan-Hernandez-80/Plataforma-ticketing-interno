import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { ComboBoxComponent } from "../../atoms/combo-box/combo-box.component";
import { TextBoxComponent } from "../../atoms/text-box/text-box.component";
import { RouterModule } from "@angular/router";
import { ValidationCardComponent } from "../validation-card/validation-card.component";

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
  ],
  templateUrl: "./ticket-manage-card.component.html",
  styleUrl: "./ticket-manage-card.component.css",
})
export class TicketManageCardComponent {
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
