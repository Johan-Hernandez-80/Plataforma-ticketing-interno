import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { CommentListItemComponent } from "../../atoms/comment-list-item/comment-list-item.component";
import { ValidationCardComponent } from "../validation-card/validation-card.component";
import { TextAreaComponent } from "../text-area/text-area.component";

@Component({
  selector: "app-comment-list-card",
  standalone: true,
  imports: [
    CardComponent,
    MainButtonComponent,
    CommentListItemComponent,
    ValidationCardComponent,
    TextAreaComponent,
  ],
  templateUrl: "./comment-list-card.component.html",
  styleUrl: "./comment-list-card.component.css",
})
export class CommentListCardComponent {
  isCloseTicketValidation = false;
  isTextArea = false;

  setIsCloseTicketValidation(state: boolean, result?: boolean) {
    this.isCloseTicketValidation = state;
    if (result == undefined) {
      return;
    }
  }

  setIsTextArea(state: boolean, result?: boolean) {
    this.isTextArea = state;
    if (result == undefined) {
      return;
    }
  }

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
