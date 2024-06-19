package domain.services.fusionComunidades.DTOs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaDTO {
    @SerializedName("id")
    private Long id;
}
