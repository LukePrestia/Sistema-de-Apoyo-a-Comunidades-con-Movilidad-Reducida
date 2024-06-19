package domain.repositorios;

import domain.services.Mensaje;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class RepositorioMensajesPendientes {
    private static RepositorioMensajesPendientes instancia = null;
    public static RepositorioMensajesPendientes getInstance(){
        if(instancia==null){
            instancia = new RepositorioMensajesPendientes();
        }
        return instancia;
    }
    List<MensajePendiente> mensajes = new ArrayList<>();
}
