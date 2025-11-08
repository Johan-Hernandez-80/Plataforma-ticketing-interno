import { Component } from "@angular/core";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { LogOutButtonComponent } from "../../atoms/log-out-button/log-out-button.component";
import { SmallNotificationListComponent } from "../small-notification-list/small-notification-list.component";

@Component({
  selector: "app-header",
  standalone: true,
  imports: [
    MainButtonComponent,
    LogOutButtonComponent,
    SmallNotificationListComponent,
  ],
  templateUrl: "./header.component.html",
  styleUrl: "./header.component.css",
})
export class HeaderComponent {
  isPerfilHover = false;
  isNotificationButtonActive = false;

  setIsPerfilHover(state: boolean) {
    this.isPerfilHover = state;
  }

  setNotificationButtonActive(state: boolean) {
    this.isNotificationButtonActive = state;
  }
}
