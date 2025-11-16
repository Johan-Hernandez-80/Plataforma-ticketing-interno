import { Component, inject, OnInit } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { CommentListItemComponent } from "../../atoms/comment-list-item/comment-list-item.component";
import { ValidationCardComponent } from "../validation-card/validation-card.component";
import { TextAreaComponent } from "../text-area/text-area.component";
import { ActivatedRoute, RouterModule } from "@angular/router";
import {
  ApiService,
  DisplayComentario,
} from "../../../../services/api.service";
import { MapperService } from "../../../../services/mapper.service";

@Component({
  selector: "app-comment-list-card",
  standalone: true,
  imports: [
    CardComponent,
    MainButtonComponent,
    CommentListItemComponent,
    ValidationCardComponent,
    TextAreaComponent,
    RouterModule,
  ],
  templateUrl: "./comment-list-card.component.html",
  styleUrl: "./comment-list-card.component.css",
})
export class CommentListCardComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private apiService = inject(ApiService);
  private mapper = inject(MapperService);
  idTicket = Number(this.route.snapshot.paramMap.get("id"));
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

  ngOnInit() {
    this.apiService.getComentariosByTicketId(this.idTicket).subscribe({
      next: (response) => {
        this.comentarios = this.mapper.mapComentariosDtoToDisplay(
          response ?? [],
        );
      },
      error: () => {
        alert("error inesperado");
      },
    });
  }

  comentarios: DisplayComentario[] = [];
}
