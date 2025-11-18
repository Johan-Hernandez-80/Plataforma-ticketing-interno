import { Component, Input, Output, EventEmitter, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CardComponent } from '../../atoms/card/card.component';
import { MainButtonComponent } from '../../atoms/main-button/main-button.component';
import { TextInputComponent } from "../../atoms/text-input/text-input.component";
import { ApiService } from '../../../../services/api.service';
import { CategoriaDTO } from '../../../../services/api.service';

@Component({
  selector: 'app-new-categoria-modal',
  standalone: true,
  imports: [CardComponent, MainButtonComponent, TextInputComponent, FormsModule],
  templateUrl: './new-categoria-modal.component.html',
  styleUrl: './new-categoria-modal.component.css'
})
export class NewCategoriaModalComponent {
  @Input() confirmLabel = 'Confirmar';
  @Output() crear = new EventEmitter<void>();
  currentCategoria = '';
  private api = inject(ApiService);

  onConfirm() {
    if (!this.currentCategoria.trim()) return;

    const dto: CategoriaDTO = { nombre: this.currentCategoria };
    this.api.createCategoria(dto).subscribe({
      next: (created: CategoriaDTO) => this.crear.emit(),
      error: (err) => console.error('API error:', err)
    });
  }
}
