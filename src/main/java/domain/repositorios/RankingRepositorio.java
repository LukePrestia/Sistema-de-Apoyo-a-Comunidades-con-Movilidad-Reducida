package domain.repositorios;

import domain.incidentes.Incidente;
import domain.ranking.ParEntidadInt;
import domain.ranking.RankingAfectados;
import domain.ranking.RankingCantidadIncidentes;
import domain.ranking.RankingPromedio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class RankingRepositorio {

    public RankingAfectados rankingAfectados = new RankingAfectados();

    public RankingCantidadIncidentes rankingCantidadIncidentes = new RankingCantidadIncidentes();

    public RankingPromedio rankingPromedio = new RankingPromedio();

    public List<Incidente> afectados = this.rankingAfectados.GenerarRankingAfectados();

    public List<ParEntidadInt> cantidadIncidentes = this.rankingCantidadIncidentes.generarRankingCantidadDeIncidentes();

    public List<ParEntidadInt> promedio = this.rankingPromedio.generarRanking();

}
