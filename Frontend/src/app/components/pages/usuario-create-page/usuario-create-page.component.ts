import { Component } from '@angular/core';
import { UsuarioCreateCardComponent } from '../../shared/molecules/usuario-create-card/usuario-create-card.component';
import { BackgroundComponent } from '../../shared/atoms/background/background.component';

@Component({
  selector: 'app-usuario-create-page',
  standalone: true,
  imports: [UsuarioCreateCardComponent, BackgroundComponent],
  templateUrl: './usuario-create-page.component.html',
  styleUrl: './usuario-create-page.component.css'
})
export class UsuarioCreatePageComponent {

}
