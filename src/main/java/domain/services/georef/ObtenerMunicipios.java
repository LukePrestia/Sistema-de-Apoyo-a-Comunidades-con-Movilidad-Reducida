package domain.services.georef;

import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.services.georef.ServicioGeoref;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ObtenerMunicipios {
  public List<Municipio> obtencionTodosMunicipios(){
    try{
    List<Municipio> ListMunicipios = new ArrayList<>();
    ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

    ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();

    listadoDeProvinciasArgentinas.getProvincias().sort((p1, p2) -> p1.getId() >= p2.getId()? 1 : -1);

    ListMunicipios = listadoDeProvinciasArgentinas.getProvincias().stream().flatMap(x-> obtenerMunicipiosDeProvincia(x).stream()).toList();
    //ListMunicipios.stream().forEach(x->System.out.println(x.getNombre()));

    return ListMunicipios;
  } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public List<Municipio> obtenerMunicipiosDeProvincia(Provincia posibleProvincia) {
    try {
    ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
    List <Municipio> ListMunicipios = new ArrayList<>();

    ListadoDeMunicipios municipiosDeLaProvincia = servicioGeoref.listadoDeMunicipiosDeProvincia(posibleProvincia);
    for(Municipio unMunicipio: municipiosDeLaProvincia.getMunicipios()){
      ListMunicipios.add(unMunicipio);
    }
    return ListMunicipios;
  } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}