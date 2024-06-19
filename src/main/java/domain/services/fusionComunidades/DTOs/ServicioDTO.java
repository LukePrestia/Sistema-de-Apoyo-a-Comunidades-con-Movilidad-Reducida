package domain.services.fusionComunidades.DTOs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServicioDTO {
    @SerializedName("id")
    private Long id;
}
