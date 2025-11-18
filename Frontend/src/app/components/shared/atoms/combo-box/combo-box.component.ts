import { Component, Input, output } from "@angular/core";
import { FormsModule } from "@angular/forms";

interface ChangePayload {
  value?: string;
  event?: Event;
}

@Component({
  selector: "app-combo-box",
  standalone: true,
  imports: [FormsModule],
  templateUrl: "./combo-box.component.html",
  styleUrl: "./combo-box.component.css",
})
export class ComboBoxComponent {
  @Input() options = ["ex1", "ex2", "ex3"];
  @Input() selected = "";
  @Input() width = "";
  selectedChange = output<string>();
  change = output<ChangePayload>();

  onChange(value: string) {
    this.selected = value;
    this.selectedChange.emit(value); // <− esto actualiza al padre
  }
}
