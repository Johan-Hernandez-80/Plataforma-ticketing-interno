import { Component, Input, OnChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router'; // Fixed import
import { TagComponent } from '../../atoms/tag/tag.component';
import { TicketDTO } from '../../../../services/api.service';
import { Router } from '@angular/router';
import { DisplayTicket } from '../../../../services/api.service';

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
  @Input() data: DisplayTicket[] = [];
  @Input() maxHeight: string = '600px';
  @Input() tableWidth: string = '1020px';
  displayedColumns = ['ID', 'Titulo', 'Categoria', 'Estado', 'Prioridad', 'Fecha', 'icono'];
  dataSource = new MatTableDataSource<DisplayTicket>([]);

  constructor(private router: Router) { }

  ngOnChanges() {
    this.dataSource.data = this.data;
  }
  goToManagement(ticket: DisplayTicket) {
    this.router.navigate(['/ticket/management', ticket.id], { state: { ticket } });
  }
}
