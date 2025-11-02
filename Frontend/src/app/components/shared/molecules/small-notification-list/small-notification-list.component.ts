import { Component } from "@angular/core";
import { NotificationListItemComponent } from "../../atoms/notification-list-item/notification-list-item.component";

@Component({
  selector: "app-small-notification-list",
  standalone: true,
  imports: [NotificationListItemComponent],
  templateUrl: "./small-notification-list.component.html",
  styleUrl: "./small-notification-list.component.css",
})
export class SmallNotificationListComponent {}
