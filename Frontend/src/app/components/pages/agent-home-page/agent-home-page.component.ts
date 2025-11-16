import { Component, inject, OnInit } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { TableComponent } from "../../shared/molecules/table/table.component";
import { Router } from "@angular/router";
import {
  ApiService,
  DisplayTicket,
  TicketDTO,
} from "../../../services/api.service";
import { UsuarioService } from "../../../services/usuario.service";
import { MapperService } from "../../../services/mapper.service";

@Component({
  selector: "app-agent-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, TableComponent],
  templateUrl: "./agent-home-page.component.html",
  styleUrl: "./agent-home-page.component.css",
})
export class AgentHomePageComponent implements OnInit {
  private apiService = inject(ApiService);
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);
  private mapper = inject(MapperService);
  usuario = this.usuarioService.getUser();
  tickets: DisplayTicket[] = [];

  ngOnInit() {
    this.apiService
      .getTicketsFiltradosAgente(this.usuario?.id ?? -1)
      .subscribe({
        next: (response: TicketDTO[]) => {
          console.log("EXITO " + JSON.stringify(response));
          this.tickets = this.mapper.mapTicketsDtoToDisplay(response);
        },
        error: () => {
          console.log("ERRORRRR ");
        },
      });
  }

  onClickTable() {
    this.router.navigate(["/ticket/management"]);
  }
}
