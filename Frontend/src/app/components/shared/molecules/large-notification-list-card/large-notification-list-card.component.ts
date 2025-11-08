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
      message: "Tienes una nueva reunión con el equipo de marketing.",
      date: "2025-11-08",
      time: "10:00 AM",
    },
    {
      id: 2,
      message: "Laura comentó en tu publicación.",
      date: "2025-11-08",
      time: "10:45 AM",
    },
    {
      id: 3,
      message: "Tu pedido #4721 ha sido enviado.",
      date: "2025-11-07",
      time: "3:30 PM",
    },
    {
      id: 4,
      message:
        "Recordatorio: paga la factura de Internet antes del 10 de noviembre.",
      date: "2025-11-08",
      time: "8:00 AM",
    },
    {
      id: 5,
      message:
        "Nuevo inicio de sesión detectado desde un dispositivo desconocido.",
      date: "2025-11-08",
      time: "9:15 AM",
    },
    {
      id: 6,
      message: "Carlos te envió un mensaje: “Nos vemos a las 7?”",
      date: "2025-11-08",
      time: "9:50 AM",
    },
  ];
}
