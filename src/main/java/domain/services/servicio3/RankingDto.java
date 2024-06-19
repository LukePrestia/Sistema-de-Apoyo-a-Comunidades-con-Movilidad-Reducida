package domain.services.servicio3;
import com.google.gson.annotations.SerializedName;
import domain.services.servicio3.comunidad.ComunidadPrima;
import domain.services.servicio3.entidades.EntidadPrima;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RankingDto {
    @SerializedName("comunidades")
    private List<ComunidadPrima> comunidades = new ArrayList<>();
    @SerializedName("entidades")

    private List<EntidadPrima> entidades = new ArrayList<>();
    @SerializedName("cnf")

    private float cnf;
}