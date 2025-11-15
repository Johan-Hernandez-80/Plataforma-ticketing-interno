import { Component } from '@angular/core';
import { BackgroundComponent } from '../../shared/atoms/background/background.component';
import { UpdateCardComponent } from '../../shared/molecules/update-card/update-card.component';
import { ConfirmModalComponent } from '../../shared/molecules/confirm-modal/confirm-modal.component';

@Component({
  selector: 'app-update-page',
  standalone: true,
  imports: [BackgroundComponent, UpdateCardComponent, ConfirmModalComponent],
  templateUrl: './updatePage.component.html',
  styleUrls: ['./updatePage.component.css']
})
export class UpdatePageComponent {
  showModal = false;
  modalType: 'email' | 'password' = 'email';
  modalValue = '';
  modalMessage = '';

  onRequestConfirm(evt: { type: 'email' | 'password'; payload: any }) {
    this.modalType = evt.type;
    this.modalValue = evt.type === 'email' ? evt.payload?.newEmail ?? '' : '';
    this.modalMessage = evt.type === 'password'
      ? '¿Estas seguro de que deseas actualizar tu contraseña?'
      : '¿Estas seguro de que deseas actualizar tu correo?';
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  confirmChange() {
    // TODO: integrate with service; for now just close modal
    this.showModal = false;
  }
}
