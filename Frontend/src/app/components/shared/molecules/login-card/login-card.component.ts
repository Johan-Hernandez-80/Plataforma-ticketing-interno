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
          if (response.usuario.rolId === 1) {
            this.router.navigate(["/admin/home"]);
          } else if (response.usuario.rolId === 2) {
            this.router.navigate(["/agent/home"]);
          } else if (response.usuario.rolId === 3) {
            this.router.navigate(["/home"]);
          }
        }
      },
      error: (err) => {
        alert(err);
      },
    });
  }
}
