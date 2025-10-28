import { Component } from "@angular/core";
import { SmallNotificationListItemComponent } from "../../atoms/small-notification-list-item/small-notification-list-item.component";

@Component({
  selector: "app-small-notification-list",
  standalone: true,
  imports: [SmallNotificationListItemComponent],
  templateUrl: "./small-notification-list.component.html",
  styleUrl: "./small-notification-list.component.css",
})
export class SmallNotificationListComponent {}
