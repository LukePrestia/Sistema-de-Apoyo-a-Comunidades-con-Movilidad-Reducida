package domain.services.servicio3.comunidad;

import com.google.gson.annotations.SerializedName;
import domain.services.servicio3.incidentes.IncidentePrima;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class ComunidadPrima {
    @SerializedName("cantMiembrosAfectados")
    private int cantMiembrosAfectados;
    @SerializedName("incidentes")
    private List<IncidentePrima> incidentes  = new ArrayList<>();
}

