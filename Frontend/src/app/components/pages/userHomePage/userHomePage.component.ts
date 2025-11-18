import { Component, OnInit, inject } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { Router } from "@angular/router";
import { ApiService, TicketDTO } from "../../../services/api.service";
import { UsuarioService } from "../../../services/usuario.service";
import { AuthService } from "../../../services/auth.service";

@Component({
  selector: "app-user-home-page",
  standalone: true,
  imports: [
    BackgroundComponent,
    HeaderComponent,
    TableComponent,
    MainButtonComponent,
  ],
  templateUrl: "./userHomePage.component.html",
  styleUrl: "./userHomePage.component.css",
})
export class UserHomePageComponent implements OnInit {
  router = inject(Router);
  private api = inject(ApiService);
  private usuarioService = inject(UsuarioService);
  private authService = inject(AuthService);
  tickets: TicketDTO[] = [];

  ngOnInit() {
    const user = this.usuarioService.getUser();
    const userId = user?.id ?? this.authService.getUserIdFromToken();
    if (userId == null) {
      this.tickets = [];
      return;
    }
    this.api.getTickets(undefined, undefined, userId).subscribe({
      next: (tickets) => (this.tickets = tickets ?? []),
      error: (err) => {
        const msg = (err as any)?.message || 'No fue posible cargar tus tickets';
        alert(msg);
        this.tickets = [];
      },
    });
  }

  onClickTable() {
    // La navegación por fila desde la tabla decide la ruta adecuada (según rol)
  }
}
