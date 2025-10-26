import { Component } from "@angular/core";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { LogOutButtonComponent } from "../../atoms/log-out-button/log-out-button.component";

@Component({
  selector: "app-header",
  standalone: true,
  imports: [MainButtonComponent, LogOutButtonComponent],
  templateUrl: "./header.component.html",
  styleUrl: "./header.component.css",
})
export class HeaderComponent {
  isPerfilHover = false;

  setIsPerfilHover(state: boolean) {
    this.isPerfilHover = state;
  }
}
