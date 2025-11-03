import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { BackgroundComponent } from "../../atoms/background/background.component";

@Component({
  selector: "app-text-area",
  standalone: true,
  imports: [CardComponent, MainButtonComponent, BackgroundComponent],
  templateUrl: "./text-area.component.html",
  styleUrl: "./text-area.component.css",
})
export class TextAreaComponent {}
