import { Component, output } from "@angular/core";
import { NotificationListItemComponent } from "../../atoms/notification-list-item/notification-list-item.component";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-small-notification-list",
  standalone: true,
  imports: [NotificationListItemComponent, RouterModule],
  templateUrl: "./small-notification-list.component.html",
  styleUrl: "./small-notification-list.component.css",
})
export class SmallNotificationListComponent {
  closeEvent = output<void>();

  onClose() {
    this.closeEvent.emit();
  }
}
