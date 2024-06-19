package ddsgrupo19.ServicioFusionDeComunidades.Cosas;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Getter
@Setter
public class ClaseRecibir {
    private List<Comunidad> comunidades;

    private double porcentajeServicios;
    private double porcentajeEstablecimientos;
    private double porcentajePersonas;
}
