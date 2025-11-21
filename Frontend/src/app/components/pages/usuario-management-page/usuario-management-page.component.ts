import { Component, OnInit } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { UsuariosTableComponent } from "../../shared/molecules/usuarios-table/usuarios-table.component";
import { MainButtonComponent } from "../../shared/atoms/main-button/main-button.component";
import { RouterLink } from "@angular/router";
import { ApiService, UsuarioDTO } from "../../../services/api.service";

@Component({
  selector: 'app-usuario-management-page',
  standalone: true,
  imports: [
    BackgroundComponent,
    HeaderComponent,
    UsuariosTableComponent,
    MainButtonComponent,
    RouterLink
  ],
  templateUrl: './usuario-management-page.component.html',
  styleUrl: './usuario-management-page.component.css'
})
export class UsuarioManagementPageComponent implements OnInit {
  usuarios: UsuarioDTO[] = [];

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.loadUsuarios();
  }

  private loadUsuarios(): void {
    this.api.getAllUsuarios().subscribe({
      next: (data) => this.usuarios = data ?? [],
      error: () => alert("Error al cargar los usuarios")
    });
  }
}
