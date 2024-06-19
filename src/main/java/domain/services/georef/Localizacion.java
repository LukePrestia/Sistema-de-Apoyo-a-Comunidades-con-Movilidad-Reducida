package domain.services.georef;

import domain.repositorios.LocalizacionRepositorio;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
public class Localizacion {
    @Id
    @GeneratedValue
    private long idl;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "provincia")
    private Provincia provincia;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "municipio")
    private Municipio municipio;

   /* @PrePersist
    private void buscarProvinciasYMunicipios() {
        LocalizacionRepositorio.getInstancia().buscarInstancias(this);
        }*/
}
