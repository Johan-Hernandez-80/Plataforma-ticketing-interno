import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { ApiService } from './api.service';
import { Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenSubject = new BehaviorSubject<string | null>(null);
  token$ = this.tokenSubject.asObservable();
  private platformId = inject(PLATFORM_ID);

  constructor(private api: ApiService, private router: Router) {
    if (isPlatformBrowser(this.platformId)) {
      this.tokenSubject.next(localStorage.getItem('token'));
    }
  }

  login(credentials: { email: string; password: string }): Observable<{ token: string }> {
    return this.api.login(credentials).pipe(
      tap(response => {
        if (isPlatformBrowser(this.platformId)) {
          localStorage.setItem('token', response.token);
          this.tokenSubject.next(response.token);
        }
      })
    );
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
      this.tokenSubject.next(null);
    }
    this.router.navigate(['']);
  }

  isLoggedIn(): boolean {
    return isPlatformBrowser(this.platformId) ? !!localStorage.getItem('token') : false;
  }

  getToken(): string | null {
    return this.tokenSubject.value;
  }
}
