import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";

@Component({
  selector: "app-home-page",
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent],
  templateUrl: "./homePage.component.html",
  styleUrl: "./homePage.component.css",
})
export class HomePageComponent {}
