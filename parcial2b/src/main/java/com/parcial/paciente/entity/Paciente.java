package com.parcial.paciente.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * ENTIDAD ENTREGADA POR EL CATEDRATICO - NO MODIFICAR.
 * Mapea la tabla "paciente" de la base de datos parcial2b.
 */
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PACIENTE")
    private Integer idPaciente;

    @Column(name = "ESTADO")
    private Boolean estado;

    @Size(max = 65)
    @Column(name = "NOMBRE")
    private String nombre;

    @Size(max = 13)
    @Column(name = "DPI")
    private String dpi;

    @Size(max = 15)
    @Column(name = "TELEFONO")
    private String telefono;

    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;

    public Paciente() {
    }

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "com.parcial.paciente.entity.Paciente[ idPaciente=" + idPaciente + " ]";
    }

}
