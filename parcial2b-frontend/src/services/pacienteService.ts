import axios from 'axios';
import { api } from '../api/axios';
import type { Paciente, PacienteFormulario, MessageResponse } from '../types/Paciente';
export async function mostrarActivos(): Promise<Paciente[]> {
  return (await api.get<Paciente[]>('/pacientes/mostrarActivos')).data;
}
export async function guardarPaciente(datos: PacienteFormulario): Promise<MessageResponse> {
  return (await api.post<MessageResponse>('/pacientes', datos)).data;
}
export async function modificarPaciente(id: number, datos: PacienteFormulario): Promise<MessageResponse> {
  return (await api.put<MessageResponse>(`/pacientes/${id}`, datos)).data;
}
export async function anularPaciente(id: number): Promise<MessageResponse> {
  return (await api.put<MessageResponse>(`/pacientes/anular/${id}`)).data;
}
export function mensajeError(error: unknown): string {
  if (axios.isAxiosError<MessageResponse>(error)) {
    return error.response?.data?.mensaje || 'No se pudo conectar con el servidor. Verifica que el backend esté iniciado.';
  }
  return 'Ocurrió un error inesperado.';
}
