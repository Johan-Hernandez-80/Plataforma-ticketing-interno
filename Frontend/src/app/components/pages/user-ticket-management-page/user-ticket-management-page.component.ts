import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/molecules/header/header.component';
import { BackgroundComponent } from '../../shared/atoms/background/background.component';
import { TicketManageCardComponent } from '../../shared/molecules/ticket-manage-card/ticket-manage-card.component';
import { ConfirmModalComponent } from '../../shared/molecules/confirm-modal/confirm-modal.component';
import { MainButtonComponent } from '../../shared/atoms/main-button/main-button.component';

@Component({
    selector: 'app-user-ticket-management-page',
    standalone: true,
    imports: [HeaderComponent, BackgroundComponent, TicketManageCardComponent, ConfirmModalComponent, MainButtonComponent],
    templateUrl: './user-ticket-management-page.component.html',
    styleUrl: './user-ticket-management-page.component.css'
})
export class UserTicketManagementPageComponent {
    showCancelModal = false;
    cancelMessage = '¿Estas seguro de que deseas solicitar la cancelación de esta solicitud de soporte?';

    openCancel() { this.showCancelModal = true; }
    closeCancel() { this.showCancelModal = false; }
    confirmCancel() {
        // TODO: Integrar petición de cancelación
        alert('Solicitud de cancelación enviada (demo)');
        this.showCancelModal = false;
    }
}
