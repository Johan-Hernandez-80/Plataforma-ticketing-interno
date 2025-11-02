import { Component } from "@angular/core";
import { LoginCardComponent } from "../../shared/molecules/login-card/login-card.component";
import { TextInputComponent } from "../../shared/atoms/text-input/text-input.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";

@Component({
  selector: "app-login-page",
  standalone: true,
  imports: [
    LoginCardComponent,
    TextInputComponent,
    MainButtonComponent,
    BackgroundComponent,
    HeaderComponent,
  ],
  templateUrl: "./loginPage.component.html",
  styleUrl: "./loginPage.component.css",
})
export class LoginPageComponent {}
