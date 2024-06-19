package domain.services.georef.entities;

import domain.persistence.Persistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity

public class Provincia{
    @Column(name = "Id_georef")
    private int id;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idp;
    @Column
    private String nombre;
}
