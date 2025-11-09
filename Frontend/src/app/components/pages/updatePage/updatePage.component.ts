import { Component } from '@angular/core';
import { BackgroundComponent } from '../../shared/atoms/background/background.component';
import { UpdateCardComponent } from '../../shared/molecules/update-card/update-card.component';
import { ConfirmUpdateModalComponent } from '../../shared/molecules/confirm-update-modal/confirm-update-modal.component';

@Component({
  selector: 'app-update-page',
  standalone: true,
  imports: [BackgroundComponent, UpdateCardComponent, ConfirmUpdateModalComponent],
  templateUrl: './updatePage.component.html',
  styleUrls: ['./updatePage.component.css']
})
export class UpdatePageComponent {
  showModal = false;
  modalType: 'email' | 'password' = 'email';
  modalValue = '';

  onRequestConfirm(evt: { type: 'email' | 'password'; payload: any }) {
    this.modalType = evt.type;
    this.modalValue = evt.type === 'email' ? evt.payload?.newEmail ?? '' : '';
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
