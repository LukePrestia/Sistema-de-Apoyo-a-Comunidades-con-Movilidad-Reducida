package domain.establecimientos;

import domain.entidad.Entidad;
import domain.repositorios.EstablecimientoRepositorio;
import domain.repositorios.LocalizacionRepositorio;
import domain.services.georef.Localizacion;
import domain.servicio.Servicio;
import domain.persistence.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table
@Entity
public class Establecimiento extends Persistente {
  @Column(name="nombre")
  private  String nombre;
  @ManyToOne
  private Entidad entidad;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name ="Clave_localizacion")
  private Localizacion ubicacion;
  @OneToMany(mappedBy = "establecimiento", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  private List<Servicio> servicios = new ArrayList<>();
  @Column(name="Borrado_logico")
  private Boolean borrado=false;
}
