import { Component, Input, forwardRef } from "@angular/core";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
  selector: "app-text-input",
  standalone: true,
  imports: [],
  templateUrl: "./text-input.component.html",
  styleUrls: ["./text-input.component.css"],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TextInputComponent),
      multi: true,
    },
  ],
})
export class TextInputComponent implements ControlValueAccessor {
  /** Back-compat original prop */
  @Input() placeHolder = "";
  /** Preferred alias */
  @Input() placeholder = "";
  @Input() type = "text";

  value = "";
  disabled = false;

  private onChange: (v: string) => void = () => {};
  private onTouched: () => void = () => {};

  get effectivePlaceholder(): string {
    return this.placeholder || this.placeHolder || "";
  }

  // ControlValueAccessor methods
  writeValue(val: string | null): void {
    this.value = val ?? "";
  }
  registerOnChange(fn: (val: string) => void): void {
    this.onChange = fn;
  }
  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }
  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  onInput(event: Event) {
    const val = (event.target as HTMLInputElement).value;
    this.value = val;
    this.onChange(val);
  }

  onBlur() {
    this.onTouched();
  }
}
