import { Component, Input, output } from "@angular/core";
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
  click = output<void>();

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width ? this.width : "",
      height: this.height ? this.height : "",
    };
  }

  onClick() {
    this.click.emit();
  }
}
