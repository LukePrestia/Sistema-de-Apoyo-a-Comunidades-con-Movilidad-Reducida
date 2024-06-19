package domain.repositorios;

import domain.persona.Persona;

public class MensajePendiente {

    String mensaje;
    Persona persona;


    public MensajePendiente(String texto, Persona persona) {
        this.mensaje = texto;
        this.persona = persona;
    }
}
