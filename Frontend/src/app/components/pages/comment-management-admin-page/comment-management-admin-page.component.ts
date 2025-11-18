import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { CommentListAdminCardComponent } from "../../shared/molecules/comment-list-admin-card/comment-list-admin-card.component";

@Component({
  selector: 'app-comment-management-admin-page',
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, CommentListAdminCardComponent],
  templateUrl: './comment-management-admin-page.component.html',
  styleUrl: './comment-management-admin-page.component.css'
})
export class CommentManagementAdminPageComponent {

}
