package domain.services.servicio3.entidades;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@NoArgsConstructor
public class EntidadPrima {
    @SerializedName("id")
    private Long id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("idEstablecimientos")
    private List<Integer> idEstablecimientos  = new ArrayList<>();
}
