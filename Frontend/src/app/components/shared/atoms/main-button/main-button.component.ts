import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-main-button",
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: "./main-button.component.html",
  styleUrl: "./main-button.component.css",
})
export class MainButtonComponent {
  @Input() routerLink: string | null = null;
  @Input() placeHolder = "";
  @Input() iconUrl = "";
  @Input() alt = "";
  @Input() width: string = "";
  @Input() height: string = "";
  @Input() variant: "primary" | "secondary" | "primary-outline" = "primary";
  @Output() clicked = new EventEmitter<void>();

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width ? this.width : "",
      height: this.height ? this.height : "",
    };
  }

  onButtonClick(event: MouseEvent) {
    // If there's no navigation target, prevent default anchor behavior
    if (!this.routerLink) {
      event.preventDefault();
    }
    this.clicked.emit();
  }
}
