package domain.incidentes;


import domain.persona.Persona;

public interface ModoNotificacion {
    public void avisar(Incidente incidente, boolean rotura, Persona persona);
}
