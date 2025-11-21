import { Component } from '@angular/core';
import { ReporteCardComponent } from '../../shared/molecules/reporte-card/reporte-card.component';
import { BackgroundComponent } from '../../shared/atoms/background/background.component';

@Component({
  selector: 'app-reporte-page',
  standalone: true,
  imports: [BackgroundComponent, ReporteCardComponent],
  templateUrl: './reporte-page.component.html',
  styleUrl: './reporte-page.component.css'
})
export class ReportePageComponent {

}
