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
  @Input() message: string =
    "Se te ha asignado un nuevo ticket con ID: 1234, Prioridad: Importante";
  @Input() date: string = "Hoy";
  @Input() time: string = "27/10/2025";
  @Input() width = "";
  @Input() height = "";

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width,
      height: this.height,
    };
  }
}
