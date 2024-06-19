package domain.entidad;
import domain.establecimientos.Estacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter

public class Linea extends Entidad {
    private List<Estacion> estaciones;
    private Estacion primeraEstacion;
    private Estacion ultimaEstacion;

}
