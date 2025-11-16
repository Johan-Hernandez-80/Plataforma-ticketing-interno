import {
  Component,
  EventEmitter,
  Input,
  Output,
  OnChanges,
} from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-main-button",
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: "./main-button.component.html",
  styleUrl: "./main-button.component.css",
})
export class MainButtonComponent implements OnChanges {
  @Input() routerLink: any = null;
  @Input() placeHolder = "";
  @Input() iconUrl = "";
  @Input() alt = "";
  @Input() width = "";
  @Input() height = "";
  // Horizontal spacing controls
  @Input() paddingLeft = "";
  @Input() paddingRight = "";
  @Input() marginLeft = "";
  @Input() marginRight = "";
  @Input() variant: "primary" | "secondary" | "primary-outline" = "primary";
  @Output() clicked = new EventEmitter<void>();

  customStyles: any = {};

  ngOnChanges(): void {
    this.customStyles = {
      width: this.width ? this.width : "",
      height: this.height ? this.height : "",
      paddingLeft: this.paddingLeft ? this.paddingLeft : "",
      paddingRight: this.paddingRight ? this.paddingRight : "",
      marginLeft: this.marginLeft ? this.marginLeft : "",
      marginRight: this.marginRight ? this.marginRight : "",
    };
  }

  onButtonClick(event: MouseEvent) {
    if (!this.routerLink) {
      event.preventDefault();
    }
    this.clicked.emit();
  }
}
