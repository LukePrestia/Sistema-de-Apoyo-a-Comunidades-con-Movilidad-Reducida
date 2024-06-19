package domain.incidentes;

import domain.persona.Persona;
import domain.services.Mensaje;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuandoSuceden implements ModoNotificacion{

    @Override
    public String toString() {
        return "CuandoSuceden";
    }
    @Override
    public void avisar(Incidente incidente, boolean rotura, Persona persona) {
        Mensaje mensaje = new Mensaje();
        String texto = new String();

        texto = mensaje.crearMensaje(incidente,rotura);
        persona.getMetodoAviso().enviarAPersona(persona,texto);
    }
}
