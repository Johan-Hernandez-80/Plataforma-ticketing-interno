import { TicketDTO } from "../services/api.service";

export interface Ticket {
  id: number;
  titulo: string;
  categoria: string;
  estado: 'Pendiente' | 'En Progreso' | 'Cerrado';
  prioridad: 'Urgente' | 'Importante' | 'Programado';
  fecha: string;
}
export const SAMPLE_TICKETS_DTO: TicketDTO[] = [
  {
    id: 4,
    usuarioId: 3,
    agenteId: 2,
    categoriaId: 1,
    titulo: "La impresora no imprime",
    descripcion: "Ya la apague y volvi a encender y no funciono",
    prioridad: "Urgente",
    estado: "Cerrado",
    fechaCreacion: "2025-10-19 13:15:01",
    fechaCierre: "2025-10-19 17:45:33",
  },
  {
    id: 4,
    usuarioId: 3,
    agenteId: 2,
    categoriaId: 1,
    titulo: "La impresora no imprime",
    descripcion: "Ya la apague y volvi a encender y no funciono",
    prioridad: "Urgente",
    estado: "Cerrado",
    fechaCreacion: "2025-10-19 13:15:01",
    fechaCierre: "2025-10-19 17:45:33",
  },
  {
    id: 4,
    usuarioId: 3,
    agenteId: 2,
    categoriaId: 1,
    titulo: "La impresora no imprime",
    descripcion: "Ya la apague y volvi a encender y no funciono",
    prioridad: "Urgente",
    estado: "Cerrado",
    fechaCreacion: "2025-10-19 13:15:01",
    fechaCierre: "2025-10-19 17:45:33",
  },
]

export const SAMPLE_TICKETS: Ticket[] = [
  {
    id: 1,
    titulo: 'Problema de acceso a la cuenta',
    categoria: 'Acceso y Seguridad',
    estado: 'Pendiente',
    prioridad: 'Urgente',
    fecha: '06/09/2025'
  },
  {
    id: 2,
    titulo: 'Error al cargar página principal',
    categoria: 'UI',
    estado: 'En Progreso',
    prioridad: 'Importante',
    fecha: '07/09/2025'
  },
  {
    id: 3,
    titulo: 'Fallo en login con SSO',
    categoria: 'Acceso y Seguridad',
    estado: 'Cerrado',
    prioridad: 'Urgente',
    fecha: '05/09/2025'
  },
  {
    id: 4,
    titulo: 'Solicitud de nuevo reporte',
    categoria: 'Reportes',
    estado: 'Pendiente',
    prioridad: 'Programado',
    fecha: '08/09/2025'
  },
  {
    id: 5,
    titulo: 'Botón de guardar no responde',
    categoria: 'UI',
    estado: 'En Progreso',
    prioridad: 'Importante',
    fecha: '07/09/2025'
  }
];
