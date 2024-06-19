package domain.services.fusionComunidades;

import com.google.gson.annotations.SerializedName;
import domain.services.fusionComunidades.DTOs.ComunidadDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClaseMandar {
    @SerializedName("comunidades")
    private List<ComunidadDTO> comunidades  = new ArrayList<>();
    @SerializedName("porcentajeServicios")
    private double porcentajeServicios;
    @SerializedName("porcentajeEstablecimientos")
    private double porcentajeEstablecimientos;
    @SerializedName("porcentajePersonas")
    private double porcentajePersonas;
}

