import { Component, Input } from "@angular/core";
import { NgClass } from "@angular/common";

@Component({
  selector: "app-background",
  standalone: true,
  imports: [NgClass],
  templateUrl: "./background.component.html",
  styleUrl: "./background.component.css",
})
export class BackgroundComponent {
  @Input() variant: "basic" | "pattern" | "dark-overlay" = "basic";
}
