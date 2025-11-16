import { Component, Input, output } from "@angular/core";

interface ChangePayload {
  confirmation?: boolean;
  value?: string;
  event?: Event;
}

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
  @Input() width = "";
  change = output<ChangePayload>();

  onChange(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.change.emit({
      confirmation: undefined,
      value: select.value,
      event: event,
    });
  }
}
