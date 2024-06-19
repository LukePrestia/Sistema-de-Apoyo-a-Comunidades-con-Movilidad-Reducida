package domain.ranking;

import domain.entidad.Entidad;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter

public class RankingPromedio extends RankingTemplate {


    public List<ParEntidadInt> generarRanking() {
        List<ParEntidadInt> ListaConEntidadesYSuPromedio = new ArrayList<>();
        List<Entidad>entidadesFinal = obtenerEntidades();
        for (int i = 0; i < entidadesFinal.size(); i++) {
            int promedio = calcularPromedioEntidades(entidadesFinal.get(i));
            Entidad entidad = entidadesFinal.get(i);
            ParEntidadInt entidadYSuPromedio = new ParEntidadInt(entidad,promedio);
            ListaConEntidadesYSuPromedio.add(entidadYSuPromedio);
        }
         Collections.sort(ListaConEntidadesYSuPromedio, Comparator.comparing(ParEntidadInt::getAux,Comparator.reverseOrder()));
        return ListaConEntidadesYSuPromedio;
    }



    public int calcularPromedioEntidades(Entidad entidad){
        List<Incidente> incidentesTotales = obtenerIncidentes();
        List<Integer> duracionesEntidad = new ArrayList<>();
        for(Incidente incidente:incidentesTotales)
        {
            if(incidente.getEntidad() == entidad && incidente.getDuracion()!=null){
                Long tiempo= incidente.getDuracion().toMillis();
                Integer tiempoEnInt= tiempo.intValue();
                duracionesEntidad.add(tiempoEnInt);
            }
        }
        if(!duracionesEntidad.isEmpty()) {
            return (duracionesEntidad.stream().mapToInt(Integer::intValue).sum() / duracionesEntidad.size());
        }
        else {
            return 0;
        }
    }
}