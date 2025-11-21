import { Injectable, inject } from "@angular/core";
import {
  CategoriaDTO,
  TicketDTO,
  ComentarioDTO,
  DisplayComentario,
  DisplayTicket,
} from "./api.service";
import { ApiService } from "./api.service";
import { DatePipe } from "@angular/common";
import { UsuarioService } from "./usuario.service";

@Injectable({
  providedIn: "root",
})
export class MapperService {
  private apiService = inject(ApiService);
  private usuarioService = inject(UsuarioService);
  private datePipe = inject(DatePipe);
  private CATEGORIAS_MAP: CategoriaDTO[] = [];
  usuario = this.usuarioService.getUser();

  constructor() {
    this.apiService.listCategorias().subscribe({
      next: (response) => {
        this.CATEGORIAS_MAP = response;
      },
    });
  }

  mapTicketsDtoToDisplay(tickets: TicketDTO[]): DisplayTicket[] {
    return tickets.map((t) => ({
      id: t.id,
      usuarioId: t.usuarioId,
      categoria:
        this.CATEGORIAS_MAP[t.categoriaId - 1]?.nombre ?? "Desconocida",
      titulo: t.titulo,
      descripcion: t.descripcion,
      prioridad: t.prioridad,
      estado: t.estado,
      fechaCreacion: t.fechaCreacion,
      fechaCierre: t.fechaCierre,
    }));
  }

  mapTicketDtoToDisplay(ticket: TicketDTO): DisplayTicket {
    return {
      id: ticket.id,
      usuarioId: ticket.usuarioId,
      categoria:
        this.CATEGORIAS_MAP[ticket.categoriaId - 1]?.nombre ?? "Desconocida",
      titulo: ticket.titulo,
      descripcion: ticket.descripcion,
      prioridad: ticket.prioridad,
      estado: ticket.estado,
      fechaCreacion: ticket.fechaCreacion,
      fechaCierre: ticket.fechaCierre,
    };
  }

  mapComentariosDtoToDisplay(
    comentarios: ComentarioDTO[],
  ): DisplayComentario[] {
    return comentarios.map((c) => ({
      id: c.id,
      autor:
        c.usuarioId === this.usuario?.id
          ? "Yo"
          : (c.nombreUsuario ?? "Anonimo"),
      mensaje: c.comentario,
      date: c.fechaCreacion ? this.getDate(c.fechaCreacion) : "",
      time: c.fechaCreacion ? this.getTime(c.fechaCreacion) : "",
    }));
  }

  getDate(dateTime: string): string {
    return this.datePipe.transform(dateTime.replace(" ", "T"), "dd/MM/yyyy")!;
  }

  getTime(dateTime: string): string {
    const hora = this.datePipe.transform(dateTime.replace(" ", "T"), "hh:mm a");
    return hora ? hora.toLowerCase() : "";
  }
}
