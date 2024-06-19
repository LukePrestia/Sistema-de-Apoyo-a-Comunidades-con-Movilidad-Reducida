package domain.services.fusionComunidades.DTOs;

import com.google.gson.annotations.SerializedName;
import domain.persona.Comunidad;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


@Setter
@Getter
public class ComunidadDTO {
    @SerializedName("id")
    private Long id;
    @SerializedName("membresias")
    private List<MembresiaDTO> membresias  = new ArrayList<>();
   @SerializedName("establecimientosObservados")
    private List <EstablecimientoDTO> establecimientosObservados = new ArrayList<>();
    @SerializedName("serviciosObservados")
    private List<ServicioDTO> serviciosObservados = new ArrayList<>();

}
