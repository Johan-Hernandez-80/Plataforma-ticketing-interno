import { Component, Input } from "@angular/core";

@Component({
  selector: "app-small-notification-list-item",
  standalone: true,
  imports: [],
  templateUrl: "./small-notification-list-item.component.html",
  styleUrl: "./small-notification-list-item.component.css",
})
export class SmallNotificationListItemComponent {
  @Input() message: string =
    "Se te ha asignado un nuevo ticket con ID: 1234, Prioridad: Importante";
  @Input() date: string = "Hoy";
  @Input() time: string = "27/10/2025";
}
