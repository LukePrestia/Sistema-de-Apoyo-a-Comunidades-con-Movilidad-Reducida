package domain.services;

import domain.incidentes.Incidente;
import domain.persona.Comunidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
public class Mensaje {

    public String crearMensaje(Incidente Incidente, boolean rotoOArreglo){
       if(rotoOArreglo){

            return "La entidad " + Incidente.getEntidad().getNombre()  + " en el establecimiento " +
                    Incidente.getEstablecimiento().getNombre() + " esta roto";
        }else{

           return "La entidad " + Incidente.getEntidad().getNombre() + " en el establecimiento " +
                    Incidente.getEstablecimiento().getNombre() + " esta arreglado";
        }
    }
    public String msjRevision (Incidente Incidente)
    {
        return "Vimos que estas cerca de "+ Incidente.getEstablecimiento() + "por favor verifique el estado de: "+ Incidente.getServicio().getServicio();
    }
}
