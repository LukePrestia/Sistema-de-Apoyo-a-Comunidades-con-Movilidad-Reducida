package domain.incidentes;

import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.persistence.Persistente;
import domain.persona.Comunidad;
import domain.persona.Membresia;
import domain.repositorios.RepositorioIncidentes;
import domain.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Incidente extends Persistente{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ENTIDAD",referencedColumnName = "id")
    private Entidad entidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Establecimiento establecimiento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SERVICIO",referencedColumnName = "id")
    private Servicio servicio;
    @Column(columnDefinition = "DATE")
    private LocalDateTime horaInicio;
    @Column
    private Duration duracion;
    @Column
    private Integer afectados;
    @Column
    private TipoEstado estado ;
    public Incidente(Entidad entidad, Establecimiento establecimiento, Servicio servicio,LocalDateTime horaInicio,TipoEstado estado) {
        this.entidad = entidad;
        this.establecimiento = establecimiento;
        this.servicio = servicio;
        this.horaInicio = horaInicio;
        this.estado = estado;
    }
    public Incidente() {
        this.horaInicio = LocalDateTime.now();
    }
    public void cerrar(List<Comunidad> comunidadesDePersonaQueReporta ) {
        this.duracion = Duration.between(horaInicio, LocalDateTime.now());
    }
    public boolean BotonCerrar(){
        return this.getEstado().equals(TipoEstado.INCIDENTE_ABIERTO);
    }
}
