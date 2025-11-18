import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { CommentCardComponent } from "../../shared/molecules/comment-card/comment-card.component";

@Component({
  selector: "app-comment-view-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, CommentCardComponent],
  templateUrl: "./comment-view-page.component.html",
  styleUrl: "./comment-view-page.component.css",
})
export class CommentViewPageComponent {}
