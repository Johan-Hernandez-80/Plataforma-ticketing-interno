import { Component, OnInit, output, inject } from "@angular/core";
import { NotificationListItemComponent } from "../../atoms/notification-list-item/notification-list-item.component";
import { RouterModule } from "@angular/router";
import { ApiService } from "../../../../services/api.service";
import { UsuarioService } from "../../../../services/usuario.service";
import { NotificacionDTO } from "../../../../services/api.service";
import { DatePipe } from "@angular/common";

@Component({
  selector: "app-small-notification-list",
  standalone: true,
  imports: [NotificationListItemComponent, RouterModule],
  templateUrl: "./small-notification-list.component.html",
  styleUrl: "./small-notification-list.component.css",
  providers: [DatePipe],
})
export class SmallNotificationListComponent implements OnInit {
  private apiService = inject(ApiService);
  private usuarioService = inject(UsuarioService);
  private datePipe = inject(DatePipe);
  usuario = this.usuarioService.getUser();
  notifications: NotificacionDTO[] = [];
  closeEvent = output<void>();

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

  onClose() {
    this.closeEvent.emit();
  }

  getDate(dateTime: string): string {
    return this.datePipe.transform(dateTime.replace(" ", "T"), "dd/MM/yyyy")!;
  }

  getTime(dateTime: string): string {
    const hora = this.datePipe.transform(dateTime.replace(" ", "T"), "hh:mm a");
    return hora ? hora.toLowerCase() : "";
  }
}
