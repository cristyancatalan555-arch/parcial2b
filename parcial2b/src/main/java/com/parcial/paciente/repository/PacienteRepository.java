package com.parcial.paciente.repository;

import com.parcial.paciente.entity.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // =================================================================
    // TODO 1 (MOSTRAR): declare aqui el metodo derivado de Spring Data
    // que devuelva UNICAMENTE los pacientes con ESTADO = true,
    // ordenados por ID_PACIENTE de forma descendente.
    //
    // Pista: el nombre del metodo describe la consulta.
    //        findBy<Campo><Condicion>OrderBy<Campo>Desc
    // Debe devolver: List<Paciente>
    // =================================================================

    List<Paciente> findByEstadoTrueOrderByIdPacienteDesc();
}
