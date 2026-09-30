package com.parcial.paciente.controller;

import com.parcial.paciente.dto.MessageResponse;
import com.parcial.paciente.dto.PacienteDTO;
import com.parcial.paciente.service.PacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // =================================================================
    // ENDPOINT DE EJEMPLO YA RESUELTO (no se califica).
    // GET  http://localhost:8080/pacientes
    // =================================================================
    @GetMapping
    public List<PacienteDTO> getAllPacientes() {
        return pacienteService.findAll();
    }

    // =================================================================
    // TODO 1 - MOSTRAR
    // Verbo y ruta:  GET  /pacientes/mostrarActivos
    // Debe devolver: List<PacienteDTO> con los pacientes activos.
    // Agregue la anotacion que corresponde y llame al servicio.
    // =================================================================
    @GetMapping("/mostrarActivos")
    public List<PacienteDTO> mostrarActivos() {
        return pacienteService.mostrarActivos();
    }

    // =================================================================
    // TODO 2 - GUARDAR
    // Verbo y ruta:  POST  /pacientes
    // Recibe el PacienteDTO en el cuerpo de la peticion.
    // Si todo sale bien responde 200 con:
    //      new MessageResponse("Paciente creado con exito")
    // Si ocurre un error responde 400 (HttpStatus.BAD_REQUEST) con:
    //      new MessageResponse("Error al crear el paciente")
    // Use try / catch.
    // =================================================================
    @PostMapping
    public ResponseEntity<MessageResponse> crearPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try {
            pacienteService.crearPaciente(pacienteDTO);
            return ResponseEntity.ok(new MessageResponse("Paciente creado con exito"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al crear el paciente"));
        }
    }

    // =================================================================
    // TODO 3 - MODIFICAR
    // Verbo y ruta:  PUT  /pacientes/{idPaciente}
    // Recibe el id en la ruta y el PacienteDTO en el cuerpo.
    // Exito -> 200 con "Paciente actualizado con exito"
    // Error -> 400 con "Error al actualizar el paciente"
    // =================================================================
    @PutMapping("/{idPaciente}")
    public ResponseEntity<MessageResponse> actualizarPaciente(@PathVariable Integer idPaciente, @RequestBody PacienteDTO pacienteDTO) {
        try {
            pacienteService.modificarPaciente(idPaciente, pacienteDTO);
            return ResponseEntity.ok(new MessageResponse("Paciente actualizado con exito"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al actualizar el paciente"));
        }
    }

    // =================================================================
    // TODO 4 - ANULAR
    // Verbo y ruta:  PUT  /pacientes/anular/{idPaciente}
    // Exito -> 200 con "Paciente anulado con exito"
    // Error -> 400 con "Error al anular el paciente"
    // =================================================================
    @PutMapping("/anular/{idPaciente}")
    public ResponseEntity<MessageResponse> anularPaciente(@PathVariable Integer idPaciente) {
        try {
            pacienteService.anularPaciente(idPaciente);
            return ResponseEntity.ok(new MessageResponse("Paciente anulado con exito"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al anular el paciente"));
        }
    }

}
