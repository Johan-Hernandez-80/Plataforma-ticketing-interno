import { Component, Input, OnChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { TagComponent } from '../../atoms/tag/tag.component';

interface Ticket {
  id: number;
  titulo: string;
  categoria: string;
  estado: string;
  prioridad: string;
  fecha: string;
}

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatIconModule, MatButtonModule, TagComponent],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent implements OnChanges {
  @Input() data: Ticket[] = [];
  displayedColumns = ['ID', 'Titulo', 'Categoria', 'Estado', 'Prioridad', 'Fecha', 'icono'];
  dataSource = new MatTableDataSource<Ticket>([]);

  ngOnChanges() {
    this.dataSource.data = this.data;
  }
}
