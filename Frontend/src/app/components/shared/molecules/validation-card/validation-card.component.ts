import { Component, Input } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";

@Component({
  selector: "app-validation-card",
  standalone: true,
  imports: [CardComponent, MainButtonComponent],
  templateUrl: "./validation-card.component.html",
  styleUrl: "./validation-card.component.css",
})
export class ValidationCardComponent {
  @Input() message: string =
    "¿Estas seguro de que deseas cambiar la prioridad de esta solicitud de soporte?";
}
