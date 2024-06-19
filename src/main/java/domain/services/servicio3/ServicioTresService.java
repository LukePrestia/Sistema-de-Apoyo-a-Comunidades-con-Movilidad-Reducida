package domain.services.servicio3;

import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.servicio3.rankings.RankingGenerado;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServicioTresService {

    @POST("rankings/ranking")
    //TODO SERVICIO TRES SERVICIO
    Call<RankingGenerado> obtenerRanking(@Body RankingDto rankingIngresado);
}
