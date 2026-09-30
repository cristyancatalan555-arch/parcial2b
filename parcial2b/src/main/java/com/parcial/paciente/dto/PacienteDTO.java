package com.parcial.paciente.dto;

import lombok.Data;

/**
 * DTO ENTREGADO POR EL CATEDRATICO - NO MODIFICAR.
 */
@Data
public class PacienteDTO {
    private Integer idPaciente;
    private Boolean estado;
    private String nombre;
    private String dpi;
    private String telefono;
    private String direccion;
}
