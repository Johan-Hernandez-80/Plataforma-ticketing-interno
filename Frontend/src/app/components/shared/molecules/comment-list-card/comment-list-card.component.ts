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
  ComentarioDTO,
} from "../../../../services/api.service";
import { MapperService } from "../../../../services/mapper.service";
import { UsuarioService } from "../../../../services/usuario.service";

interface ClosePayload {
  result: boolean;
  text: string;
}

interface ChangePayload {
  confirmation?: boolean;
  value?: string;
  event?: Event;
}

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
  private usuarioService = inject(UsuarioService);
  private apiService = inject(ApiService);
  private mapper = inject(MapperService);
  usuario = this.usuarioService.getUser();
  idTicket = Number(this.route.snapshot.paramMap.get("id"));
  isCloseTicketValidation = false;
  isTextArea = false;

  setIsCloseTicketValidation(state: boolean, changePayload?: ChangePayload) {
    this.isCloseTicketValidation = state;
    if (changePayload?.confirmation == undefined) {
      return;
    }
  }

  setIsTextArea(state: boolean, closePayload?: ClosePayload) {
    this.isTextArea = state;
    if (!(closePayload?.result && closePayload?.text)) {
      return;
    }
    //logica para crear comentario
    const data: ComentarioDTO = {
      ticketId: this.idTicket,
      usuarioId: this.usuario?.id ?? -1,
      comentario: closePayload.text,
    };
    this.apiService.createComentario(this.idTicket, data).subscribe({
      next: () => {
        this.loadComentarios();
        alert("Se creo comentario con éxito");
      },
      error: (err) => {
        alert("algo salio mal " + err);
      },
    });
  }

  loadComentarios() {
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

  ngOnInit() {
    this.loadComentarios();
  }

  comentarios: DisplayComentario[] = [];
}
