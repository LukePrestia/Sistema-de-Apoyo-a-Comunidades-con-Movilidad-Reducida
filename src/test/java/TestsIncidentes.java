import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.persona.*;
import domain.services.georef.Localizacion;
import domain.services.georef.ObtenerMunicipios;
import domain.organismo_de_control.*;
import domain.prestadora_de_servicio.*;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.servicio.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mockito.ArgumentCaptor;
import domain.services.email.SendGridEmailer;
import domain.ranking.*;
import domain.incidentes.*;
import static org.mockito.Mockito.*;

public class TestsIncidentes {
    Comunidad comunidad1 = new Comunidad();
    Comunidad comunidad2 = new Comunidad();
    SinApuros sinApuros = new SinApuros();
    CuandoSuceden cuandoSuceden = new CuandoSuceden();
    Localizacion localizacion = new Localizacion();

    Persona persona1 ;
    Persona persona2 ;
    Persona persona3 ;

    Establecimiento establecimiento1 = new Establecimiento();
    Establecimiento establecimiento2= new Establecimiento();
    Entidad entidad1 = new Entidad();
    Entidad entidad2 = new Entidad();
    Membresia membresia1 = new Membresia();
    Membresia membresia2 = new Membresia();
    Membresia membresia3 = new Membresia();
    Servicio servicio1 = new Servicio();

    @Test
    public void PersonaDenunciaIncidente(){
     /*   membresia1.setPersona(persona1);

        membresia2.setPersona(persona2);
        membresia3.setPersona(persona3);


        SendGridEmailer mail = mock(SendGridEmailer.class);
        when(mail.EnviarEmail("unmail1", "La entidad entidad1 en el establecimiento establecimiento1 esta roto")).thenReturn());
        persona1.setMetodoAviso(mail);
        comunidad1.sumarMembresia(membresia1);
        persona1.DenunciarIncidente(establecimiento1,entidad1,servicio1);
        comunidad1.getIncidentes();*/
    }
    @Test
    public void PersonaResuelveIncidente(){

    }
    @Test
    public void RankingGeneraCriterioTiempo(){

    }
    @Test
    public void RankingGeneraCriterioIncidentes(){

    }
    @Test
    public void RankingGeneraCriterioImpacto(){

    }
}
