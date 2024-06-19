package domain.incidentes;

import domain.persona.Persona;
import domain.repositorios.MensajePendiente;
import domain.repositorios.RepositorioMensajesPendientes;
import domain.services.Mensaje;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class SinApuros implements ModoNotificacion {



    @Override
    public String toString() {
        return "SinApuros";
    }

    @Override
    public void avisar(Incidente incidente, boolean rotura, Persona persona) {
        Mensaje mensaje = new Mensaje();
        String texto = mensaje.crearMensaje(incidente,rotura);
        RepositorioMensajesPendientes repositorioMensajesPendientes = new RepositorioMensajesPendientes();
        repositorioMensajesPendientes.getInstance().getMensajes().add(new MensajePendiente(texto,persona));
    }
}
