import { Component, inject } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { TextInputComponent } from "../../atoms/text-input/text-input.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { AuthService } from "../../../../services/auth.service";
import { Router } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { UsuarioService } from "../../../../services/usuario.service";

@Component({
  selector: "app-login-card",
  standalone: true,
  imports: [
    CardComponent,
    TextInputComponent,
    MainButtonComponent,
    FormsModule,
  ],
  templateUrl: "./login-card.component.html",
  styleUrl: "./login-card.component.css",
})
export class LoginCardComponent {
  private router = inject(Router);
  private authService = inject(AuthService);
  private usuarioService = inject(UsuarioService);
  credentials = {
    email: "",
    password: "",
  };

  onLogin() {
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        if (response.token && response.usuario) {
          this.usuarioService.setUser(response.usuario);
          const roleFromToken = this.authService.getRoleFromToken();
          const rolId = (response.usuario as any)?.rolId;
          if (rolId === 1 || roleFromToken === 'admin') {
            this.router.navigate(["/admin/home"]);
          } else if (rolId === 2 || roleFromToken === 'agent' || roleFromToken === 'agente') {
            this.router.navigate(["/agent/home"]);
          } else if (rolId === 3 || roleFromToken === 'user' || roleFromToken === 'empleado') {
            this.router.navigate(["/home/user"]);
          } else {
            // Fallback seguro: usuarios desconocidos a home de usuario
            this.router.navigate(["/home/user"]);
          }
        }
      },
      error: (err) => {
        alert(err);
      },
    });
  }
}
