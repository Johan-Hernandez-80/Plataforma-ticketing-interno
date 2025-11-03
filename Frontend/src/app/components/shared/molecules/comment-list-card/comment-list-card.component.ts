import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { CommentListItemComponent } from "../../atoms/comment-list-item/comment-list-item.component";

@Component({
  selector: "app-comment-list-card",
  standalone: true,
  imports: [CardComponent, MainButtonComponent, CommentListItemComponent],
  templateUrl: "./comment-list-card.component.html",
  styleUrl: "./comment-list-card.component.css",
})
export class CommentListCardComponent {
  comments = [
    {
      id: 3,
      message: "HOla",
      author: "Yo",
      date: "Hoy",
      time: "9AM",
    },
    {
      id: 3,
      message: "Hey",
      author: "Yo",
      date: "Hoy",
      time: "9AM",
    },
    {
      id: 3,
      message: "What's up bruh?",
      author: "Yo",
      date: "Hoy",
      time: "9AM",
    },
    {
      id: 3,
      message: "Hello world",
      author: "Yo",
      date: "Hoy",
      time: "9AM",
    },
  ];
}
