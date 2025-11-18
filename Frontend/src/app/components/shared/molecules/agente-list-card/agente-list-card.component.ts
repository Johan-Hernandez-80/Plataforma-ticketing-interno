import { Component, inject, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { ActivatedRoute } from "@angular/router";
import { ApiService, UsuarioDTO } from "../../../../services/api.service";
import { UsuarioService } from "../../../../services/usuario.service";
import { AgenteListItemComponent } from "../../atoms/agente-list-item/agente-list-item.component";
import { ValidationCardComponent } from "../validation-card/validation-card.component";
import { CardComponent } from "../../atoms/card/card.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-agente-list-card',
  standalone: true,
  imports: [CardComponent, AgenteListItemComponent, ValidationCardComponent, RouterModule, CommonModule],
  templateUrl: './agente-list-card.component.html',
  styleUrl: './agente-list-card.component.css'
})
export class AgenteListCardComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private apiService = inject(ApiService);
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);

  idTicket = Number(this.route.snapshot.paramMap.get("id"));
  agentes: UsuarioDTO[] = [];
  showReassignConfirm = false;
  selectedAgent: UsuarioDTO | null = null;

  ngOnInit() {
    this.loadAgents();
  }

  loadAgents() {
    this.apiService.getAllAgentes().subscribe({
      next: (response) => {
        this.agentes = (response ?? []).filter(u => u.rolId === 2);
      },
      error: () => alert("Error loading agents")
    });
  }

  requestReassign(agent: UsuarioDTO) {
    this.selectedAgent = agent;
    this.showReassignConfirm = true;
  }

  handleReassignConfirmation(confirmed: boolean) {
    console.log('Reassigning ticket', this.idTicket, 'to agent', this.selectedAgent?.id);
    this.showReassignConfirm = false;
    if (!confirmed || !this.selectedAgent?.id) return;

    this.apiService.reasignarTicket(this.idTicket, this.selectedAgent.id).subscribe({
      next: () => {
        alert(`Ticket reassigned to ${this.selectedAgent?.nombre}`);
        this.router.navigate(['/admin/ticket/management', this.idTicket]);
      },
      error: () => alert("Failed to reassign ticket")
    });
  }
}
