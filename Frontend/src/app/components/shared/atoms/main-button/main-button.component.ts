import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-main-button",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./main-button.component.html",
  styleUrl: "./main-button.component.css",
})
export class MainButtonComponent {
  @Input() placeHolder = "";
  @Input() iconUrl = "";
  @Input() alt = "";
  @Input() width: string = "";
  @Input() height: string = "";
  @Input() type: "primary" | "secondary" | "primary-outline" = "primary";

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width ? this.width : "",
      height: this.height ? this.height : "",
    };
  }
}
