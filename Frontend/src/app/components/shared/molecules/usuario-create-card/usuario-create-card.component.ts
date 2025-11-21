import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardComponent } from '../../atoms/card/card.component';
import { MainButtonComponent } from '../../atoms/main-button/main-button.component';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../../../services/api.service';

interface RolOption {
  nombre: string;
  id: number;
}

@Component({
  selector: 'app-usuario-create-card',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    CardComponent,
    MainButtonComponent,
    RouterLink,
  ],
  templateUrl: './usuario-create-card.component.html',
  styleUrl: './usuario-create-card.component.css',
})
export class UsuarioCreateCardComponent {
  private apiService = inject(ApiService);

  departamentos: string[] = [];

  roles: RolOption[] = [
    { nombre: 'Agente', id: 2 },
    { nombre: 'Empleado', id: 3 }
  ];

  form = {
    nombre: '',
    emailCorporativo: '',
    emailPersonal: '',
    rolSeleccionado: this.roles[0],
    departamento: null as string | null,
  };

  isLoading = false;

  ngOnInit(): void {
    this.loadDepartamentos();
  }

  loadDepartamentos() {
    this.apiService.getAllDepartamentos().subscribe({
      next: (data: string[]) => {
        this.departamentos = data;
      },
      error: (err) => {
        console.error('Error al cargar departamentos', err);
        alert('No se pudieron cargar los departamentos');
      }
    });
  }

  registrarUsuario() {
    if (!this.form.nombre?.trim()) {
      alert('El nombre es obligatorio');
      return;
    }
    if (!this.form.emailCorporativo?.trim()) {
      alert('El email corporativo es obligatorio');
      return;
    }
    if (!this.form.departamento) {
      alert('Debe seleccionar un departamento');
      return;
    }

    this.isLoading = true;

    const payload = {
      nombre: this.form.nombre.trim(),
      emailCorporativo: this.form.emailCorporativo.trim(),
      emailPersonal: this.form.emailPersonal?.trim() || '',
      rolId: this.form.rolSeleccionado.id,
      departamento: this.form.departamento,
      contrasena: this.generateTempPassword()
    };

    this.apiService.createUsuario(payload).subscribe({
      next: () => {
        alert('¡Usuario creado exitosamente!');
        this.resetForm();   // ← now works
      },
      error: (err) => {
        console.error('Error al crear usuario', err);
        alert('Error al crear el usuario');
      },
      complete: () => this.isLoading = false
    });
  }

  private generateTempPassword(): string {
    return Math.random().toString(36).slice(-8) + 'A1!';
  }

  private resetForm() {
    this.form = {
      nombre: '',
      emailCorporativo: '',
      emailPersonal: '',
      rolSeleccionado: this.roles[0],
      departamento: null
    };
  }
}
