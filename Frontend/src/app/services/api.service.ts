import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export interface LoginRequest { email: string; password: string; }
export interface RegisterRequest { name: string; email: string; password: string; role: string; }
export interface Usuario { id: number; rolId: number; nombre: string; emailPersonal: string; emailCorporativo: string; contrasena: string; departamento: string }

@Injectable({ providedIn: 'root' })
export class ApiService {
  private apiUrl = environment.apiUrl;
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) { }

  // AuthController
  login(credentials: LoginRequest): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/auth/login`, credentials, this.httpOptions)
      .pipe(catchError(err => throwError(() => err.error || 'Login failed')));
  }
}
