import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { ProfileAdminCardComponent } from "../../shared/molecules/profile-admin-card/profile-admin-card.component";

@Component({
  selector: 'app-usuario-detail-page',
  standalone: true,
  imports: [BackgroundComponent, ProfileAdminCardComponent],
  templateUrl: './usuario-detail-page.component.html',
  styleUrl: './usuario-detail-page.component.css'
})
export class UsuarioDetailPageComponent {

}
