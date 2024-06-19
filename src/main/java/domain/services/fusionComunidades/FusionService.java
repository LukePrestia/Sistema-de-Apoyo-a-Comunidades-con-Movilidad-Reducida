package domain.services.fusionComunidades;

import domain.persona.Comunidad;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface FusionService {
    @POST("sugerir")
        //TODO SERVICIO NUESTRO

    Call<List<ComunidadParID>> comunidadesFusion(@Body ClaseMandar parametrosApi);
}
