import { Component, inject, OnInit} from "@angular/core";
import { CardComponent } from "../../atoms/card/card.component";
import { NotificationListItemComponent } from "../../atoms/notification-list-item/notification-list-item.component";
import { RouterModule } from "@angular/router";
import { ApiService } from "../../../../services/api.service";
import { UsuarioService } from "../../../../services/usuario.service";
import { DatePipe } from "@angular/common";
import { NotificacionDTO } from "../../../../services/api.service";

@Component({
  selector: "app-large-notification-list-card",
  standalone: true,
  imports: [CardComponent, NotificationListItemComponent, RouterModule],
  templateUrl: "./large-notification-list-card.component.html",
  styleUrl: "./large-notification-list-card.component.css",
  providers: [
    DatePipe
  ]
})
export class LargeNotificationListCardComponent implements OnInit{
  private apiService = inject(ApiService);
  private usuarioService = inject(UsuarioService);
  private datePipe = inject(DatePipe);
  usuario = this.usuarioService.getUser();
  notifications: NotificacionDTO[] = [];

  navBack(): string{
    switch(this.usuario?.rolId){
      case 1: return '/admin/home';
      case 2: return '/agent/home';
      case 3: return '/home';
      default: return '';
    }
  }

  ngOnInit(): void {
    this.getNotifications();
  }

  getNotifications() {
    this.apiService.getNotificacionesById(this.usuario?.id ?? -1).subscribe({
      next: (data) => {
        this.notifications = data;
      },
      error: (err) => {
        alert("eror apa " + err);
      },
    });
  }

  getDate(dateTime: string): string {
    return this.datePipe.transform(dateTime.replace(" ", "T"), "dd/MM/yyyy")!;
  }

  getTime(dateTime: string): string {
    const hora = this.datePipe.transform(dateTime.replace(" ", "T"), "hh:mm a");
    return hora ? hora.toLowerCase() : "";
  }
}
