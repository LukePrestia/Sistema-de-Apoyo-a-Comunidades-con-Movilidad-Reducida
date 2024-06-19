package domain.entidad;

import domain.establecimientos.Establecimiento;
import domain.persistence.Persistente;
import domain.persona.Persona;
import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.EntidadRepositorio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Table
@Entity
public class Entidad extends Persistente{
  @Column
  private String nombre;
  @ManyToOne
  @JoinColumn(name ="id_prestadora")
  private Prestadora prestadora;
  @OneToMany(mappedBy = "entidad", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
  private List<Establecimiento> listEstablecimiento = new ArrayList<>();
  @Column(name="Borrado_logico")
  private Boolean borrado=false;
}


