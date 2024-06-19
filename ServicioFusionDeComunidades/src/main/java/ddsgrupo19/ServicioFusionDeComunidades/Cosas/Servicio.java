package ddsgrupo19.ServicioFusionDeComunidades.Cosas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servicio {
    private Integer id;
   //private Boolean funciona;
   private boolean EstadoServicio(){
       return false;
   }
}

