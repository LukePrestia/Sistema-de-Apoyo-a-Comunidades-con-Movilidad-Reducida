package domain.ranking;

import domain.entidad.Entidad;
import domain.incidentes.Incidente;
import domain.repositorios.IncidenteRepositorio;
import domain.repositorios.RepositorioIncidentes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public abstract class  RankingTemplate {

    public List<Incidente> obtenerIncidentes(){
        IncidenteRepositorio incidenteRepositorio = new IncidenteRepositorio();
        return incidenteRepositorio.buscarTodos(Incidente.class);
    }
    public List<Entidad> obtenerEntidades(){
        List<Incidente> incidentesTotales = obtenerIncidentes();
        Set<Entidad> entidades = new HashSet<Entidad>();
        incidentesTotales.forEach(incidente->entidades.add(incidente.getEntidad()));
        List<Entidad> entidadesFinal = new ArrayList<>(entidades);
        return entidadesFinal;
    }
}
