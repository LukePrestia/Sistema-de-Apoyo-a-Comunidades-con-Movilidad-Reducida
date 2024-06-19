package domain.ranking;

import domain.entidad.Entidad;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParEntidadInt {
    private Entidad entidad;
    private int aux;

    public ParEntidadInt(Entidad entidad, int aux) {
        this.entidad = entidad;
        this.aux = aux;
    }

}
