import { Component, Input } from "@angular/core";

@Component({
  selector: "app-file-container",
  standalone: true,
  imports: [],
  templateUrl: "./file-container.component.html",
  styleUrl: "./file-container.component.css",
})
export class FileContainerComponent {
  @Input() fileExtension = "pdf";
  @Input() fileName = "archddddddddddivoddd.png";
}
