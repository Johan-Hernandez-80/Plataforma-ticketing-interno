import { Component, Input, output } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { BackgroundComponent } from "../../atoms/background/background.component";

@Component({
  selector: "app-validation-card",
  standalone: true,
  imports: [CardComponent, MainButtonComponent, BackgroundComponent],
  templateUrl: "./validation-card.component.html",
  styleUrl: "./validation-card.component.css",
})
export class ValidationCardComponent {
  @Input() message: string = "¿?";

  close = output<boolean>();

  onClose(state: boolean) {
    this.close.emit(state);
  }
}
