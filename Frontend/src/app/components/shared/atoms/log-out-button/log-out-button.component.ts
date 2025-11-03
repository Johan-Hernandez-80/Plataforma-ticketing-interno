import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-log-out-button",
  standalone: true,
  imports: [RouterModule],
  templateUrl: "./log-out-button.component.html",
  styleUrl: "./log-out-button.component.css",
})
export class LogOutButtonComponent {
  isFocus = false;

  changeIsFocus() {
    this.isFocus = !this.isFocus;
  }
}
