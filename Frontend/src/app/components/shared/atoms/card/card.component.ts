import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SimpleChanges } from "@angular/core";

@Component({
  selector: "app-card",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./card.component.html",
  styleUrl: "./card.component.css",
})
export class CardComponent {
  @Input() width: string = "";
  @Input() height: string = "";

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width ? this.width : "auto",
      height: this.height ? this.height : "auto",
    };
  }
}
