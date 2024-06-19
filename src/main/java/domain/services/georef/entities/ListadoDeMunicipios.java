package domain.services.georef.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ListadoDeMunicipios {
  private int cantidad;
  private int total;
  private int inicio;
  private Parametro parametros;
  private List<Municipio> municipios;

  private class Parametro {
    private List<String> campos;
    private int max;
    private List<String> provincia;
  }
}