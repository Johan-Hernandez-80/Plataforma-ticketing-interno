import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { LargeNotificationListCardComponent } from "../../shared/molecules/large-notification-list-card/large-notification-list-card.component";

@Component({
  selector: "app-notification-list-page",
  standalone: true,
  imports: [
    BackgroundComponent,
    HeaderComponent,
    LargeNotificationListCardComponent,
  ],
  templateUrl: "./notification-list-page.component.html",
  styleUrl: "./notification-list-page.component.css",
})
export class NotificationListPageComponent {}
