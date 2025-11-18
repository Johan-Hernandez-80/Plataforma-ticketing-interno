import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { CommentAdminCardComponent } from "../../shared/molecules/comment-admin-card/comment-admin-card.component";

@Component({
  selector: 'app-comment-view-admin-page',
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, CommentAdminCardComponent],
  templateUrl: './comment-view-admin-page.component.html',
  styleUrl: './comment-view-admin-page.component.css'
})
export class CommentViewAdminPageComponent {

}
