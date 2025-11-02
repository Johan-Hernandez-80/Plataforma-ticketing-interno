import { Component, Input } from "@angular/core";

@Component({
  selector: "app-comment-list-item",
  standalone: true,
  imports: [],
  templateUrl: "./comment-list-item.component.html",
  styleUrl: "./comment-list-item.component.css",
})
export class CommentListItemComponent {
  @Input() author = "Yo";
  @Input() message = "Mensaje";
  @Input() date = "Hoy";
  @Input() time = "9AM";
}
