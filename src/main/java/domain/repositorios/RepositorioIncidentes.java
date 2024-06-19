package domain.repositorios;

import domain.incidentes.Incidente;
import domain.services.Mensaje;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class RepositorioIncidentes {
    private static RepositorioIncidentes instancia = null;
    public static RepositorioIncidentes getInstance(){
        if(instancia==null){
            instancia = new RepositorioIncidentes();
        }
        return instancia;
    }
    List<Incidente> incidentes = new ArrayList<>();
}
