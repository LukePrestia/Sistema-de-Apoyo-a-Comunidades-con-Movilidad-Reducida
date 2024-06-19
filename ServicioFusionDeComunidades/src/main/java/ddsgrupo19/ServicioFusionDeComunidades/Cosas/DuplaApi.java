package ddsgrupo19.ServicioFusionDeComunidades.Cosas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplaApi {
     long comunidad1;
    long comunidad2;


    public DuplaApi(long id, long id1) {
        this.comunidad1  = id;
        this.comunidad2 = id1;
    }
}

