import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';

interface LoginRequest { email: string; password: string; }

@Component({
  selector: 'app-test-endpoints',
  templateUrl: './test-endpoints.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class TestEndpointsComponent {
  loginRequest: LoginRequest = { email: 'test@empresa.com', password: '1234' };
  response: any = null;
  error: any = null;

  constructor(private auth: AuthService) { }

  testLogin() {
    this.auth.login(this.loginRequest).subscribe({
      next: (res) => {
        this.response = res;
        this.error = null;
        console.log('Login response:', res);
      },
      error: (err) => {
        this.error = err;
        this.response = null;
        console.error('Login failed:', err);
      }
    });
  }
}
