import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-text-box",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./text-box.component.html",
  styleUrl: "./text-box.component.css",
})
export class TextBoxComponent {
  @Input() width = "";
  @Input() height = "";
  @Input() message = "";

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width,
      height: this.height,
    };
  }
}
