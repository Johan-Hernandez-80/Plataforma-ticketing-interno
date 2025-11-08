import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-notification-list-item",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./notification-list-item.component.html",
  styleUrl: "./notification-list-item.component.css",
})
export class NotificationListItemComponent {
  @Input() message =
    "Se te ha asignado un nuevo ticket con ID: 1234, Prioridad: Importante";
  @Input() date = "Hoy";
  @Input() time = "27/10/2025";
  @Input() width = "";
  @Input() height = "";

  customStyles: object= {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width,
      height: this.height,
    };
  }
}
