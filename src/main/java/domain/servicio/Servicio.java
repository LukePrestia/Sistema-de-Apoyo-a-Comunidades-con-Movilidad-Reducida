package domain.servicio;

import domain.establecimientos.Establecimiento;
import domain.persistence.Persistente;
import domain.persona.Persona;
import domain.repositorios.ServicioRepositorio;
import domain.repositorios.TipoServicioRepositorio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "SERVICIO")
@Entity
public class Servicio extends Persistente {
   @Column
   private String nombre;
   @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JoinColumn(name = "tipo")
   private TipoServicio servicio;
   @Column
   private Boolean funciona;
   @ManyToOne
   @JoinColumn(name= "id_establecimiento")
   private Establecimiento establecimiento;
   @Column(name="borrado_logico")
   private Boolean borrado=false;
   @PrePersist
   private void buscarInstancias() {
      ServicioRepositorio.getInstancia().buscarInstancia(this);
   }
}

