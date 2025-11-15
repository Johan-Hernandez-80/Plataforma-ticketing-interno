import { Component, Input, OnChanges } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-notification-list-item",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./notification-list-item.component.html",
  styleUrl: "./notification-list-item.component.css",
})
export class NotificationListItemComponent implements OnChanges {
  @Input() message = "test";
  @Input() date = "";
  @Input() time = "";
  @Input() width = "";
  @Input() height = "";

  customStyles: object = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width,
      height: this.height,
    };
  }
}
