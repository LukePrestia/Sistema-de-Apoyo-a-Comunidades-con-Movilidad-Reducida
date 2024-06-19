package domain.services.servicio3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.Incidente;
import domain.persona.Comunidad;
import domain.persona.Membresia;
import domain.services.fusionComunidades.ClaseMandar;
import domain.services.fusionComunidades.DTOs.*;
import domain.services.georef.GeorefService;
import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.servicio3.comunidad.ComunidadPrima;
import domain.services.servicio3.entidades.EntidadPrima;
import domain.services.servicio3.incidentes.IncidentePrima;
import domain.services.servicio3.rankings.RankingGenerado;
import domain.servicio.Servicio;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioTres {
    private static ServicioTres instancia = null;
    private static final String urlApi = "http://localhost:8081/";
    private Retrofit retrofit;

    private ServicioTres() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();


        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ServicioTres instancia() {
        if (instancia == null) {
            instancia = new ServicioTres();
        }
        return instancia;
    }

    public RankingGenerado generarRanking(List<Comunidad> comunidades, List<Entidad> entidades, float cnf) throws IOException {
        ServicioTresService servicioTresService = this.retrofit.create(ServicioTresService.class);

        RankingDto parametros = new RankingDto();
        this.llenarRankingDTO(parametros, comunidades, entidades, cnf);

        Call<RankingGenerado> requestRanking = servicioTresService.obtenerRanking(parametros);
        Response<RankingGenerado> responseRanking = requestRanking.execute();
        return responseRanking.body();
    }

    private void llenarRankingDTO(RankingDto parametros, List<Comunidad> comunidades, List<Entidad> entidades, float cnf) {
        for (Comunidad comunidad : comunidades) {

            ComunidadPrima comunidadPrima = new ComunidadPrima();
            comunidadPrima.setCantMiembrosAfectados(comunidad.cantidadDeAfectados());

            for (Incidente incidente : comunidad.getIncidentes()) {

                IncidentePrima incidentePrima = new IncidentePrima();
               /* incidentePrima.setFechaHoraApertura(incidente.getHoraInicio());
                incidentePrima.setFechaHoraCierre(incidente.getHoraInicio());

                if (incidente.getDuracion() != null) {
                    incidentePrima.setFechaHoraCierre(incidente.getHoraInicio().plusHours(incidente.getDuracion().toHours()));
                }*/
                incidentePrima.setIdEstablecimiento(Math.toIntExact(incidente.getEstablecimiento().getId()));
                comunidadPrima.getIncidentes().add(incidentePrima);
            }

            parametros.getComunidades().add(comunidadPrima);
        }
        for (Entidad entidad : entidades) {
            EntidadPrima entidadPrima = new EntidadPrima();
            entidadPrima.setNombre(entidad.getNombre());
            entidadPrima.setId(entidad.getId());
            for (Establecimiento establecimiento : entidad.getListEstablecimiento()) {
                entidadPrima.getIdEstablecimientos().add(Math.toIntExact(establecimiento.getId()));
            }
            parametros.getEntidades().add(entidadPrima);
        }
        parametros.setCnf(cnf);

    }
}
