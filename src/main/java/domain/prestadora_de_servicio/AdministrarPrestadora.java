package domain.prestadora_de_servicio;

import domain.archivos.LectorArchivo;
import domain.entidad.Entidad;

import java.util.ArrayList;
import java.util.List;


public class AdministrarPrestadora {
  public Prestadora altaPrestadora() { //Public para poder usarlo en el test, no se como hacer lo de reflexion
    String archivoCSV = "Prestadora_de_servicio.csv";
    String linea;
    Prestadora nuevo = null;
    LectorArchivo leer = new LectorArchivo();
    while ((linea = leer.traerLinea(archivoCSV)) != null) {
      //System.out.println(linea); //Para ver lo que carga, se puede sacar
      String[] campos = linea.split(",");
      String Nombre_Prestadora = campos[0];
      String [] nombreEntidades=campos[1].split(";");
      List<Entidad> entidades = new ArrayList<>();
      for (String nombreEntidad : nombreEntidades) {
        Entidad entidad = new Entidad();
        entidad.setNombre(nombreEntidad);
        entidades.add(entidad);
      }
      nuevo = new Prestadora(Nombre_Prestadora, entidades);
    }
    return nuevo;
  }
}