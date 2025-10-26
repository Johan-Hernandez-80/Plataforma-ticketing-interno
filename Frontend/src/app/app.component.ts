import { Component } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { TextInputComponent } from "./components/shared/atoms/text-input/text-input.component";
import { MainButtonComponent } from "./components/shared/atoms/main-button/main-button.component";
import { CardComponent } from "./components/shared/atoms/card/card.component";
import { LoginCardComponent } from "./components/shared/molecules/login-card/login-card.component";
import { LoginComponent } from "./components/pages/login/login.component";
import { HeaderComponent } from "./components/shared/molecules/header/header.component";
import { LogOutButtonComponent } from "./components/shared/atoms/log-out-button/log-out-button.component";

@Component({
  selector: "app-root",
  standalone: true,
  imports: [
    RouterOutlet,
    TextInputComponent,
    MainButtonComponent,
    CardComponent,
    LoginCardComponent,
    LoginComponent,
    HeaderComponent,
    LogOutButtonComponent,
  ],
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.css",
})
export class AppComponent {
  title = "Frontend";
}
