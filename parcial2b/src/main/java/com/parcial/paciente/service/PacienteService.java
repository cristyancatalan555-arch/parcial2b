package com.parcial.paciente.service;

import com.parcial.paciente.dto.PacienteDTO;
import com.parcial.paciente.entity.Paciente;
import com.parcial.paciente.repository.PacienteRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // =================================================================
    // METODO DE EJEMPLO YA RESUELTO (no se califica).
    // Devuelve TODOS los pacientes, activos y anulados.
    // Uselo como guia para construir los metodos que faltan.
    // =================================================================
    public List<PacienteDTO> findAll() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // =================================================================
    // TODO 1 - MOSTRAR
    // Devuelva la lista de pacientes ACTIVOS (estado = true),
    // ordenados por idPaciente descendente, convertidos a PacienteDTO.
    // Use el metodo que declaro en PacienteRepository.
    // =================================================================
    public List<PacienteDTO> mostrarActivos() {
        return pacienteRepository.findByEstadoTrueOrderByIdPacienteDesc()
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // =================================================================
    // TODO 2 - GUARDAR
    // Inserte un nuevo paciente en la base de datos.
    // El registro nuevo SIEMPRE debe quedar con estado = true.
    // Devuelva el PacienteDTO ya guardado (con su idPaciente generado).
    // =================================================================
    public PacienteDTO crearPaciente(PacienteDTO dto) {
        return convertToDTO(pacienteRepository.saveAndFlush(convertToEntity(dto)));
    }

    // =================================================================
    // TODO 3 - MODIFICAR
    // Busque el paciente por su idPaciente. Si no existe, lance
    // RuntimeException con el mensaje: "El paciente no existe con id " + idPaciente
    // Si existe, actualice nombre, dpi, telefono y direccion, guarde y
    // devuelva el PacienteDTO actualizado.
    // OJO: no debe cambiar el estado del registro.
    // =================================================================
    public PacienteDTO modificarPaciente(Integer idPaciente, PacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("El paciente no existe con id " + idPaciente));
        paciente.setNombre(dto.getNombre());
        paciente.setDpi(dto.getDpi());
        paciente.setTelefono(dto.getTelefono());
        paciente.setDireccion(dto.getDireccion());
        return convertToDTO(pacienteRepository.saveAndFlush(paciente));
    }

    // =================================================================
    // TODO 4 - ANULAR (borrado logico)
    // Busque el paciente por su idPaciente. Si no existe, lance
    // RuntimeException con el mensaje: "El paciente no existe con id " + idPaciente
    // Si existe, cambie su estado a false, guarde y devuelva el DTO.
    // NO debe borrar fisicamente el registro de la tabla.
    // =================================================================
    public PacienteDTO anularPaciente(Integer idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("El paciente no existe con id " + idPaciente));
        paciente.setEstado(false);
        return convertToDTO(pacienteRepository.saveAndFlush(paciente));
    }

    // =================================================================
    // METODOS DE CONVERSION YA RESUELTOS - NO MODIFICAR
    // =================================================================
    private PacienteDTO convertToDTO(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setIdPaciente(p.getIdPaciente());
        dto.setEstado(p.getEstado());
        dto.setNombre(p.getNombre());
        dto.setDpi(p.getDpi());
        dto.setTelefono(p.getTelefono());
        dto.setDireccion(p.getDireccion());
        return dto;
    }

    private Paciente convertToEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNombre(dto.getNombre());
        paciente.setDpi(dto.getDpi());
        paciente.setTelefono(dto.getTelefono());
        paciente.setDireccion(dto.getDireccion());
        paciente.setEstado(true);
        return paciente;
    }

}
