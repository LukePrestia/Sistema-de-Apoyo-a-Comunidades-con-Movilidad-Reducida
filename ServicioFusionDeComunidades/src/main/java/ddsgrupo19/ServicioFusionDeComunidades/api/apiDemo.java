package ddsgrupo19.ServicioFusionDeComunidades.api;

import ch.qos.logback.core.joran.sanity.Pair;
import ddsgrupo19.ServicioFusionDeComunidades.Cosas.*;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class apiDemo {
    @GetMapping("/saludar")
    public String saludar(){
        return "Hola";
    }
    @PostMapping("/sugerir")
    public List <DuplaApi>  sugerir(@RequestBody ClaseRecibir recibidor){
        List<Comunidad> comunidades = recibidor.getComunidades();
        double porcentajeEstablecimientos = recibidor.getPorcentajeEstablecimientos();
        double porcentajeServicios = recibidor.getPorcentajeServicios();
        double porcentajePersonas = recibidor.getPorcentajePersonas();

        Integer cantidadComunidades = comunidades.size();
        List <DuplaApi> comunidadesAFusionar = new ArrayList<>();
        List <Comunidad> comunidad = new ArrayList<>();

        for(int i = 0; i<cantidadComunidades;i++){
            for(int j = 0; j<cantidadComunidades;j++){
                if (i!=j){
                    Boolean confianza = comunidades.get(i).getConfianza() == comunidades.get(j).getConfianza();
                    Boolean establecimientos = establecimientosCoinciden(comunidades.get(i).getEstablecimientosObservados(),comunidades.get(j).getEstablecimientosObservados(), porcentajeEstablecimientos);
                    Boolean servicios = serviciosQueCoinciden(comunidades.get(i).getServiciosObservados(),comunidades.get(j).getServiciosObservados(),porcentajeServicios);
                    Boolean personas = personasQueCoinciden(comunidades.get(i).getMembresias(),comunidades.get(j).getMembresias(),porcentajePersonas);
                    if (confianza && establecimientos && servicios && personas){
                       comunidadesAFusionar.add(new DuplaApi(comunidades.get(i).getId(),comunidades.get(j).getId()));
                    }
                }

            }
        }
      // comunidadesAFusionar = this.eliminarParesInversos(comunidadesAFusionar);
        return comunidadesAFusionar;
    }

        public  List<DuplaApi> eliminarParesInversos(List<DuplaApi> lista) {

            for (DuplaApi par : lista) {
                DuplaApi parInverso = new DuplaApi(par.getComunidad2(), par.getComunidad1()); // Crea el par inverso
                if (!lista.contains(parInverso)) {
                    lista.remove(par); // Agrega el par actual al resultado
                }
            }

            return lista;
        }
    public Boolean establecimientosCoinciden(List<Establecimiento> lista1, List<Establecimiento> lista2 ,double minimo) {
        int tamanioLista1 = lista1.size();
        Long cantidadQueCoinciden = lista1.stream()
                .map(establecimiento -> establecimiento.getId())
                .filter(id -> lista2.stream().anyMatch(establecimiento2 -> establecimiento2.getId() == id))
                .count();

        double porcentaje = (double)  cantidadQueCoinciden / tamanioLista1;
        return porcentaje>=minimo;
    }
    public Boolean serviciosQueCoinciden(List<Servicio> lista1, List<Servicio> lista2,double minimo){
        Integer tamanioLista1 = lista1.size();
        Long cantidadQueCoinciden = lista1.stream()
                .map(servicio -> servicio.getId())
                .filter(id -> lista2.stream().anyMatch(servicio2 -> servicio2.getId() == id))
                .count();
        double porcentaje = (double)  cantidadQueCoinciden / tamanioLista1;
        return porcentaje>=minimo;
    }
    public Boolean personasQueCoinciden(List<Membresia> lista1, List<Membresia> lista2,double minimo){
        Integer tamanioLista1 = lista1.size();
        Long cantidadQueCoinciden = lista1.stream()
                .map(membresia -> membresia.getPersona().getId())
                .filter(id -> lista2.stream().anyMatch(membresia -> membresia.getPersona().getId() == id))
                .count();

        double porcentaje = (double)  cantidadQueCoinciden / tamanioLista1;


        return porcentaje>=minimo;
    }
}

