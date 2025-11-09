import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { ProfileCardComponent } from "../../shared/molecules/profile-card/profile-card.component";

@Component({
  selector: "app-profile-page",
  standalone: true,
  imports: [BackgroundComponent, ProfileCardComponent],
  templateUrl: "./profilePage.component.html",
  styleUrls: ["./profilePage.component.css"],
})
export class ProfilePageComponent {}
