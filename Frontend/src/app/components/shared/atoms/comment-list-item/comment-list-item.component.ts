import { Component, Input, inject } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { DisplayComentario } from "../../../../services/api.service";

@Component({
  selector: "app-comment-list-item",
  standalone: true,
  imports: [RouterModule],
  templateUrl: "./comment-list-item.component.html",
  styleUrl: "./comment-list-item.component.css",
})
export class CommentListItemComponent {
  router = inject(Router);
  @Input() data: DisplayComentario | null = null;
  @Input() idTicket = -1;
}
