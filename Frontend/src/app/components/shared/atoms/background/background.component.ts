import { Component, Input } from "@angular/core";

@Component({
  selector: "app-background",
  standalone: true,
  templateUrl: "./background.component.html",
  styleUrl: "./background.component.css",
})
export class BackgroundComponent {
  @Input() variant: "pattern" | "dark-overlay" | "default" = "default";
}
