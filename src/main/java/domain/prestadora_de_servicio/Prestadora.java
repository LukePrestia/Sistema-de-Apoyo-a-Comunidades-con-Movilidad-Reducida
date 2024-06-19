package domain.prestadora_de_servicio;

import domain.entidad.Entidad;
import domain.organismo_de_control.Organismo;
import domain.persistence.Persistente;
import domain.ranking.ParEntidadInt;
import domain.repositorios.PrestadoraRepositorio;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@Entity
@Table
public class Prestadora extends Persistente {
  @Column(name="nombre")
  private String nombre;
  @ManyToOne
  @JoinColumn(name = "id_organismo")
  private Organismo organismo;
  @OneToMany(mappedBy = "prestadora", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  private List<Entidad> entidades;
  @Column(name="Borrado_logico")
  private boolean borrado=false;
  @Transient
  private List<ParEntidadInt> ultimoRanking;
  public Prestadora(String Nombre, List<Entidad> Entidades) {
    this.nombre = Nombre;
    this.entidades=Entidades;
  }
  public Prestadora() {
    // Default constructor
  }
  public void recibirYfiltrarRanking(List<ParEntidadInt> Ranking ){
    List<ParEntidadInt> rankingFiltrado = new ArrayList<>();
    for (ParEntidadInt par : Ranking) {
      String entidadNombre = par.getEntidad().getNombre();

      for (Entidad entidad : entidades) {
        if (entidadNombre.equals(entidad.getNombre())) {
          rankingFiltrado.add(par);
          break;
        }
      }
    }
    ultimoRanking = rankingFiltrado;
    }
}


