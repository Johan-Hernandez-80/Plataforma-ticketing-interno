import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export interface LoginRequest { email: string; password: string; }
export interface UsuarioDTO {
  id?: number; rolId: number; nombre: string; emailPersonal: string;
  emailCorporativo: string; contrasena: string; departamento: string;
}
export interface TicketDTO {
  id?: number; usuarioId: number; agenteId?: number; categoriaId: number;
  titulo: string; descripcion: string; prioridad: string; estado: string;
  fechaCreacion?: string; fechaCierre?: string;
}
export interface PrioridadDTO { prioridad: string; }
export interface ComentarioDTO {
  id?: number; ticketId: number; usuarioId: number;
  comentario: string; fechaCreacion?: string;
}
export interface CategoriaDTO { id?: number; nombre: string; descripcion?: string; }
export interface NotificacionDTO { id?: number; usuarioId: number; mensaje: string; fechaCreacion?: string; }

@Injectable({ providedIn: 'root' })
export class ApiService {
  private apiUrl = environment.apiUrl;
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) { }

  // AuthController
  login(credentials: LoginRequest): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/auth/login`, credentials, this.httpOptions);
  }

  // UsuarioController
  getNotificacionesById(idUsuario: number): Observable<NotificacionDTO[]> {
    return this.http.get<NotificacionDTO[]>(`${this.apiUrl}/usuarios/${idUsuario}/notificaciones`, this.httpOptions);
  }
  updateUsuario(idUsuario: number, usuarioDTO: Partial<UsuarioDTO>): Observable<UsuarioDTO> {
    return this.http.patch<UsuarioDTO>(`${this.apiUrl}/usuarios/${idUsuario}`, usuarioDTO, this.httpOptions);
  }
  createUsuario(usuarioDTO: UsuarioDTO): Observable<UsuarioDTO> {
    return this.http.post<UsuarioDTO>(`${this.apiUrl}/usuarios`, usuarioDTO, this.httpOptions);
  }

  // TicketController
  createTicket(ticketDTO: TicketDTO): Observable<TicketDTO> {
    return this.http.post<TicketDTO>(`${this.apiUrl}/tickets`, ticketDTO, this.httpOptions);
  }

  getTickets(estado?: string, prioridad?: string, usuarioId?: number): Observable<TicketDTO[]> {
    let params = new HttpParams();
    if (estado) params = params.set('estado', estado);
    if (prioridad) params = params.set('prioridad', prioridad);
    if (usuarioId) params = params.set('usuarioId', usuarioId.toString());
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/tickets`, { ...this.httpOptions, params });
  }

  getTicketById(idTicket: number): Observable<TicketDTO> {
    return this.http.get<TicketDTO>(`${this.apiUrl}/tickets/${idTicket}`, this.httpOptions);
  }

  getComentariosByTicketId(idTicket: number): Observable<ComentarioDTO[]> {
    return this.http.get<ComentarioDTO[]>(`${this.apiUrl}/tickets/${idTicket}/comentarios`, this.httpOptions);
  }

  createComentario(ticketId: number, comentarioDTO: Omit<ComentarioDTO, 'id' | 'fechaCreacion'>): Observable<ComentarioDTO> {
    return this.http.post<ComentarioDTO>(`${this.apiUrl}/tickets/${ticketId}/comments`, comentarioDTO, this.httpOptions);
  }

  updatePrioridad(idTicket: number, prioridad: PrioridadDTO): Observable<TicketDTO> {
    return this.http.patch<TicketDTO>(`${this.apiUrl}/tickets/${idTicket}/prioridad`, prioridad, this.httpOptions);
  }

  cerrarTicket(idTicket: number): Observable<TicketDTO> {
    return this.http.patch<TicketDTO>(`${this.apiUrl}/tickets/${idTicket}/cerrar`, {}, this.httpOptions);
  }

  reasignarTicket(idTicket: number, agenteId: number): Observable<TicketDTO> {
    return this.http.post<TicketDTO>(`${this.apiUrl}/tickets/${idTicket}`, { agenteId }, this.httpOptions);
  }

  getTicketsFiltrados(estado?: string, prioridad?: string, agenteId?: number, fecha?: string): Observable<TicketDTO[]> {
    let params = new HttpParams();
    if (estado) params = params.set('estado', estado);
    if (prioridad) params = params.set('prioridad', prioridad);
    if (agenteId) params = params.set('agenteId', agenteId.toString());
    if (fecha) params = params.set('fecha', fecha);
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/tickets/filtered`, { ...this.httpOptions, params });
  }

  // CategoriaController
  createCategoria(dto: CategoriaDTO): Observable<CategoriaDTO> {
    return this.http.post<CategoriaDTO>(`${this.apiUrl}/categorias`, dto, this.httpOptions);
  }

  listCategorias(): Observable<CategoriaDTO[]> {
    return this.http.get<CategoriaDTO[]>(`${this.apiUrl}/categorias`, this.httpOptions);
  }

  getCategoriaById(id: number): Observable<CategoriaDTO> {
    return this.http.get<CategoriaDTO>(`${this.apiUrl}/categorias/${id}`, this.httpOptions);
  }

  updateCategoria(id: number, dto: CategoriaDTO): Observable<CategoriaDTO> {
    return this.http.put<CategoriaDTO>(`${this.apiUrl}/categorias/${id}`, dto, this.httpOptions);
  }

  deleteCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/categorias/${id}`, this.httpOptions);
  }

  // AgenteController
  getTicketsFiltradosAgente(agenteId: number, estado?: string, prioridad?: string, fecha?: string): Observable<TicketDTO[]> {
    let params = new HttpParams();
    if (estado) params = params.set('estado', estado);
    if (prioridad) params = params.set('prioridad', prioridad);
    if (fecha) params = params.set('fecha', fecha);
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/agentes/${agenteId}/tickets`, { ...this.httpOptions, params });
  }

  // AdminController
  getEstadisticas(): Observable<{ ticketsAbiertos: number; ticketsCerrados: number; empleadosActivos: number; agentesActivos: number }> {
    return this.http.get<any>(`${this.apiUrl}/admin/estadisticas`, this.httpOptions);
  }

  exportarInformePDF(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/admin/estadisticas/exportar/pdf`, { responseType: 'blob' });
  }
}
