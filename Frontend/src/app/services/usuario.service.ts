import { Injectable } from "@angular/core";
import { UsuarioDTO } from "./api.service";

@Injectable({
  providedIn: "root",
})
export class UsuarioService {
  private userData: UsuarioDTO | null = null; 

  setUser(data: UsuarioDTO) {
    localStorage.setItem('user', JSON.stringify(data))
    this.userData = data;
  }

  getUser(): UsuarioDTO | null {
    if(!this.userData){
      this.userData = JSON.parse(localStorage.getItem('user')??'');
    }
    return this.userData;
  }

  clearUser() {
    localStorage.removeItem('user');
    this.userData = null;
  }
}
