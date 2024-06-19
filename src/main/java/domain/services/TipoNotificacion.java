package domain.services;

import domain.incidentes.Incidente;
import domain.persona.Persona;

public interface TipoNotificacion {
    public boolean enviarAPersona(Persona persona, String mensaje);
}
