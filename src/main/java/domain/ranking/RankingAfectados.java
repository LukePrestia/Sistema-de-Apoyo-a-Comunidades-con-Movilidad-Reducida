package domain.ranking;

import domain.entidad.Entidad;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter

public class RankingAfectados extends RankingTemplate {
    public List<Incidente>GenerarRankingAfectados(){
        List<Incidente> incidentesTotales = obtenerIncidentes();
        for(Incidente incidente:incidentesTotales){
        if(!incidentesTotales.isEmpty() && incidente.getAfectados()!=null){
        Collections.sort(incidentesTotales, Comparator.comparing(Incidente::getAfectados,Comparator.reverseOrder()));
        return incidentesTotales;}
        else{
            return null;
        }}
        return null;
    }
}
