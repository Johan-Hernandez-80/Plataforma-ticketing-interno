import { Component } from "@angular/core";
import { LoginCardComponent } from "../../shared/molecules/login-card/login-card.component";
import { TextInputComponent } from "../../shared/atoms/text-input/text-input.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";

@Component({
  selector: "app-login",
  standalone: true,
  imports: [LoginCardComponent, TextInputComponent, MainButtonComponent],
  templateUrl: "./login.component.html",
  styleUrl: "./login.component.css",
})
export class LoginComponent {}
