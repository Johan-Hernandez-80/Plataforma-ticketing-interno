import { Component, inject } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { RouterLink } from "@angular/router";
import { UsuarioService } from "../../../../services/usuario.service";

@Component({
  selector: "app-profile-card",
  standalone: true,
  imports: [CardComponent, MainButtonComponent, RouterLink],
  templateUrl: "./profile-card.component.html",
  styleUrl: "./profile-card.component.css",
})
export class ProfileCardComponent {
  private usuarioService = inject(UsuarioService);
  usuario = this.usuarioService.getUser();

  getRol(): string{
    switch (this.usuario?.rolId){
      case 1: return 'Administrador'
      case 2: return 'Agente'
      case 3: return 'Empleado'
      default: return '';
    }
  }

  navBack(): string{
    switch(this.usuario?.rolId){
      case 1: return '/admin/home';
      case 2: return '/agent/home';
      case 3: return '/home';
      default: return '';
    }
  }
}
