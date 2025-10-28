import { Component } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { TextInputComponent } from "./components/shared/atoms/text-input/text-input.component";
import { MainButtonComponent } from "./components/shared/atoms/main-button/main-button.component";
import { CardComponent } from "./components/shared/atoms/card/card.component";
import { LoginCardComponent } from "./components/shared/molecules/login-card/login-card.component";
import { LoginComponent } from "./components/pages/login/login.component";
import { HeaderComponent } from "./components/shared/molecules/header/header.component";
import { LogOutButtonComponent } from "./components/shared/atoms/log-out-button/log-out-button.component";
import { SmallNotificationListItemComponent } from "./components/shared/atoms/small-notification-list-item/small-notification-list-item.component";
import { SmallNotificationListComponent } from "./components/shared/molecules/small-notification-list/small-notification-list.component";
import { ValidationCardComponent } from "./components/shared/molecules/validation-card/validation-card.component";
import { TextAreaComponent } from "./components/shared/molecules/text-area/text-area.component";

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
    SmallNotificationListItemComponent,
    SmallNotificationListComponent,
    ValidationCardComponent,
    TextAreaComponent,
  ],
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.css",
})
export class AppComponent {
  title = "Frontend";
}
