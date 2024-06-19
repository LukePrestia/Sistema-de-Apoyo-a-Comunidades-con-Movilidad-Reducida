package domain.persona;
import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.persistence.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Membresia extends Persistente {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_persona")
    private Persona persona;
    @ManyToOne
    @JoinColumn(name="id_comunidad")
    private Comunidad comunidad;
    @Column
    private Boolean admin;
    @Column
    private Boolean afectado; //afectado - observador
    @Column(name="Borrado_logico")
    private Boolean borrado=false;
}
