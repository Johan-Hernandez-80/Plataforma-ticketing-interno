import { Component, inject, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
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
import { CommentListAdminItemComponent } from "../../atoms/comment-list-admin-item/comment-list-admin-item.component";

interface ClosePayload {
  result: boolean;
  text: string;
}

@Component({
  selector: 'app-comment-list-admin-card',
  standalone: true,
  imports: [
    CardComponent,
    MainButtonComponent,
    CommentListAdminItemComponent,
    ValidationCardComponent,
    TextAreaComponent,
    RouterModule,
  ],
  templateUrl: './comment-list-admin-card.component.html',
  styleUrl: './comment-list-admin-card.component.css'
})
export class CommentListAdminCardComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private usuarioService = inject(UsuarioService);
  private apiService = inject(ApiService);
  private mapper = inject(MapperService);
  private router = inject(Router);
  usuario = this.usuarioService.getUser();
  idTicket = Number(this.route.snapshot.paramMap.get("id"));
  isCloseTicketValidation = false;
  isTextArea = false;
  isTicketCerrado = true;

  asignarAAgente() {
    this.router.navigate([
      '/admin/ticket/management',
      this.idTicket,
      'asignar'
    ]);
  }

  setIsCloseTicketValidation(state: boolean, result?: boolean) {
    this.isCloseTicketValidation = state;
    if (!result) {
      return;
    }
    //logica para cerrar un ticket
    this.isTicketCerrado = true;
    this.apiService.cerrarTicket(this.idTicket).subscribe({
      next: () => {
        this.loadTicketState();
        alert("ticket cerrado exitosamente");
      },
      error: () => {
        alert("error: no se puedo cerrar");
      },
    });
  }

  setIsTextArea(state: boolean, closePayload?: ClosePayload) {
    this.isTextArea = state;
    if (!(closePayload?.result && closePayload?.text.trim())) {
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

  loadTicketState() {
    this.apiService.getTicketById(this.idTicket).subscribe({
      next: (response) => {
        this.isTicketCerrado = response?.estado.toUpperCase() === "CERRADO";
      },
      error: (err) => {
        alert("eror: " + err);
      },
    });
  }

  ngOnInit() {
    this.loadComentarios();
    this.loadTicketState();
  }

  comentarios: DisplayComentario[] = [];
}
