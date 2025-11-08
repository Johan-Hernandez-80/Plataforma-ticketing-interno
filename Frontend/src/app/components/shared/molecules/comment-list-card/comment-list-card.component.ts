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
      id: 1,
      message:
        "¡Hola! Estuve revisando el código del componente durante la mañana y noté que podríamos optimizar la parte del renderizado para hacerlo más eficiente. Si reorganizamos algunos de los métodos y reducimos las llamadas innecesarias al backend, podríamos mejorar el rendimiento general de la aplicación. ¿Quieres que te muestre una posible implementación para hacerlo?",
      author: "María López",
      date: "08/11/2025",
      time: "09:05 AM",
    },
    {
      id: 2,
      message:
        "Hey, acabo de leer tu comentario y me parece una excelente idea. Además de optimizar esa parte, podríamos aprovechar para limpiar un poco la lógica del servicio, ya que tiene algunas funciones repetidas que podríamos reutilizar con un helper. También pensé que sería buena idea agregar algunos tests unitarios para asegurar que los cambios no rompan nada.",
      author: "Carlos Pérez",
      date: "08/11/2025",
      time: "09:12 AM",
    },
    {
      id: 3,
      message:
        "What’s up, bruh? I was just testing the new feature on different browsers and it works perfectly! The UI feels much smoother now, especially when loading large data sets. I think the lazy loading we added really made a difference. Maybe we can run a few performance tests later today just to confirm everything’s consistent across devices.",
      author: "Jake Thompson",
      date: "08/11/2025",
      time: "09:18 AM",
    },
    {
      id: 4,
      message:
        "Hello world! Just wanted to leave a quick note saying that the deployment went great and everything’s running in production without issues. The logs show stable performance, and the new notification system is functioning perfectly. I’ll keep monitoring the metrics for the next few hours just to make sure everything stays consistent.",
      author: "Ana Martínez",
      date: "08/11/2025",
      time: "09:25 AM",
    },
    {
      id: 5,
      message:
        "¡Hola! Estuve revisando el código del componente durante la mañana y noté que podríamos optimizar la parte del renderizado para hacerlo más eficiente. Si reorganizamos algunos de los métodos y reducimos las llamadas innecesarias al backend, podríamos mejorar el rendimiento general de la aplicación. ¿Quieres que te muestre una posible implementación para hacerlo?",
      author: "María López",
      date: "08/11/2025",
      time: "09:05 AM",
    },
    {
      id: 6,
      message:
        "Hey, acabo de leer tu comentario y me parece una excelente idea. Además de optimizar esa parte, podríamos aprovechar para limpiar un poco la lógica del servicio, ya que tiene algunas funciones repetidas que podríamos reutilizar con un helper. También pensé que sería buena idea agregar algunos tests unitarios para asegurar que los cambios no rompan nada.",
      author: "Carlos Pérez",
      date: "08/11/2025",
      time: "09:12 AM",
    },
    {
      id: 7,
      message:
        "What’s up, bruh? I was just testing the new feature on different browsers and it works perfectly! The UI feels much smoother now, especially when loading large data sets. I think the lazy loading we added really made a difference. Maybe we can run a few performance tests later today just to confirm everything’s consistent across devices.",
      author: "Jake Thompson",
      date: "08/11/2025",
      time: "09:18 AM",
    },
    {
      id: 8,
      message:
        "Hello world! Just wanted to leave a quick note saying that the deployment went great and everything’s running in production without issues. The logs show stable performance, and the new notification system is functioning perfectly. I’ll keep monitoring the metrics for the next few hours just to make sure everything stays consistent.",
      author: "Ana Martínez",
      date: "08/11/2025",
      time: "09:25 AM",
    },
  ];
}
