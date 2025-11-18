import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges } from '@angular/core';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router'; // Fixed import
import { TagComponent } from '../../atoms/tag/tag.component';
import { ApiService, TicketDTO } from '../../../../services/api.service';
import { Router } from '@angular/router';
import { DisplayTicket } from '../../../../services/api.service';
import { CategoriaDTO } from '../../../../services/api.service';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    RouterModule,
    TagComponent,
  ],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent implements OnChanges {
  @Input() data: TicketDTO[] = [];
  @Input() maxHeight: string = '600px';
  @Input() tableWidth: string = '1020px';
  @Input() isAdmin: boolean = false;

  displayedColumns = [
    "ID",
    "Titulo",
    "Categoria",
    "Estado",
    "Prioridad",
    "Fecha",
    "icono",
  ];
  dataSource = new MatTableDataSource<DisplayTicket>([]);

  constructor(private router: Router, private api: ApiService) { }

  ngOnChanges() {
    if (!this.data || this.data.length === 0) {
      this.dataSource.data = [];
      return;
    }

    const observables = this.data.map((t: TicketDTO) =>
      this.api.getCategoriaById(t.categoriaId).pipe(
        map((categoria: CategoriaDTO): DisplayTicket => ({
          id: t.id,
          usuarioId: t.usuarioId,
          categoria: categoria.nombre,
          titulo: t.titulo,
          descripcion: t.descripcion,
          prioridad: t.prioridad,
          estado: t.estado,
          fechaCreacion: t.fechaCreacion,
          fechaCierre: t.fechaCierre,
        }))
      )
    );

    forkJoin(observables).subscribe((displayTickets: DisplayTicket[]) => {
      this.dataSource.data = displayTickets;
    });

  }

  goToManagement(ticket: DisplayTicket) {
    let route = "/ticket/management";
    if (this.isAdmin === true) {
      route = "/admin/ticket/management";
    }
    this.router.navigate([route, ticket.id], {
      state: { ticket },
    });
  }
}
