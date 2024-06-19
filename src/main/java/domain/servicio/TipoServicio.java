package domain.servicio;

import domain.persistence.Persistente;
import domain.repositorios.LocalizacionRepositorio;
import domain.repositorios.TipoServicioRepositorio;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "TIPO_SERVICIO")
public class TipoServicio{
    @Id
    private String tipo;
}