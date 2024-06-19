package domain.establecimientos;

import domain.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Estacion extends Establecimiento {
    private String nombre;
    private String localizacion; //MUNICIPIO?
    public List<Servicio> servicios;
}
