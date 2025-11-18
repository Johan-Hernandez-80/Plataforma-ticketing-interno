import { Component, Input, Output, EventEmitter } from '@angular/core';
import { UsuarioDTO } from '../../../../services/api.service';

@Component({
  selector: 'app-agente-list-item',
  standalone: true,
  templateUrl: './agente-list-item.component.html',
  styleUrl: './agente-list-item.component.css'
})
export class AgenteListItemComponent {
  @Input() agent!: UsuarioDTO;
  @Output() reassign = new EventEmitter<UsuarioDTO>();

  assign() {
    this.reassign.emit(this.agent);
  }
}
