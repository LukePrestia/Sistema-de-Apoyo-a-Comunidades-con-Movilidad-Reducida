package domain.organismo_de_control;

import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.PrestadoraRepositorio;

import java.util.ArrayList;
import java.util.List;

public class CargaMasivaWeb {
  public Organismo altaOrganismo(String nombreOrganismo, String prestadoras)  {
    List<Prestadora> prestadorasO = new ArrayList<>();
    String[] prestadorasNombre=prestadoras.split(";");
    for (String prestadoraServicio : prestadorasNombre) {
      Prestadora prestadora = new Prestadora(prestadoraServicio, null);
      PrestadoraRepositorio repo= new PrestadoraRepositorio();
      List<Prestadora> prestadoraBD=repo.buscarPorNombre(Prestadora.class, prestadoraServicio);
      if(prestadoraBD.isEmpty()) {
        repo.save(prestadora);
        prestadorasO.add(prestadora);
      }else{
        prestadorasO.add(prestadoraBD.get(0));
      }
    }
    Organismo nuevo = new Organismo(nombreOrganismo, prestadorasO);
    return nuevo;
  }
}