import { Component, Input, output } from "@angular/core";

@Component({
  selector: "app-combo-box",
  standalone: true,
  imports: [],
  templateUrl: "./combo-box.component.html",
  styleUrl: "./combo-box.component.css",
})
export class ComboBoxComponent {
  @Input() options = ["ex1", "ex2", "ex3"];
  @Input() selected = "";
  change = output<string>();

  onChange(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.change.emit(select.value);
  }
}
