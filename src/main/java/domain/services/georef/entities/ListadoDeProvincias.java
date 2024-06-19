package domain.services.georef.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
@Getter
@Setter
public class ListadoDeProvincias {
  private int cantidad;
  private int total;
  private int inicio;
  private Parametro parametros;
  private List<Provincia> provincias;

  public Optional<Provincia> provinciaDeId(int id){
    return this.provincias.stream()
        .filter(p -> p.getId() == id)
        .findFirst();
  }

  private class Parametro {
    private List<String> campos;
  }
}