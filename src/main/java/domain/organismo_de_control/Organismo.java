package domain.organismo_de_control;
import domain.persistence.Persistente;
import lombok.Setter;
import lombok.Getter;
import domain.ranking.ParEntidadInt;
import domain.prestadora_de_servicio.Prestadora;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@Entity
@Table
public class Organismo extends Persistente {
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "organismo", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Prestadora> prestadoras;
    @Column(name="Borrado_logico")
    private Boolean borrado=false;
    public Organismo(String Nombre, List<Prestadora> Prestadoras) {
        this.nombre = Nombre;
        this.prestadoras = (List<Prestadora>) Prestadoras;
    }
    public Organismo() {
        // Default constructor
    }
    public List<List<ParEntidadInt>> rankingsDePrestadoras() {
        List<List<ParEntidadInt>> rankings = new ArrayList<>();

        for (Prestadora prestadora : prestadoras) {
            rankings.add(prestadora.getUltimoRanking());
        }

        return rankings;
    }

}

