import { Component, Input } from "@angular/core";

@Component({
  selector: "app-background",
  standalone: true,
  imports: [],
  templateUrl: "./background.component.html",
  styleUrl: "./background.component.css",
})
export class BackgroundComponent {
  @Input() type: "basic" | "pattern" = "basic";
}
