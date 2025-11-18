import { Component, OnInit, inject } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { CardComponent } from "../../shared/atoms/card/card.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { ComboBoxComponent } from "../../shared/atoms/combo-box/combo-box.component";
import { TextInputComponent } from "../../shared/atoms/text-input/text-input.component";
import { FileUploadCardComponent } from "../../shared/molecules/file-upload-card/file-upload-card.component";
import { Router } from "@angular/router";
import { ApiService, CategoriaDTO, TicketDTO } from "../../../services/api.service";
import { UsuarioService } from "../../../services/usuario.service";

@Component({
  selector: "app-ticket-create-page",
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    BackgroundComponent,
    HeaderComponent,
    CardComponent,
    MainButtonComponent,
    ComboBoxComponent,
    TextInputComponent,
    FileUploadCardComponent,
  ],
  templateUrl: "./ticket-create-page.component.html",
  styleUrl: "./ticket-create-page.component.css",
})
export class TicketCreatePageComponent implements OnInit {
  private router = inject(Router);
  private api = inject(ApiService);
  private usuarioService = inject(UsuarioService);

  motivo = "";
  descripcion = "";
  categoria = "";
  categoriaId: number | null = null;
  prioridad = "";
  evidencia?: File;

  categoriasDTO: CategoriaDTO[] = [];
  categorias: string[] = [];

  prioridades = ["Urgente", "Programado", "Importante"];

  ngOnInit(): void {
    this.api.listCategorias().subscribe({
      next: (list) => {
        this.categoriasDTO = list ?? [];
        this.categorias = this.categoriasDTO.map((c) => c.nombre);
        if (this.categoriasDTO.length > 0) {
          // Preseleccionar la primera categoría para evitar estado inválido
          this.categoria = this.categoriasDTO[0].nombre;
          this.categoriaId = this.categoriasDTO[0].id ?? null;
        }
      },
      error: (err: unknown) => {
        console.error(err);
      },
    });

    // Preseleccionar prioridad por defecto
    if (!this.prioridad && this.prioridades.length > 0) {
      this.prioridad = this.prioridades[0];
    }
  }

  onBack() {
    this.router.navigate(["/home/user"]);
  }

  onCategoriaChange(value: string) {
    this.categoria = value;
    const norm = (s: string | undefined) => (s ?? "").toString().trim().toLowerCase();
    const match = this.categoriasDTO.find((c) => norm(c.nombre) === norm(value));
    this.categoriaId = match?.id ?? null;
  }

  onPrioridadChange(value: string) {
    this.prioridad = value;
  }

  onCrear() {
    const user = this.usuarioService.getUser();
    if (!user || !user.id) {
      alert("No se encontró el usuario actual. Inicia sesión nuevamente.");
      return;
    }
    if (!this.motivo.trim()) {
      alert("Ingresa el motivo del ticket.");
      return;
    }
    if (!this.descripcion.trim()) {
      alert("Ingresa la descripción del problema.");
      return;
    }
    if (this.categoriaId === null || this.categoriaId === undefined) {
      alert("Selecciona una categoría válida.");
      return;
    }
    if (!this.prioridad) {
      alert("Selecciona una prioridad.");
      return;
    }

    const payload: TicketDTO = {
      usuarioId: user.id,
      categoriaId: this.categoriaId,
      titulo: this.motivo.trim(),
      descripcion: this.descripcion.trim(),
      prioridad: this.prioridad,
      estado: "Pendiente",
    };

    this.api.createTicket(payload).subscribe({
      next: () => {
        alert("Ticket creado correctamente.");
        this.router.navigate(["/home/user"]);
      },
      error: (err: unknown) => {
        console.error(err);
        alert("No se pudo crear el ticket. Intenta nuevamente.");
      },
    });
  }

  onFileSelected(file: File) {
    this.evidencia = file;
  }

  onFileRejected(reason: string) {
    alert(reason);
  }
}
