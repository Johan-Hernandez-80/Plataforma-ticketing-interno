import { Component, Input } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-profile-button",
  standalone: true,
  imports: [RouterModule],
  templateUrl: "./profile-button.component.html",
  styleUrls: ["./profile-button.component.css"],
})
export class ProfileButtonComponent {
  @Input() link: string = "/profile";
  isFocus = false;

  changeIsFocus() {
    this.isFocus = !this.isFocus;
  }
}
