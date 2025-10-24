import { Component, Input } from "@angular/core";

@Component({
  selector: "app-text-input",
  standalone: true,
  imports: [],
  templateUrl: "./text-input.component.html",
  styleUrl: "./text-input.component.css",
})
export class TextInputComponent {
  @Input() placeHolder = "";
  @Input() type = "text";
}
