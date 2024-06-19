package controllers;

import domain.ranking.RankingAfectados;
import domain.ranking.RankingCantidadIncidentes;
import domain.ranking.RankingPromedio;
import domain.repositorios.RankingRepositorio;
import io.javalin.http.Context;
import org.modelmapper.ModelMapper;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingController implements ICrudViewsHandler {

    private final RankingRepositorio rankingRepositorio;

    public RankingController(RankingRepositorio rankingRepositorio, ModelMapper modelMapper) {
        this.rankingRepositorio = rankingRepositorio;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();

        model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        model.put("administrador",esAdmin(context));
        context.render("rankings/listarRankings.hbs",model);
    }

    @Override
    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();

        String rankingType = context.pathParam("tipo");
        System.out.println(rankingType);
        // Determine the type of ranking and retrieve the corresponding data
        if ("afectados".equals(rankingType)) {
            RankingAfectados ranking = new RankingAfectados();
            System.out.println(ranking);
            model.put("ranking",ranking.GenerarRankingAfectados());
          model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        model.put("administrador",esAdmin(context));
        context.render("rankings/listarRankingsIncidentes.hbs",model);
        } else if ("incidentes".equals(rankingType)) {
            RankingCantidadIncidentes rankingCantidadIncidentes = new RankingCantidadIncidentes();
            System.out.println(rankingCantidadIncidentes);
            model.put("ranking",rankingCantidadIncidentes.generarRankingCantidadDeIncidentes());
          model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        model.put("administrador",esAdmin(context));
        context.render("rankings/listarRankings.hbs",model);
        } else if ("promedio".equals(rankingType)) {
            RankingPromedio rankingPromedio = new RankingPromedio();
            System.out.println(rankingPromedio);

            model.put("ranking",rankingPromedio.generarRanking());
          model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        model.put("administrador",esAdmin(context));
        context.render("rankings/listarRankings.hbs",model);
        } else {
            context.status(404).result("Ranking not found");
        }
    }




    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
