import { Injectable, inject } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { ApiService } from "./api.service";
import { Router } from "@angular/router";
import { UsuarioDTO } from "./api.service";
import { isPlatformBrowser } from "@angular/common";
import { PLATFORM_ID } from "@angular/core";

@Injectable({ providedIn: "root" })
export class AuthService {
  private tokenSubject = new BehaviorSubject<string | null>(null);
  token$ = this.tokenSubject.asObservable();
  private platformId = inject(PLATFORM_ID);

  constructor(
    private api: ApiService,
    private router: Router,
  ) {
    if (isPlatformBrowser(this.platformId)) {
      this.tokenSubject.next(localStorage.getItem("token"));
    }
  }

  login(credentials: {
    email: string;
    password: string;
  }): Observable<{ usuario: UsuarioDTO; token: string }> {
    return this.api.login(credentials).pipe(
      tap((response) => {
        if (isPlatformBrowser(this.platformId)) {
          localStorage.setItem("token", response.token);
          this.tokenSubject.next(response.token);
        }
      }),
    );
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem("token");
      this.tokenSubject.next(null);
    }
    this.router.navigate([""]);
  }

  isLoggedIn(): boolean {
    return isPlatformBrowser(this.platformId)
      ? !!localStorage.getItem("token")
      : false;
  }

  getToken(): string | null {
    return this.tokenSubject.value;
  }

  getUserIdFromToken(): number | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payloadPart = token.split(".")[1];
      const base64 = payloadPart.replace(/-/g, "+").replace(/_/g, "/");
      const padded = base64.padEnd(base64.length + (4 - (base64.length % 4)) % 4, "=");
      const json = isPlatformBrowser(this.platformId)
        ? atob(padded)
        : Buffer.from(padded, 'base64').toString('utf-8');
      const payload = JSON.parse(json);
      const sub = payload?.sub ?? payload?.userId ?? payload?.id;
      const id = typeof sub === 'string' ? parseInt(sub, 10) : sub;
      return Number.isFinite(id) ? id : null;
    } catch {
      return null;
    }
  }

  getRoleFromToken(): string | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payloadPart = token.split(".")[1];
      const base64 = payloadPart.replace(/-/g, "+").replace(/_/g, "/");
      const padded = base64.padEnd(base64.length + (4 - (base64.length % 4)) % 4, "=");
      const json = isPlatformBrowser(this.platformId)
        ? atob(padded)
        : Buffer.from(padded, 'base64').toString('utf-8');
      const payload = JSON.parse(json);
      const role = payload?.rol ?? payload?.role ?? payload?.authority ?? null;
      return typeof role === 'string' ? role.toLowerCase() : null;
    } catch {
      return null;
    }
  }
}
