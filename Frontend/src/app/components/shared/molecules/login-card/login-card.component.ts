import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { TextInputComponent } from "../../atoms/text-input/text-input.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";

@Component({
  selector: "app-login-card",
  standalone: true,
  imports: [CardComponent, TextInputComponent, MainButtonComponent],
  templateUrl: "./login-card.component.html",
  styleUrl: "./login-card.component.css",
})
export class LoginCardComponent {}
