package domain.persona;

import domain.establecimientos.Establecimiento;
import domain.incidentes.Incidente;
import domain.persistence.Persistente;
import domain.services.Mensaje;
import domain.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="Comunidad")
public class Comunidad extends Persistente {
    @Column(name = "confianza")
    private Integer confianza;
    @Column(name ="nombre")
    private String nombre;
    @OneToMany(mappedBy = "comunidad", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Membresia> membresias = new ArrayList<>();
    @OneToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Incidente> incidentes= new ArrayList<>();
    @OneToMany
    private List<Establecimiento> establecimientosObservados = new ArrayList<>();
    @OneToMany
    private List<Servicio> serviciosObservados = new ArrayList<>();
    @Column(name="Borrado_logico")
    private Boolean borrado=false;
    public void sumarMembresia(Membresia membresia){
        if (membresias.contains(membresia)) membresias.add(membresia);
    }
    public void recibirReporteIncidente(Incidente incidente){
        incidentes.add(incidente);
        membresias.stream().forEach(membresia->membresia.getPersona().recibirMensaje(incidente,true));
    }
    public void eliminarReporteIncidente(Incidente incidente){
        incidentes.remove(incidente);
        membresias.stream().forEach(membresia->membresia.getPersona().recibirMensaje(incidente,false));
    }

    public boolean checkAfectado(Persona usuario){
        if(membresias.indexOf(usuario) != -1) {
            int index =  membresias.indexOf(usuario);
                if (membresias.get(index).getAfectado()){return true;}
         }
        return false;
    }
    public void hacerAdmin(Persona candidato) {
        buscarMembresia(candidato).setAdmin(true);

    }
    public Membresia buscarMembresia(Persona persona){
        for(int i=0;i<membresias.size()+1;i++){
            Persona estudio = membresias.get(i).getPersona();
            if(estudio==persona){
                return membresias.get(i);
            }
        }
        return membresias.get(0);
    }
    public void sugerirRevision() {
        Mensaje mensaje = new Mensaje();
        for (Membresia membresia1 : membresias) {
            for (Incidente incidente1 : incidentes) {
                if (membresia1.getPersona().getUbicacion().equals(incidente1.getEstablecimiento().getUbicacion())) {
                    {   mensaje.msjRevision(incidente1);
                        membresia1.getPersona().getMetodoAviso().enviarAPersona(membresia1.getPersona(), mensaje.msjRevision(incidente1));
                    }
                }
            }
            }
        }
    public void sumarEstablecimiento(Establecimiento establecimiento){
        this.establecimientosObservados.add(establecimiento);
    }
    public void sumarServicio(Servicio servicio){
        this.serviciosObservados.add(servicio);
    }
    public int cantidadDeAfectados(){
        int count = 0;
        for (Membresia membresia : membresias){
            if(checkAfectado(membresia.getPersona())){
                count++;}
        }
        return count;
    }

}
