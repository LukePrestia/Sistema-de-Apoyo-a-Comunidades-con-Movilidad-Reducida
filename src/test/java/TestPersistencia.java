import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.CuandoSuceden;
import domain.incidentes.Incidente;
import domain.incidentes.SinApuros;
import domain.organismo_de_control.Organismo;
import domain.persona.*;
import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.*;
import domain.services.Mensaje;
import domain.services.email.SendGridEmailer;
import domain.services.georef.Localizacion;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.services.wpp.SendWpp;
import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.incidentes.TipoEstado.INCIDENTE_ABIERTO;

public class TestPersistencia implements WithSimplePersistenceUnit {
    @PersistenceUnit(unitName = "simple-persistence-unit")
    TAMANIO tam = new TAMANIO();
    TOP10000 top = new TOP10000();
    List<Requisitos> chequeos = new ArrayList<>();
    Servicio banioCrf1 = new Servicio();
    Servicio banioCrf2 = new Servicio();
    Servicio banioStd1 = new Servicio();
    Servicio banioStd2 = new Servicio();
    Servicio banioMtr1 = new Servicio();
    Servicio banioMtr2 = new Servicio();
    Servicio banio = new Servicio();

    Establecimiento retiro = new Establecimiento();
    Establecimiento constitucion = new Establecimiento();
    Establecimiento santander1 = new Establecimiento();
    Establecimiento santander2 = new Establecimiento();
    Establecimiento carrefour1 = new Establecimiento();
    Establecimiento carrefour2 = new Establecimiento();
    List<Establecimiento> EstablecimientosMtr = new ArrayList<>();
    List<Establecimiento> EstablecimientosStd = new ArrayList<>();
    List<Establecimiento> EstablecimientosCrf = new ArrayList<>();
    Entidad linea_mitre = new Entidad();
    Entidad santander = new Entidad();
    Entidad carrefour = new Entidad();
    Servicio ascensor = new Servicio();
    List<TipoServicio> necesidades = new ArrayList<>();

    Persona juan_polito_mail = new Persona(new Localizacion(), "mgurruchaga@frba.utn.edu.ar",
            new CuandoSuceden(), new SendGridEmailer(), "+5491136586529", Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    Persona juan_polito_wpp = new Persona(new Localizacion(), "polito@frba.utn.edu.ar",
            new SinApuros(), new SendWpp(), "+5491136586529", Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    Persona luca = new Persona(new Localizacion(), "polito@frba.utn.edu.ar",
            new SinApuros(), new SendGridEmailer(), "+5491136586529", Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    Persona marto = new Persona(new Localizacion(), "msimon@frba.utn.edu.ar",
            new CuandoSuceden(), new SendGridEmailer(), "+54911382232140", Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    List<Entidad> intereses = new ArrayList<>();

    Incidente incidente_LineaA = new Incidente(linea_mitre, retiro, banio, LocalDateTime.now(),INCIDENTE_ABIERTO);

    String mensaje_prueba = new String();
    Mensaje mensaje_test = new Mensaje();
    Comunidad comunidadTest = new Comunidad();
    Comunidad comunidadDos = new Comunidad();
    Comunidad comunidadTres = new Comunidad();
    SinApuros juan_wpp = new SinApuros();
    SinApuros luca_s = new SinApuros();
    Municipio municipio = new Municipio();
    Provincia provincia = new Provincia();
    Localizacion localizacion = new Localizacion();
    List<Servicio> servicios = new ArrayList<>();

    Entidad entidad = new Entidad();

    List<Prestadora> prestadoras = new ArrayList<>();
    Organismo organismo = new Organismo("Banco Central", prestadoras);
    Prestadora prestadora = new Prestadora("Santander Rio", intereses);
    TipoServicio bano = new TipoServicio();
    TipoServicio tascensor = new TipoServicio();
    TipoServicio tescalera = new TipoServicio();

    Servicio escaleraCrf1 = new Servicio();
    Servicio escaleraCrf2 = new Servicio();
    Servicio escaleraStd1 = new Servicio();
    Servicio escaleraStd2 = new Servicio();
    Servicio escaleraMtr1 = new Servicio();
    Servicio escaleraMtr2 = new Servicio();

    Servicio ascensorCrf1 = new Servicio();
    Servicio ascensorCrf2 = new Servicio();
    Servicio ascensorStd1 = new Servicio();
    Servicio ascensorStd2 = new Servicio();
    Servicio ascensorMtr1 = new Servicio();
    Servicio ascensorMtr2 = new Servicio();
    List<Servicio> serviciosMtr1 = new ArrayList<>();
    List<Servicio> serviciosStd1 = new ArrayList<>();
    List<Servicio> serviciosCfr1 = new ArrayList<>();
    List<Servicio> serviciosMtr2 = new ArrayList<>();
    List<Servicio> serviciosStd2 = new ArrayList<>();
    List<Servicio> serviciosCfr2 = new ArrayList<>();

    Incidente incidente_LineaB = new Incidente(linea_mitre, retiro, ascensorMtr2, LocalDateTime.now(), INCIDENTE_ABIERTO);

    @BeforeEach
    public void setUp() {

        provincia.setNombre("Buenos Aires");
        provincia.setId(1);
        municipio.setNombre("La Matanza");
        municipio.setId(1);
        localizacion.setMunicipio(municipio);
        localizacion.setProvincia(provincia);

        bano.setTipo("BANO");
        tascensor.setTipo("ASCENSOR");
        tescalera.setTipo("ESCALERA");

        banioCrf1.setServicio(bano);
        banioCrf1.setFunciona(true);
        banioCrf1.setEstablecimiento(carrefour1);
        banioCrf2.setServicio(bano);
        banioCrf2.setFunciona(false);
        banioCrf2.setEstablecimiento(carrefour2);
        banioStd1.setServicio(bano);
        banioStd1.setFunciona(true);
        banioStd1.setEstablecimiento(santander1);
        banioStd2.setServicio(bano);
        banioStd2.setFunciona(false);
        banioStd2.setEstablecimiento(santander2);
        banioMtr1.setServicio(bano);
        banioMtr1.setFunciona(true);
        banioMtr1.setEstablecimiento(retiro);
        banioMtr2.setServicio(bano);
        banioMtr2.setFunciona(false);
        banioMtr2.setEstablecimiento(constitucion);
        banioCrf1.setServicio(bano);
        banioCrf1.setFunciona(true);
        banioCrf1.setEstablecimiento(carrefour1);
        banioCrf2.setServicio(bano);
        banioCrf2.setFunciona(false);
        banioCrf2.setEstablecimiento(carrefour2);
        banioStd1.setServicio(bano);
        banioStd1.setFunciona(true);
        banioStd1.setEstablecimiento(santander1);
        banioStd2.setServicio(bano);
        banioStd2.setFunciona(false);
        banioStd2.setEstablecimiento(santander2);
        banioMtr1.setServicio(bano);
        banioMtr1.setFunciona(true);
        banioMtr1.setEstablecimiento(retiro);
        banioMtr2.setServicio(bano);
        banioMtr2.setFunciona(false);
        banioMtr2.setEstablecimiento(constitucion);

        ascensorCrf1.setServicio(tascensor);
        ascensorCrf1.setFunciona(true);
        ascensorCrf1.setEstablecimiento(carrefour1);
        ascensorCrf2.setServicio(tascensor);
        ascensorCrf2.setFunciona(false);
        ascensorCrf2.setEstablecimiento(carrefour2);
        ascensorStd1.setServicio(tascensor);
        ascensorStd1.setFunciona(true);
        ascensorStd1.setEstablecimiento(santander1);
        ascensorStd2.setServicio(tascensor);
        ascensorStd2.setFunciona(false);
        ascensorStd2.setEstablecimiento(santander2);
        ascensorMtr1.setServicio(tascensor);
        ascensorMtr1.setFunciona(true);
        ascensorMtr1.setEstablecimiento(retiro);
        ascensorMtr2.setServicio(tascensor);
        ascensorMtr2.setFunciona(false);
        ascensorMtr2.setEstablecimiento(constitucion);
        escaleraCrf1.setServicio(tescalera);
        escaleraCrf1.setFunciona(true);
        escaleraCrf1.setEstablecimiento(carrefour1);
        escaleraCrf2.setServicio(tescalera);
        escaleraCrf2.setFunciona(false);
        escaleraCrf2.setEstablecimiento(carrefour2);
        escaleraStd1.setServicio(tescalera);
        escaleraStd1.setFunciona(true);
        escaleraStd1.setEstablecimiento(santander1);
        escaleraStd2.setServicio(tescalera);
        escaleraStd2.setFunciona(false);
        escaleraStd2.setEstablecimiento(santander2);
        escaleraMtr1.setServicio(tescalera);
        escaleraMtr1.setFunciona(true);
        escaleraMtr1.setEstablecimiento(retiro);
        escaleraMtr2.setServicio(tescalera);
        escaleraMtr2.setFunciona(false);
        escaleraMtr2.setEstablecimiento(constitucion);
        escaleraCrf1.setServicio(tescalera);
        escaleraCrf1.setFunciona(true);
        escaleraCrf1.setEstablecimiento(carrefour1);
        escaleraCrf2.setServicio(tescalera);
        escaleraCrf2.setFunciona(false);
        escaleraCrf2.setEstablecimiento(carrefour2);
        escaleraStd1.setServicio(tescalera);
        escaleraStd1.setFunciona(true);
        escaleraStd1.setEstablecimiento(santander1);
        escaleraStd2.setServicio(tescalera);
        escaleraStd2.setFunciona(false);
        escaleraStd2.setEstablecimiento(santander2);
        escaleraMtr1.setServicio(tescalera);
        escaleraMtr1.setFunciona(true);
        escaleraMtr1.setEstablecimiento(retiro);
        escaleraMtr2.setServicio(tescalera);
        escaleraMtr2.setFunciona(false);
        escaleraMtr2.setEstablecimiento(constitucion);

        serviciosCfr1.add(banioCrf1);
        serviciosCfr1.add(ascensorCrf1);
        serviciosCfr1.add(escaleraCrf1);
        serviciosCfr2.add(banioCrf2);
        serviciosCfr2.add(ascensorCrf2);
        serviciosCfr2.add(escaleraCrf2);
        serviciosStd1.add(banioStd1);
        serviciosStd1.add(ascensorStd1);
        serviciosStd1.add(escaleraStd1);
        serviciosStd2.add(banioStd2);
        serviciosStd2.add(ascensorStd2);
        serviciosStd2.add(escaleraStd2);
        serviciosMtr1.add(banioMtr1);
        serviciosMtr1.add(ascensorMtr1);
        serviciosMtr1.add(escaleraMtr1);
        serviciosMtr2.add(banioMtr2);
        serviciosMtr2.add(ascensorMtr2);
        serviciosMtr2.add(escaleraMtr2);
        retiro.setNombre("Retiro");
        retiro.setUbicacion(localizacion);
        retiro.setServicios(serviciosMtr1);
        retiro.setEntidad(linea_mitre);
        constitucion.setNombre("Constitucion");
        constitucion.setUbicacion(localizacion);
        constitucion.setServicios(serviciosMtr2);
        constitucion.setEntidad(linea_mitre);
        santander1.setNombre("Santander Caballito");
        santander1.setUbicacion(localizacion);
        santander1.setServicios(serviciosStd1);
        santander1.setEntidad(santander);
        santander2.setNombre("Santander Flores");
        santander2.setUbicacion(localizacion);
        santander2.setServicios(serviciosStd2);
        santander2.setEntidad(santander);
        carrefour1.setNombre("Carrefour La Plata");
        carrefour1.setUbicacion(localizacion);
        carrefour1.setServicios(serviciosCfr1);
        carrefour1.setEntidad(carrefour);
        carrefour2.setNombre("Carrefour market jujuy");
        carrefour2.setUbicacion(localizacion);
        carrefour2.setServicios(serviciosCfr2);
        carrefour2.setEntidad(carrefour);

        EstablecimientosMtr.add(retiro);
        EstablecimientosMtr.add(constitucion);
        EstablecimientosStd.add(santander1);
        EstablecimientosStd.add(santander2);
        EstablecimientosCrf.add(carrefour1);
        EstablecimientosCrf.add(carrefour2);

        carrefour.setNombre("Carrefour");
        santander.setNombre("Santander");
        linea_mitre.setNombre("Linea Mitre");
        linea_mitre.setListEstablecimiento(EstablecimientosMtr);
        carrefour.setListEstablecimiento(EstablecimientosCrf);
        santander.setListEstablecimiento(EstablecimientosStd);

        intereses.add(carrefour);
        intereses.add(linea_mitre);
        intereses.add(santander);

        luca.setEntidades(intereses);
        marto.setEntidades(intereses);
        luca.setTipoServicios(necesidades);
        marto.setTipoServicios(necesidades);
        luca.setUbicacion(localizacion);
        marto.setUbicacion(localizacion);

        organismo.setNombre("Banco Central");
        prestadora.setNombre("Santander Rio");
        prestadora.setOrganismo(organismo);
        prestadora.setEntidades(intereses);
        prestadoras.add(prestadora);
        carrefour.setPrestadora(prestadora);
        linea_mitre.setPrestadora(prestadora);
        santander.setPrestadora(prestadora);
        organismo.setPrestadoras(prestadoras);

        comunidadTest.sumarServicio(ascensor);
        comunidadTest.sumarServicio(escaleraCrf1);
        comunidadTest.sumarEstablecimiento(santander1);
        comunidadTest.sumarEstablecimiento(retiro);
        comunidadTres.sumarServicio(ascensor);
        comunidadTres.sumarEstablecimiento(retiro);
        comunidadDos.sumarEstablecimiento(santander2);
        comunidadDos.sumarServicio(ascensor);
        comunidadTest.setNombre("Comunidad Test");
        mensaje_prueba = "Mensaje de ejemplo";
        luca.entrarComunidad(comunidadTest, true);
        marto.entrarComunidad(comunidadTest, false);
        comunidadTest.getIncidentes().add(incidente_LineaA);
        luca.entrarComunidad(comunidadDos, true);
        juan_polito_wpp.entrarComunidad(comunidadDos, true);
        comunidadDos.setConfianza(1);
        comunidadTest.setConfianza(1);
        comunidadTres.setConfianza(1);
        marto.entrarComunidad(comunidadTres, true);
        luca.entrarComunidad(comunidadTres, false);
    }

    @Test
    public void hibernatePersistirTEST() {
            OrganismoRepositorio.getInstancia().persistir(organismo);
        Persona fede = new Persona();
        fede.setUbicacion(localizacion);
        fede.setTipoServicios(null);
        fede.setEntidades(null);
        fede.setMail("biettifede@gmail.com");
        fede.setTelefono("1149352459");
        PersonaRepositorio.getInstancia().persistir(fede);
        List<Entidad> interesesfede = new ArrayList<>();
        long id1=1;
        long id2=2;
        long id3=3;
        interesesfede.add(EntidadRepositorio.getInstancia().entityManager().find(Entidad.class,id1));
        interesesfede.add(EntidadRepositorio.getInstancia().entityManager().find(Entidad.class,id2));
        interesesfede.add(EntidadRepositorio.getInstancia().entityManager().find(Entidad.class,id3));
        fede.setEntidades(interesesfede);
        List<TipoServicio> necesidadesfede = new ArrayList<>();
        necesidadesfede.add(TipoServicioRepositorio.getInstancia().entityManager().find(TipoServicio.class, "BANO"));
        necesidadesfede.add(TipoServicioRepositorio.getInstancia().entityManager().find(TipoServicio.class, "ESCALERA"));
        necesidadesfede.add(TipoServicioRepositorio.getInstancia().entityManager().find(TipoServicio.class, "ASCENSOR"));
        fede.setTipoServicios(necesidadesfede);
        PersonaRepositorio.getInstancia().actualizar(fede);

        ComunidadRepositorio.getInstancia().persistir(comunidadTest);
        Membresia membresiafede = new Membresia();
        membresiafede.setPersona(fede);
        membresiafede.setComunidad(comunidadTest);
        membresiafede.setAdmin(true);
        membresiafede.setAfectado(false);
            comunidadTest.sumarMembresia(membresiafede);
            comunidadTest.sumarEstablecimiento(EstablecimientoRepositorio.getInstancia().buscarPorId(Establecimiento.class, 1));
            comunidadTest.sumarEstablecimiento(EstablecimientoRepositorio.getInstancia().buscarPorId(Establecimiento.class, 2));
            comunidadTest.sumarEstablecimiento(EstablecimientoRepositorio.getInstancia().buscarPorId(Establecimiento.class, 3));
            comunidadTest.sumarServicio(ServicioRepositorio.getInstancia().buscarPorId(Servicio.class, 1));
            comunidadTest.sumarServicio(ServicioRepositorio.getInstancia().buscarPorId(Servicio.class, 2));
            comunidadTest.sumarServicio(ServicioRepositorio.getInstancia().buscarPorId(Servicio.class, 3));
            comunidadTest.setConfianza(5);
        ComunidadRepositorio.getInstancia().actualizar(comunidadTest);

        //TEST INCIDENTE
        IncidenteRepositorio.getInstancia().persistir(incidente_LineaB);

   }
}