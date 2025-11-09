import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CardComponent } from '../../atoms/card/card.component';
import { MainButtonComponent } from '../../atoms/main-button/main-button.component';

@Component({
  selector: 'app-confirm-update-modal',
  standalone: true,
  imports: [CardComponent, MainButtonComponent],
  templateUrl: './confirm-update-modal.component.html',
  styleUrls: ['./confirm-update-modal.component.css']
})
export class ConfirmUpdateModalComponent {
  @Input() type: 'email' | 'password' = 'email';
  @Input() pendingValue: string = '';
  @Output() cancel = new EventEmitter<void>();
  @Output() confirm = new EventEmitter<void>();
}
