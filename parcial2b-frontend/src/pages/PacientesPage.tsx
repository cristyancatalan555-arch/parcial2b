import { useEffect, useState } from 'react';
import type { ChangeEvent, FormEvent } from 'react';
import type { Paciente, PacienteFormulario } from '../types/Paciente';
import { mostrarActivos, guardarPaciente, modificarPaciente, anularPaciente, mensajeError } from '../services/pacienteService';

const vacio: PacienteFormulario = { nombre: '', dpi: '', telefono: '', direccion: '' };
export default function PacientesPage() {
  const [pacientes, setPacientes] = useState<Paciente[]>([]);
  const [formulario, setFormulario] = useState<PacienteFormulario>({ ...vacio });
  const [editando, setEditando] = useState<number | null>(null);
  const [mensaje, setMensaje] = useState('');
  const [error, setError] = useState('');
  const [cargando, setCargando] = useState(true);
  const [ocupado, setOcupado] = useState(false);
  async function recargar(): Promise<void> {
    setCargando(true);
    try { setPacientes(await mostrarActivos()); }
    catch (err: unknown) { setError(mensajeError(err)); }
    finally { setCargando(false); }
  }
  useEffect(() => { void recargar(); }, []);
  function cambiar(event: ChangeEvent<HTMLInputElement>) {
    const campo = event.target.name as keyof PacienteFormulario;
    setFormulario(actual => ({ ...actual, [campo]: event.target.value }));
  }
  function cancelar() { setEditando(null); setFormulario({ ...vacio }); }
  async function guardar(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setError(''); setMensaje('');
    if (!formulario.nombre.trim()) { setError('Ingresa el nombre del paciente.'); return; }
    setOcupado(true);
    try {
      const datos = { ...formulario, nombre: formulario.nombre.trim() };
      const respuesta = editando === null ? await guardarPaciente(datos) : await modificarPaciente(editando, datos);
      setMensaje(respuesta.mensaje); cancelar(); await recargar();
    } catch (err: unknown) { setError(mensajeError(err)); }
    finally { setOcupado(false); }
  }
  function editar(p: Paciente) {
    setEditando(p.idPaciente);
    setFormulario({ nombre: p.nombre, dpi: p.dpi ?? '', telefono: p.telefono ?? '', direccion: p.direccion ?? '' });
    setMensaje(''); setError('');
    document.getElementById('nombre')?.focus();
  }
  async function anular(p: Paciente) {
    if (!window.confirm(`¿Deseas anular al paciente ${p.nombre}?`)) return;
    setOcupado(true); setError(''); setMensaje('');
    try {
      const respuesta = await anularPaciente(p.idPaciente);
      setMensaje(respuesta.mensaje);
      if (editando === p.idPaciente) cancelar();
      await recargar();
    } catch (err: unknown) { setError(mensajeError(err)); }
    finally { setOcupado(false); }
  }
  return <main>
    <header><div><p className="eyebrow">PROGRAMACIÓN II · PARCIAL II</p><h1>Gestión de pacientes</h1><p>Registro y administración de pacientes activos</p></div>
      <div className="identidad"><strong>{import.meta.env.VITE_ESTUDIANTE_NOMBRE}</strong><span>Carné: {import.meta.env.VITE_ESTUDIANTE_CARNET}</span></div></header>
    {mensaje && <div className="mensaje exito" role="status">{mensaje}</div>}
    {error && <div className="mensaje error" role="alert">{error}</div>}
    <section className="panel"><h2>{editando === null ? 'Nuevo paciente' : `Modificar paciente #${editando}`}</h2>
      <form onSubmit={guardar}><fieldset disabled={ocupado}><div className="campos">
        <label htmlFor="nombre">Nombre completo *<input id="nombre" name="nombre" value={formulario.nombre} onChange={cambiar} maxLength={65} required /></label>
        <label htmlFor="dpi">DPI<input id="dpi" name="dpi" value={formulario.dpi} onChange={cambiar} maxLength={13} /></label>
        <label htmlFor="telefono">Teléfono<input id="telefono" name="telefono" value={formulario.telefono} onChange={cambiar} maxLength={15} /></label>
        <label htmlFor="direccion">Dirección<input id="direccion" name="direccion" value={formulario.direccion} onChange={cambiar} maxLength={100} /></label>
      </div><div className="acciones"><button type="submit">{ocupado ? 'Procesando…' : editando === null ? 'Guardar paciente' : 'Guardar cambios'}</button>
      {editando !== null && <button type="button" className="secundario" onClick={cancelar}>Cancelar edición</button>}</div></fieldset></form></section>
    <section className="panel"><div className="titulo-lista"><h2>Pacientes activos <span className="contador">{pacientes.length}</span></h2><button className="secundario" disabled={ocupado || cargando} onClick={() => { setError(''); void recargar(); }}>Actualizar</button></div>
      {cargando ? <p role="status">Cargando pacientes…</p> : <div className="tabla"><table><thead><tr><th>ID</th><th>Nombre</th><th>DPI</th><th>Teléfono</th><th>Dirección</th><th>Estado</th><th>Acciones</th></tr></thead><tbody>
        {pacientes.map(p => <tr key={p.idPaciente}><td>{p.idPaciente}</td><td className="nombre">{p.nombre}</td><td>{p.dpi || '—'}</td><td>{p.telefono || '—'}</td><td>{p.direccion || '—'}</td><td><span className="estado">Activo</span></td><td><div className="botones"><button className="secundario" disabled={ocupado} onClick={() => editar(p)}>Modificar</button><button className="peligro" disabled={ocupado} onClick={() => void anular(p)}>Anular</button></div></td></tr>)}
        {pacientes.length === 0 && <tr><td colSpan={7}>No hay pacientes activos para mostrar.</td></tr>}
      </tbody></table></div>}</section>
    <footer>Los pacientes anulados permanecen en la base de datos.</footer>
  </main>;
}
