import { Component } from "@angular/core";
import { BackgroundComponent } from "../../shared/atoms/background/background.component";
import { HeaderComponent } from "../../shared/molecules/header/header.component";
import { AgenteListCardComponent } from "../../shared/molecules/agente-list-card/agente-list-card.component";

@Component({
  selector: 'app-asignar-agente-page',
  standalone: true,
  imports: [BackgroundComponent, HeaderComponent, AgenteListCardComponent],
  templateUrl: './asignar-agente-page.component.html',
  styleUrl: './asignar-agente-page.component.css'
})
export class AsignarAgentePageComponent {

}
