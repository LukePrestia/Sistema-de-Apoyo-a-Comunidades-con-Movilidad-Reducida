package domain.services.fusionComunidades;

import domain.establecimientos.Establecimiento;
import domain.persona.Comunidad;
import domain.persona.Membresia;
import domain.persona.Usuario;
import domain.services.fusionComunidades.DTOs.*;
import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
@Getter
@Setter
public class SugerenciaDeFusion implements WithSimplePersistenceUnit {
    private  Retrofit retrofit;
    private double porcentajeServicios = 0.75;
    private double porcentajeEstablecimientos=0.75;
    private double porcentajePersonas=0.05;
    private void hacerSugerencias() {

       //entityManager().createQuery();
    }

    private static SugerenciaDeFusion instancia = null;
    private static final String urlApi = "http://localhost:8080/api/";

    private SugerenciaDeFusion() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SugerenciaDeFusion instancia(){
        if(instancia== null){
            instancia = new SugerenciaDeFusion();
        }
        return instancia;
    }

    public List<ComunidadParID> ListaDeComunidadesAFusionar(List<Comunidad> comunidadesReales) throws IOException {



        FusionService fusionService = retrofit.create(FusionService.class);
        ClaseMandar parametros = new ClaseMandar();
        this.llenarClaseMandar(parametros,comunidadesReales);



        parametros.setPorcentajePersonas(this.porcentajePersonas);
        parametros.setPorcentajeEstablecimientos(this.porcentajeEstablecimientos);
        parametros.setPorcentajeServicios(this.porcentajeServicios);

        Call<List<ComunidadParID>> reqComunidadRecepcion = fusionService.comunidadesFusion(parametros);
        Response<List<ComunidadParID>> respComunidadRecepcion = reqComunidadRecepcion.execute();
        return respComunidadRecepcion.body();
    }

    private void llenarClaseMandar(ClaseMandar parametros, List<Comunidad> comunidadesReales) {
        for (Comunidad comunidad : comunidadesReales) {

            ComunidadDTO comunidadDTO = new ComunidadDTO();
            comunidadDTO.setId(comunidad.getId());

            for (Membresia membresia : comunidad.getMembresias()) {

                MembresiaDTO membresiaDTO = new MembresiaDTO();
                PersonaDTO personaDTO = new PersonaDTO();

                personaDTO.setId(membresia.getPersona().getId());
                membresiaDTO.setPersona(personaDTO);
                comunidadDTO.getMembresias().add(membresiaDTO);
            }
           for (Servicio servicio : comunidad.getServiciosObservados()) {
                ServicioDTO servicioDTO = new ServicioDTO();
                servicioDTO.setId(servicio.getId());
                comunidadDTO.getServiciosObservados().add(servicioDTO);
            }
            for (Establecimiento establecimiento : comunidad.getEstablecimientosObservados()) {
                EstablecimientoDTO establecimientoDTO = new EstablecimientoDTO();
                establecimientoDTO.setId(establecimiento.getId());
                comunidadDTO.getEstablecimientosObservados().add(establecimientoDTO);
            }
            parametros.getComunidades().add(comunidadDTO);
        }
    }
}
