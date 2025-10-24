import { Component } from "@angular/core";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";

@Component({
  selector: "app-header",
  standalone: true,
  imports: [MainButtonComponent],
  templateUrl: "./header.component.html",
  styleUrl: "./header.component.css",
})
export class HeaderComponent {}
