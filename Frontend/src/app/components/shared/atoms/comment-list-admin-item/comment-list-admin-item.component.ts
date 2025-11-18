import { Component, Input, inject } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { DisplayComentario } from "../../../../services/api.service";

@Component({
  selector: 'app-comment-list-admin-item',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './comment-list-admin-item.component.html',
  styleUrl: './comment-list-admin-item.component.css'
})
export class CommentListAdminItemComponent {
  router = inject(Router);
  @Input() data: DisplayComentario | null = null;
  @Input() idTicket = -1;
}
