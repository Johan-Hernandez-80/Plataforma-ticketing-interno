import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { CardComponent } from '../../atoms/card/card.component';
import { MainButtonComponent } from '../../atoms/main-button/main-button.component';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../../../services/api.service';

@Component({
  selector: 'app-reporte-card',
  standalone: true,
  imports: [CommonModule, CardComponent, MainButtonComponent, RouterLink],
  templateUrl: './reporte-card.component.html',
  styleUrl: './reporte-card.component.css'
})
export class ReporteCardComponent implements OnInit {
  private api = inject(ApiService);
  private sanitizer = inject(DomSanitizer);

  pdfBlob: Blob | null = null;
  pdfUrl: SafeUrl | null = null;
  loading = true;

  ngOnInit(): void {
    this.cargarInforme();
  }

  cargarInforme(): void {
    this.loading = true;
    this.api.exportarInformePDF().subscribe({
      next: (blob) => {
        this.pdfBlob = blob;
        this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          URL.createObjectURL(blob)
        );
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al cargar el PDF', err);
        this.loading = false;
      }
    });
  }

  descargarPDF(): void {
    if (!this.pdfBlob) return;

    const url = window.URL.createObjectURL(this.pdfBlob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `informe-estadistico-${new Date().toISOString().slice(0, 10)}.pdf`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
  }
}
