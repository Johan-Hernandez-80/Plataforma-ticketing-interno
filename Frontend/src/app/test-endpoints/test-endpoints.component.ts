import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { forkJoin, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

interface EndpointResult {
  name: string;
  method: string;
  path: string;
  status: 'pending' | 'success' | 'error';
  response?: any;
  error?: string;
}

@Component({
  selector: 'app-test-endpoints',
  templateUrl: './test-endpoints.component.html',
  styleUrls: ['./test-endpoints.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class TestEndpointsComponent {
  results: EndpointResult[] = [];
  isTesting = false;
  loginRequest = { email: 'admin@empresa.com', password: 'admin123' };

  constructor(private api: ApiService) { }

  testAllEndpoints() {
    this.isTesting = true;
    this.results = this.getEndpointTests().map(test => ({
      name: test.name,
      method: test.method,
      path: test.path,
      status: 'pending' as const
    }));

    this.api.login(this.loginRequest).subscribe({
      next: (res: { token: string }) => {
        localStorage.setItem('token', res.token);
        this.runAllTests();
      },
      error: (err) => {
        this.isTesting = false;
        this.results = [{
          name: 'LOGIN',
          method: 'POST',
          path: '/auth/login',
          status: 'error',
          error: err.message || 'Login failed'
        }];
      }
    });
  }

  private runAllTests() {
    const tests = this.getEndpointTests();
    const observables = tests.map(test =>
      test.call().pipe(
        catchError(err => {
          const errorMsg = err?.message || err?.error || 'Request failed';
          return of({ error: errorMsg } as any);
        })
      )
    );

    forkJoin(observables).subscribe({
      next: (responses: any[]) => {
        responses.forEach((res, i) => {
          if (res && res.error) {
            this.results[i].status = 'error';
            this.results[i].error = res.error;
          } else {
            this.results[i].status = 'success';
            this.results[i].response = res;
          }
        });
      },
      error: () => {
        this.results.forEach(r => {
          if (r.status === 'pending') {
            r.status = 'error';
            r.error = 'Network error';
          }
        });
      },
      complete: () => {
        localStorage.removeItem('token');
        this.isTesting = false;
      }
    });
  }

  getSuccessCount(): number {
    return this.results.filter(r => r.status === 'success').length;
  }

  getResponseKeys(response: any): string[] {
    return response ? Object.keys(response) : [];
  }

  private getEndpointTests() {
    const adminId = 1;
    const agenteId = 2;
    const userId = 3;
    const categoriaId = 1;
    const ticketId = 1;

    return [
      // Auth
      { name: 'LOGIN', method: 'POST', path: '/auth/login', call: () => this.api.login(this.loginRequest) },
      // UsuarioController
      { name: 'GET Notificaciones', method: 'GET', path: `/usuarios/${adminId}/notificaciones`, call: () => this.api.getNotificacionesById(adminId) },
      { name: 'PATCH Usuario', method: 'PATCH', path: `/usuarios/${adminId}`, call: () => this.api.updateUsuario(adminId, { nombre: 'Jaime Updated' }) },
      {
        name: 'POST Crear Usuario', method: 'POST', path: '/usuarios', call: () => this.api.createUsuario({
          rolId: 3, nombre: 'Test User', emailPersonal: 'test@personal.com',
          emailCorporativo: 'test@empresa.com', contrasena: 'test123', departamento: 'Test'
        })
      },
      // TicketController
      {
        name: 'POST Crear Ticket', method: 'POST', path: '/tickets', call: () => this.api.createTicket({
          usuarioId: userId, categoriaId, titulo: 'Test Ticket', descripcion: 'Auto test',
          prioridad: 'Importante', estado: 'pendiente'
        })
      },
      { name: 'GET Tickets (filtrados)', method: 'GET', path: '/tickets?estado=Pendiente&prioridad=Programado&usuarioId=3', call: () => this.api.getTickets('Pendiente', 'Programado', 3) },
      { name: 'GET Ticket por ID', method: 'GET', path: `/tickets/${ticketId}`, call: () => this.api.getTicketById(ticketId) },
      { name: 'GET Comentarios', method: 'GET', path: `/tickets/${ticketId}/comentarios`, call: () => this.api.getComentariosByTicketId(ticketId) },
      {
        name: 'POST Comentario', method: 'POST', path: `/tickets/${ticketId}/comments`, call: () => this.api.createComentario(ticketId, {
          ticketId, usuarioId: agenteId, comentario: 'Auto comment'
        })
      },
      { name: 'PATCH Prioridad', method: 'PATCH', path: `/tickets/${ticketId}/prioridad`, call: () => this.api.updatePrioridad(ticketId, { prioridad: 'Urgente' }) },
      { name: 'PATCH Cerrar Ticket', method: 'PATCH', path: `/tickets/${ticketId}/cerrar`, call: () => this.api.cerrarTicket(ticketId) },
      { name: 'POST Reasignar', method: 'POST', path: `/tickets/${ticketId}`, call: () => this.api.reasignarTicket(ticketId, agenteId) },
      { name: 'GET Tickets Filtrados', method: 'GET', path: '/tickets/filtered?estado=Cerrado&agenteId=2', call: () => this.api.getTicketsFiltrados("Cerrado", "Urgente", 2, "2025-04-01") },
      // CategoriaController
      { name: 'POST Crear Categoría', method: 'POST', path: '/categorias', call: () => this.api.createCategoria({ nombre: 'Test Cat', descripcion: 'Auto' }) },
      { name: 'GET Todas Categorías', method: 'GET', path: '/categorias', call: () => this.api.listCategorias() },
      { name: 'GET Categoría por ID', method: 'GET', path: `/categorias/${categoriaId}`, call: () => this.api.getCategoriaById(categoriaId) },
      { name: 'PUT Actualizar Categoría', method: 'PUT', path: `/categorias/${categoriaId}`, call: () => this.api.updateCategoria(categoriaId, { nombre: 'Updated Cat' }) },
      {
        name: 'DELETE Categoría',
        method: 'DELETE',
        path: `/categorias/3`,
        call: () => this.api.deleteCategoria(3).pipe(
          catchError(() => of({ error: 'Expected 404 (non-existent)' } as any))
        )
      },
      // AgenteController
      { name: 'GET Tickets de Agente', method: 'GET', path: `/agentes/${agenteId}/tickets`, call: () => this.api.getTicketsFiltradosAgente(agenteId) },
      // AdminController
      { name: 'GET Estadísticas', method: 'GET', path: '/admin/estadisticas', call: () => this.api.getEstadisticas() },
      { name: 'GET Exportar PDF', method: 'GET', path: '/admin/estadisticas/exportar/pdf', call: () => this.api.exportarInformePDF() }
    ];
  }
}
