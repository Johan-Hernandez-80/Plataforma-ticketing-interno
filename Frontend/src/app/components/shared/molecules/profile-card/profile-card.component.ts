import { Component } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { RouterLink } from "@angular/router";

@Component({
  selector: "app-profile-card",
  standalone: true,
  imports: [CardComponent, MainButtonComponent, RouterLink],
  templateUrl: "./profile-card.component.html",
  styleUrl: "./profile-card.component.css",
})
export class ProfileCardComponent {
  user = {
    nombre: "Alejandro Montenegro Lozano",
    emailCorporativo: "amontenegro@acmecorp.com",
    emailPersonal: "alejandro.m.lozano@gmail.com",
    rol: "Usuario",
    departamento: "Finanzas",
  };
}
