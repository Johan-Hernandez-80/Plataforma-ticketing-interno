import { Component, EventEmitter, Output } from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { MainButtonComponent } from "../../atoms/main-button/main-button.component";
import { TextInputComponent } from "../../atoms/text-input/text-input.component";
import { RouterLink } from "@angular/router";
import { FormsModule } from "@angular/forms";

@Component({
    selector: "app-update-card",
    standalone: true,
    imports: [CardComponent, MainButtonComponent, TextInputComponent, RouterLink, FormsModule],
    templateUrl: "./update-card.component.html",
    styleUrls: ["./update-card.component.css"],
})
export class UpdateCardComponent {
    @Output() requestConfirm = new EventEmitter<{ type: 'email' | 'password'; payload: any }>();
    tab: 'email' | 'password' = 'email';


    currentPassword = '';
    newPassword = '';
    confirmPassword = '';
    newEmail = '';

    switchTab(tab: 'email' | 'password') {
        if (this.tab === tab) {
            return;
        }
        this.tab = tab;
    }

    confirm() {
        if (this.tab === 'password') {
            this.requestConfirm.emit({
                type: 'password',
                payload: {
                    currentPassword: this.currentPassword,
                    newPassword: this.newPassword,
                    confirmPassword: this.confirmPassword,
                },
            });
        } else {
            this.requestConfirm.emit({ type: 'email', payload: { newEmail: this.newEmail } });
        }
    }

    
}
