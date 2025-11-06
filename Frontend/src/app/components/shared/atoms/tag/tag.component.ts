import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tag',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tag.component.html',
  styleUrl: './tag.component.css'
})
export class TagComponent {
  @Input() value: string = '';
  @Input() type: 'estado' | 'prioridad' = 'estado';

  get tagClass(): string {
    const base = `tag tag--${this.type}`;
    if (this.type === 'estado') {
      return `${base}-${this.value.toLowerCase().replace(' ', '-')}`;
    }
    return `${base}-${this.value.toLowerCase()}`;
  }
}
