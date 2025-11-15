import { Component, inject } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { CardComponent } from "../../shared/atoms/card/card.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { ComboBoxComponent } from "../../shared/atoms/combo-box/combo-box.component";
import { TextInputComponent } from "../../shared/atoms/text-input/text-input.component";
import { FileContainerComponent } from "../../shared/atoms/file-container/file-container.component";
import { FileUploadCardComponent } from "../../shared/molecules/file-upload-card/file-upload-card.component";
import { Router } from "@angular/router";

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
    FileContainerComponent,
    FileUploadCardComponent,
  ],
  templateUrl: "./ticket-create-page.component.html",
  styleUrl: "./ticket-create-page.component.css",
})
export class TicketCreatePageComponent {
  private router = inject(Router);

  motivo = "";
  descripcion = "";
  categoria = "";
  prioridad = "";
  evidencia?: File;

  categorias = [
    "Acceso y seguridad",
    "Sistema y aplicaciones",
    "Infraestructura y red",
  ];

  prioridades = ["Urgente", "Programado", "Importante"];

  onBack() {
    this.router.navigate(["/home/user"]);
  }

  onCategoriaChange(value: string) {
    this.categoria = value;
  }

  onPrioridadChange(value: string) {
    this.prioridad = value;
  }

  onCrear() {
    // TODO: Integrar con API create ticket
    alert("Ticket creado (demo). Motivo: " + this.motivo);
    this.router.navigate(["/home/user"]);
  }

  onFileSelected(file: File) {
    this.evidencia = file;
  }

  onFileRejected(reason: string) {
    alert(reason);
  }
}
