package com.parcial.paciente.dto;

/**
 * CLASE ENTREGADA POR EL CATEDRATICO - NO MODIFICAR.
 * Es la respuesta estandar { "mensaje": "..." } que consume el front-end.
 */
public class MessageResponse {
    private String mensaje;

    public MessageResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
