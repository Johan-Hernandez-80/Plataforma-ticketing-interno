import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { NotificationListItemComponent } from "../../atoms/notification-list-item/notification-list-item.component";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-large-notification-list-card",
  standalone: true,
  imports: [CardComponent, NotificationListItemComponent, RouterModule],
  templateUrl: "./large-notification-list-card.component.html",
  styleUrl: "./large-notification-list-card.component.css",
})
export class LargeNotificationListCardComponent {
  notifications = [
    {
      id: 1,
      message: "Yo",
      date: "HOy",
      time: "10AM",
    },
    {
      id: 2,
      message: "Ella",
      date: "Mañana",
      time: "10AM",
    },
    {
      id: 3,
      message: "El",
      date: "HOy",
      time: "10AM",
    },
    {
      id: 4,
      message: "Yo",
      date: "HOy",
      time: "10AM",
    },
    {
      id: 5,
      message: "Ella",
      date: "Mañana",
      time: "10AM",
    },
    {
      id: 6,
      message: "El",
      date: "HOy",
      time: "10AM",
    },
  ];
}
