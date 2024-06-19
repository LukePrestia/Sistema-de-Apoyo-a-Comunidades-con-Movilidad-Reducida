package ddsgrupo19.ServicioFusionDeComunidades.Cosas;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Comunidad {
    private List<Membresia> membresias = new ArrayList<>();
    private List<Establecimiento> establecimientosObservados = new ArrayList<>();
    private List<Servicio> serviciosObservados = new ArrayList<>();
    private Integer confianza;
    private long id;
}