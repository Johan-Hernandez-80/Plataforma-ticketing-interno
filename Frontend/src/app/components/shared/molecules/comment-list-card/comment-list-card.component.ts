import { Component, inject, OnInit } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { CommentListItemComponent } from "../../atoms/comment-list-item/comment-list-item.component";
import { ValidationCardComponent } from "../validation-card/validation-card.component";
import { TextAreaComponent } from "../text-area/text-area.component";
import { ActivatedRoute, RouterModule } from "@angular/router";
import {
  ApiService,
  ComentarioDTO,
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

  comentarios: DisplayComentario[] = [
    {
      id: 1,
      mensaje:
        "¡Hola! Estuve revisando el código del componente durante la mañana y noté que podríamos optimizar la parte del renderizado para hacerlo más eficiente. Si reorganizamos algunos de los métodos y reducimos las llamadas innecesarias al backend, podríamos mejorar el rendimiento general de la aplicación. ¿Quieres que te muestre una posible implementación para hacerlo?",
      autor: "María López",
      date: "08/11/2025",
      time: "09:05 AM",
    },
    {
      id: 2,
      mensaje:
        "Hey, acabo de leer tu comentario y me parece una excelente idea. Además de optimizar esa parte, podríamos aprovechar para limpiar un poco la lógica del servicio, ya que tiene algunas funciones repetidas que podríamos reutilizar con un helper. También pensé que sería buena idea agregar algunos tests unitarios para asegurar que los cambios no rompan nada.",
      autor: "Carlos Pérez",
      date: "08/11/2025",
      time: "09:12 AM",
    },
  ];
}
