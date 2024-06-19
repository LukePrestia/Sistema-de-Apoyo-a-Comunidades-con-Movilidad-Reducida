package domain.services.servicio3.rankings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaRanking {
    private int puesto;
    private Float puntaje;
    private String entidad;
    private Long entidadId;

    public EntradaRanking(Float puntaje, String entidad, Long entidadId) {
        this.puntaje = puntaje;
        this.entidad = entidad;
        this.entidadId = entidadId;
    }
}
