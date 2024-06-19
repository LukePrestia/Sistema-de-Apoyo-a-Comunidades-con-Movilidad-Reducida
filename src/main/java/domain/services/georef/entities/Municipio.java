package domain.services.georef.entities;

import domain.persistence.Persistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
public class Municipio{
  @Column(name = "Id_georef")
  private int id;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long idm;
  @Column
  private String nombre;
}
