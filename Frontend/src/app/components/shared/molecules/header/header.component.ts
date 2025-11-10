import { Component, inject } from "@angular/core";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { LogOutButtonComponent } from "../../atoms/log-out-button/log-out-button.component";
import { ProfileButtonComponent } from "../../atoms/profile-button/profile-button.component";
import { SmallNotificationListComponent } from "../small-notification-list/small-notification-list.component";
import { Router } from "@angular/router";

@Component({
  selector: "app-header",
  standalone: true,
  imports: [
    MainButtonComponent,
    LogOutButtonComponent,
    ProfileButtonComponent,
    SmallNotificationListComponent,
  ],
  templateUrl: "./header.component.html",
  styleUrl: "./header.component.css",
})
export class HeaderComponent {
  private router = inject(Router);
  isPerfilHover = false;
  isNotificationButtonActive = false;
  logOutButtonClass = "";

  setLogOutButtonClass(newClass: string) {
    if (!newClass) {
      setTimeout(() => {
        this.logOutButtonClass = newClass;
      }, 2000);
    } else {
      this.logOutButtonClass = newClass;
    }
  }

  setIsPerfilHover(state: boolean) {
    this.isPerfilHover = state;
  }

  setNotificationButtonActive(state: boolean) {
    this.isNotificationButtonActive = state;
  }

  navigate(path: string) {
    this.router.navigate([path]);
  }
}
