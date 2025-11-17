import { Component, inject } from '@angular/core';
import { BackgroundComponent } from '../../shared/atoms/background/background.component';
import { UpdateCardComponent } from '../../shared/molecules/update-card/update-card.component';
import { ConfirmModalComponent } from '../../shared/molecules/confirm-modal/confirm-modal.component';
import { ApiService, UsuarioDTO } from '../../../services/api.service';
import { UsuarioService } from '../../../services/usuario.service';

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
  private api: ApiService = inject(ApiService);
  private usuarioService: UsuarioService = inject(UsuarioService);
  private modalPayload: any = null;

  onRequestConfirm(evt: { type: 'email' | 'password'; payload: any }) {
    this.modalType = evt.type;
    this.modalValue = evt.type === 'email' ? evt.payload?.newEmail ?? '' : '';
    this.modalPayload = evt.payload ?? null;
    this.modalMessage = evt.type === 'password'
      ? '¿Estas seguro de que deseas actualizar tu contraseña?'
      : '¿Estas seguro de que deseas actualizar tu correo?';
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  confirmChange() {
    const user = this.usuarioService.getUser();
    if (!user || !user.id) {
      alert('No se encontró el usuario actual. Inicia sesión nuevamente.');
      this.showModal = false;
      return;
    }

    if (this.modalType === 'email') {
      const newEmail = (this.modalValue || '').trim();
      if (!newEmail) {
        alert('Ingresa un correo válido.');
        return;
      }
      this.api.updateUsuario(user.id, { emailPersonal: newEmail }).subscribe({
        next: (updated: UsuarioDTO) => {
          this.usuarioService.setUser(updated);
          this.showModal = false;
          alert('Correo actualizado correctamente.');
        },
        error: (err: unknown) => {
          console.error(err);
          alert('No se pudo actualizar el correo.');
        },
      });
    } else {
      const newPassword: string = this.modalPayload?.newPassword ?? '';
      const confirmPassword: string = this.modalPayload?.confirmPassword ?? '';
      if (!newPassword) {
        alert('Ingresa una nueva contraseña.');
        return;
      }
      if (newPassword !== confirmPassword) {
        alert('Las contraseñas no coinciden.');
        return;
      }
      this.api.updateUsuario(user.id, { contrasena: newPassword }).subscribe({
        next: (updated: UsuarioDTO) => {
          this.usuarioService.setUser(updated);
          this.showModal = false;
          alert('Contraseña actualizada correctamente.');
        },
        error: (err: unknown) => {
          console.error(err);
          alert('No se pudo actualizar la contraseña.');
        },
      });
    }
  }
}
