import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { CommentListCardComponent } from "../../shared/molecules/comment-list-card/comment-list-card.component";

@Component({
  selector: "app-comment-management-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, CommentListCardComponent],
  templateUrl: "./comment-management-page.component.html",
  styleUrl: "./comment-management-page.component.css",
})
export class CommentManagementPageComponent {}
