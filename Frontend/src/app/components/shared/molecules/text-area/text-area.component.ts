import { Component, output } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { BackgroundComponent } from "../../atoms/background/background.component";
import { FormsModule } from "@angular/forms";

interface ClosePayload {
  result: boolean;
  text: string;
}

@Component({
  selector: "app-text-area",
  standalone: true,
  imports: [
    CardComponent,
    MainButtonComponent,
    BackgroundComponent,
    FormsModule,
  ],
  templateUrl: "./text-area.component.html",
  styleUrl: "./text-area.component.css",
})
export class TextAreaComponent {
  close = output<ClosePayload>();
  currentText = "";

  onClose(result: boolean) {
    this.close.emit({
      result: result,
      text: result ? this.currentText : "",
    });
  }
}
