import { Component, Input } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-comment-list-item",
  standalone: true,
  imports: [RouterModule],
  templateUrl: "./comment-list-item.component.html",
  styleUrl: "./comment-list-item.component.css",
})
export class CommentListItemComponent {
  @Input() author = "Yo";
  @Input() message = "Mensaje";
  @Input() date = "Hoy";
  @Input() time = "9AM";
}
