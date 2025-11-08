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
  @Input() author = "María López";
  @Input() message = "¡Hola! ¿Cómo estás? ¿Listo para la reunión de hoy?";
  @Input() date = "08/11/2025";
  @Input() time = "09:00 AM";
}
