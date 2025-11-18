// usuarios-table.component.ts
import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges } from '@angular/core';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { ApiService, UsuarioDTO } from '../../../../services/api.service';

interface DisplayUsuario {
  id: number;
  nombre: string;
  rol: string;
}

@Component({
  selector: 'app-usuarios-table',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './usuarios-table.component.html',
  styleUrl: './usuarios-table.component.css',
})
export class UsuariosTableComponent implements OnChanges {
  @Input() data: UsuarioDTO[] = [];
  @Input() tableWidth = '1080px';

  displayedColumns = ['id', 'nombre', 'rol', 'icono'];
  dataSource = new MatTableDataSource<DisplayUsuario>([]);

  constructor(private router: Router, private api: ApiService) { }

  ngOnChanges() {
    if (!this.data || this.data.length === 0) {
      this.dataSource.data = [];
      return;
    }

    const rolMap: Record<number, string> = {
      1: 'Administrador',
      2: 'Agente',
      3: 'Usuario',
    };

    const displayData: DisplayUsuario[] = this.data.map(u => ({
      id: u.id!,
      nombre: u.nombre,
      rol: rolMap[u.rolId] || 'Desconocido',
    }));

    this.dataSource.data = displayData;
  }

  goToUserDetail(user: DisplayUsuario) {
    this.router.navigate(['/admin/usuario/management', user.id]);
  }
}
