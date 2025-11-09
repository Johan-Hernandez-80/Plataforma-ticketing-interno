import { Component } from "@angular/core";
import { TextBoxComponent } from "../../atoms/text-box/text-box.component";
import { CardComponent } from "../../atoms/card/card.component";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-comment-card",
  standalone: true,
  imports: [TextBoxComponent, CardComponent, RouterModule],
  templateUrl: "./comment-card.component.html",
  styleUrl: "./comment-card.component.css",
})
export class CommentCardComponent {
  message = `
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Queremos informarte que estamos trabajando en tu solicitud y ya hemos realizado las primeras pruebas para identificar el origen del problema. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
    Nuestro equipo técnico ha detectado algunos comportamientos inusuales en el sistema y actualmente se encuentra analizando los registros para determinar la causa raíz. Te mantendremos informado con cada avance. 
`;
}
