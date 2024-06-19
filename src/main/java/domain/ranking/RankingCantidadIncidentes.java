package domain.ranking;

import domain.entidad.Entidad;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.DoubleToIntFunction;

@Getter
@Setter

public class RankingCantidadIncidentes extends RankingTemplate {
    public List<ParEntidadInt> generarRankingCantidadDeIncidentes() {
        List<Incidente> incidentesTotales = obtenerIncidentes();

        Set<Entidad> entidades = new HashSet<Entidad>();
        List<ParEntidadInt> ListaConEntidadesYSuCantidadDeIncidentes = new ArrayList<>();
        incidentesTotales.forEach(incidente->entidades.add(incidente.getEntidad()));
        List<Entidad> entidadesFinal = new ArrayList<>(entidades);
        for (int i = 0; i < entidades.size(); i++) {
            int cantidadIncidentesDeLaEntidad = contadorIncidentesPorEntidad(entidadesFinal.get(i));
            Entidad entidad = entidadesFinal.get(i);
            ParEntidadInt entidadYSuCantidadDeIncidentes = new ParEntidadInt(entidad,cantidadIncidentesDeLaEntidad);
            ListaConEntidadesYSuCantidadDeIncidentes.add(entidadYSuCantidadDeIncidentes);
        }
        Collections.sort(ListaConEntidadesYSuCantidadDeIncidentes, Comparator.comparing(ParEntidadInt::getAux,Comparator.reverseOrder()));

        return ListaConEntidadesYSuCantidadDeIncidentes;
    }
    public int contadorIncidentesPorEntidad(Entidad entidad){
        List<Incidente> incidentesTotales = obtenerIncidentes();
       return  incidentesTotales.stream().filter(incidente -> incidente.getEntidad()==entidad).toList().size();

    }
}