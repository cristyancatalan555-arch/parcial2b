export interface Paciente {
  idPaciente: number;
  estado: boolean;
  nombre: string;
  dpi: string | null;
  telefono: string | null;
  direccion: string | null;
}
export interface PacienteFormulario { nombre: string; dpi: string; telefono: string; direccion: string; }
export interface MessageResponse { mensaje: string; }
