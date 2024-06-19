package domain.services.servicio3.incidentes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
public class IncidentePrima {
    /* @SerializedName("fechaHoraApertura")
    private LocalDateTime fechaHoraApertura;
    @SerializedName("fechaHoraCierre")
    private LocalDateTime fechaHoraCierre;*/
    @SerializedName("idEstablecimiento")
    private int idEstablecimiento;

    /*public Long calcularDuracion() {
        if(fechaHoraCierre == null) {
            return -1L;
        } else {
            return Duration.between(fechaHoraApertura, fechaHoraCierre).toHours();
        }
    }

    public Boolean estaAbierto() {
        return fechaHoraCierre == null;
    }*/
}
