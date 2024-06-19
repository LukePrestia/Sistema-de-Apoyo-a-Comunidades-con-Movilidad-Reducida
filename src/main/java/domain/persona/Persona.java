package domain.persona;
import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.CuandoSuceden;
import domain.incidentes.Incidente;
import domain.incidentes.ModoNotificacion;
import domain.incidentes.TipoEstado;
import domain.persistence.Persistente;
import domain.repositorios.PersonaRepositorio;
import domain.repositorios.RepositorioIncidentes;
import domain.services.TipoNotificacion;
import domain.services.georef.Localizacion;
import domain.services.wpp.SendWpp;
import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static domain.incidentes.TipoEstado.INCIDENTE_ABIERTO;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona extends Persistente {
    @OneToMany(mappedBy = "persona")
    private List<Membresia> membresias= new ArrayList<>();
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name ="localizacion_id", referencedColumnName = "Idl")
    private Localizacion ubicacion;
    @Column(name="mail",columnDefinition = "text")
    private String mail;
    @Column(name="telefono")
    private String telefono;
    @Column(name="Borrado_logico")
    private Boolean borrado=false;



    @Transient
    private ModoNotificacion modo = new CuandoSuceden();


    @Transient
    private TipoNotificacion metodoAviso = new SendWpp();

    @Column(name= "Modo_De_Notificacion")
    private String modoNotif = this.modo.toString();

    @Column(name= "Metodo_De_Notificacion")
    private String metoNotif = this.metodoAviso.toString();

    @ManyToMany
    @JoinTable(
            name = "necesidades",
            joinColumns = @JoinColumn(name = "id_persona"),
            inverseJoinColumns = @JoinColumn(name = "tipo", referencedColumnName = "tipo")
    )
    private List<TipoServicio> tipoServicios;
    @ManyToMany
    @JoinTable(
            name = "intereses",
            joinColumns = @JoinColumn(name = "id_persona"),
            inverseJoinColumns = @JoinColumn(name = "id_entidad")
    )
    private List<Entidad> entidades;
    public Persona(Localizacion ubicacion, String mail, ModoNotificacion modo,TipoNotificacion metodoAviso, String telefono,List<TipoServicio> servicios,List<Entidad> entidades ) {
        this.ubicacion = ubicacion;
        this.modo = modo;
        this.metodoAviso = metodoAviso;
        this.mail =  mail;
        this.telefono = telefono;
        this.entidades = entidades;
        this.tipoServicios = servicios;
    }
    public Persona() {

    }
    public void denunciarIncidente(Establecimiento establecimiento, Entidad entidad, Servicio servicio){
        Incidente incidente = new Incidente(entidad,establecimiento,servicio, LocalDateTime.now(), INCIDENTE_ABIERTO);
        membresias.stream().forEach(membresia->membresia.getComunidad().recibirReporteIncidente(incidente));
        Integer afectados = membresias.stream().mapToInt(membresia->membresia.getComunidad().cantidadDeAfectados()).sum();
        incidente.setAfectados(afectados);
        RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
        repositorioIncidentes.getInstance().getIncidentes().add(incidente);
    }
    public void denunciarIncidente(Incidente incidente){
        membresias.stream().forEach(membresia->membresia.getComunidad().recibirReporteIncidente(incidente));
        Integer afectados = membresias.stream().mapToInt(membresia->membresia.getComunidad().cantidadDeAfectados()).sum();
        incidente.setAfectados(afectados);
        RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
        repositorioIncidentes.getInstance().getIncidentes().add(incidente);
    }
    public void cerrarIncidente(Incidente incidente){
        List<Comunidad> comunidades=new ArrayList<>();
        for(Membresia membresia:this.membresias)
        {comunidades.add(membresia.getComunidad());}
        incidente.cerrar(comunidades);
        membresias.stream().forEach(membresia->membresia.getComunidad().eliminarReporteIncidente(incidente));
    }
    public void entrarComunidad(Comunidad comunidad, Boolean rol){
        Membresia membresia = new Membresia();
        membresia.setPersona(this);
        membresia.setAfectado(rol);
        membresia.setAdmin(false);
        membresia.setComunidad(comunidad);
        comunidad.sumarMembresia(membresia);
        this.membresias.add(membresia);
    }
    public void recibirMensaje(Incidente incidente, boolean rotura) {
        modo.avisar(incidente,rotura,this);
    }
}
