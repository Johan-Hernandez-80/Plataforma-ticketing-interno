import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CardComponent } from '../../atoms/card/card.component';
import { MainButtonComponent } from '../../atoms/main-button/main-button.component';

@Component({
  selector: 'app-confirm-modal',
  standalone: true,
  imports: [CardComponent, MainButtonComponent],
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.css'
})
export class ConfirmModalComponent {
  @Input() message: string = '';
  @Input() confirmLabel: string = 'Confirmar';
  @Input() cancelLabel: string = 'Cancelar';
  @Output() cancel = new EventEmitter<void>();
  @Output() confirm = new EventEmitter<void>();
}
