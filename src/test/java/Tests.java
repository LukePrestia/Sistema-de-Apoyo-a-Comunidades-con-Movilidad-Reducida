
import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.CuandoSuceden;
import domain.incidentes.Incidente;
import domain.incidentes.SinApuros;
import domain.incidentes.TipoEstado;
import domain.persona.*;
import domain.ranking.RankingAfectados;
import domain.ranking.RankingCantidadIncidentes;
import domain.ranking.RankingPromedio;
import domain.services.Mensaje;
import domain.services.fusionComunidades.ObtenerComunidadesAFusion;
import domain.services.fusionComunidades.SugerenciaDeFusion;
import domain.services.georef.Localizacion;
import domain.repositorios.*;
import domain.services.georef.ObtenerMunicipios;
import domain.organismo_de_control.*;
import domain.prestadora_de_servicio.*;
import domain.services.georef.entities.*;
import domain.services.servicio3.RankingDto;
import domain.services.servicio3.ServicioTres;
import domain.services.servicio3.comunidad.ComunidadPrima;
import domain.services.servicio3.incidentes.IncidentePrima;
import domain.services.wpp.SendWpp;
import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import domain.services.email.SendGridEmailer;

import javax.persistence.PersistenceUnit;

import static domain.incidentes.TipoEstado.INCIDENTE_ABIERTO;
import static org.mockito.Mockito.*;
public class Tests {

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
    Establecimiento constitucion= new Establecimiento();
    Establecimiento santander1=new Establecimiento();
    Establecimiento santander2=new Establecimiento();
    Establecimiento carrefour1=new Establecimiento();
    Establecimiento carrefour2=new Establecimiento();
    List<Establecimiento> EstablecimientosMtr = new ArrayList<>();
    List<Establecimiento> EstablecimientosStd = new ArrayList<>();
    List<Establecimiento> EstablecimientosCrf = new ArrayList<>();
    Entidad linea_mitre = new Entidad();
    Entidad santander = new Entidad();
    Entidad carrefour = new Entidad();
    Servicio ascensor = new Servicio();
    List<TipoServicio> necesidades= new ArrayList<>();

    Persona juan_polito_mail = new Persona(new Localizacion(), "mgurruchaga@frba.utn.edu.ar",
            new CuandoSuceden(), new SendGridEmailer(), "+5491136586529",Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    Persona juan_polito_wpp = new Persona(new Localizacion(), "polito@frba.utn.edu.ar",
            new SinApuros(), new SendWpp(), "+5491136586529",Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    Persona luca = new Persona(new Localizacion(), "polito@frba.utn.edu.ar",
            new SinApuros(), new SendGridEmailer(), "+5491136586529", Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    Persona marto = new Persona(new Localizacion(), "msimon@frba.utn.edu.ar",
            new CuandoSuceden(), new SendGridEmailer(), "+54911382232140",Arrays.asList(new TipoServicio()), Arrays.asList(new Entidad()));
    List<Entidad> intereses=new ArrayList<>();
    Incidente incidente_LineaA = new Incidente(linea_mitre, retiro, banio, LocalDateTime.now(), INCIDENTE_ABIERTO);
    String mensaje_prueba = new String();
    Mensaje mensaje_test = new Mensaje();
    Comunidad comunidadTest = new Comunidad();
    Comunidad comunidadDos = new Comunidad();
    Comunidad comunidadTres = new Comunidad();
    SinApuros juan_wpp = new SinApuros();
    SinApuros luca_s = new SinApuros();
    Municipio municipio=new Municipio();
    Provincia provincia=new Provincia();
    Localizacion localizacion=new Localizacion();
    List<Servicio> servicios=new ArrayList<>();

    Entidad entidad=new Entidad();

    List<Prestadora> prestadoras=new ArrayList<>();
    Organismo organismo=new Organismo("Banco Central", prestadoras);
    Prestadora prestadora=new Prestadora("Santander Rio",intereses);
    TipoServicio bano=new TipoServicio();
    TipoServicio tascensor=new TipoServicio();
    TipoServicio tescalera=new TipoServicio();

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
    List<Servicio> serviciosMtr1=new ArrayList<>();
    List<Servicio> serviciosStd1=new ArrayList<>();
    List<Servicio> serviciosCfr1=new ArrayList<>();
    List<Servicio> serviciosMtr2=new ArrayList<>();
    List<Servicio> serviciosStd2=new ArrayList<>();
    List<Servicio> serviciosCfr2=new ArrayList<>();

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

        luca.setId(1);
        marto.setId(2);
        juan_polito_wpp.setId(3);
        ascensor.setId(1);
        escaleraCrf1.setId(2);
        santander1.setId(1);
        retiro.setId(2);
        santander2.setId(5);
        comunidadTest.sumarServicio(ascensorMtr1);
        comunidadTest.sumarServicio(escaleraCrf1);
        comunidadTest.sumarEstablecimiento(santander1);
        comunidadTest.sumarEstablecimiento(retiro);
        comunidadTres.sumarServicio(ascensorMtr1);
        comunidadTres.sumarEstablecimiento(retiro);
        comunidadDos.sumarEstablecimiento(santander2);
        comunidadDos.sumarServicio(ascensorMtr1);
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
    public void noSeCreaPorTamañoContraseña() {
        Usuario usuario = new Usuario();

        chequeos.add(tam);
        usuario.crearUsuario("123", "G19", chequeos);
        Assertions.assertNotEquals("123", usuario.getContraseña());
    }

    @Test
    public void seCreaContraseña() {
        Usuario usuario = new Usuario();
        chequeos.add(tam);
        chequeos.add(top);
        usuario.crearUsuario("djkasbjkdbsakkdkasb", "G19", chequeos);
        Assertions.assertEquals("djkasbjkdbsakkdkasb", usuario.getContraseña());
    }

    @Test
    public void noSeCreaPortop10000Contraseña() {
        Usuario usuario = new Usuario();
        chequeos.add(top);
        usuario.crearUsuario("123456789", "G19", chequeos);
        Assertions.assertNotEquals("123456789", usuario.getContraseña());
    }

    @Test
    public void existePinamar() {
        ObtenerMunicipios municipio = new ObtenerMunicipios();
        Assertions.assertTrue(municipio.obtencionTodosMunicipios().stream().map(muni -> muni.getNombre()).anyMatch(muni -> muni.equals("Pinamar")));
    }

    @Test
    public void noExisteRiver() {
        ObtenerMunicipios municipio = new ObtenerMunicipios();
        Assertions.assertFalse(municipio.obtencionTodosMunicipios().stream().map(muni -> muni.getNombre()).anyMatch(muni -> muni.equals("River")));
    }


    @Test
    public void municipiosMockeado() {
        Municipio arrecifes = new Municipio();
        List<Municipio> listMuni = new ArrayList<>();

        arrecifes.setId(2);
        arrecifes.setNombre("Arrecifes");

        listMuni.add(arrecifes);

        ObtenerMunicipios obtenedorMunicipios = mock(ObtenerMunicipios.class);
        when(obtenedorMunicipios.obtencionTodosMunicipios()).thenReturn(listMuni);

        Assertions.assertTrue(obtenedorMunicipios.obtencionTodosMunicipios().stream().map(municipio -> municipio.getNombre()).anyMatch(municipio -> municipio.equals("Arrecifes")));
    }

    @Test
    public void altaPrestadora() {
        Prestadora prestadora = new AdministrarPrestadora().altaPrestadora();
        String nombre = prestadora.getNombre();
        Assertions.assertTrue(nombre == prestadora.getNombre());
    }

    @Test
    public void crearMensje() {
        Assertions.assertEquals("La entidad Linea Mitre en el establecimiento Retiro esta roto", mensaje_test.crearMensaje(incidente_LineaA, true));

    }

    @Test
    public void testEmail() {
        SendGridEmailer sendGridEmailer = new SendGridEmailer();
        // sendGridEmailer.EnviarAPersona(marto, mensaje_prueba);
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void testWpp() {
        SendWpp sendWpp = new SendWpp();
        //  sendWpp.EnviarAPersona(marto, mensaje_prueba);
        Assertions.assertEquals(1, 1);
    }

    @Test
    public void denunciarIncidente() {
        SendWpp sendWpp = mock(SendWpp.class);
        ;
        when(sendWpp.enviarAPersona(juan_polito_wpp, mensaje_prueba)).thenReturn(true);
        SendGridEmailer mail = mock(SendGridEmailer.class);
        ;
        when(mail.enviarAPersona(luca, mensaje_prueba)).thenReturn(true);
        when(mail.enviarAPersona(marto, mensaje_prueba)).thenReturn(true);
        luca.setMetodoAviso(sendWpp);
        luca.setMetodoAviso(mail);
        marto.setMetodoAviso(mail);
        luca.denunciarIncidente(retiro, linea_mitre, banio);
        Assertions.assertFalse(luca.getMembresias().get(0).getComunidad().getIncidentes().isEmpty());
    }

    @Test
    public void denunciarYCerrarIncidente() {
        SendWpp sendWpp = mock(SendWpp.class);
        ;
        when(sendWpp.enviarAPersona(juan_polito_wpp, mensaje_prueba)).thenReturn(true);
        SendGridEmailer mail = mock(SendGridEmailer.class);
        ;
        when(mail.enviarAPersona(luca, mensaje_prueba)).thenReturn(true);
        when(mail.enviarAPersona(marto, mensaje_prueba)).thenReturn(true);
        luca.setMetodoAviso(sendWpp);
        luca.setMetodoAviso(mail);
        marto.setMetodoAviso(mail);
        luca.denunciarIncidente(retiro, linea_mitre, banio);
        marto.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        Assertions.assertTrue(juan_polito_wpp.getMembresias().get(0).getComunidad().getIncidentes().isEmpty());
    }

    @Test
    public void rankingprimerCriterio() {
        SendWpp sendWpp = mock(SendWpp.class);
        ;
        when(sendWpp.enviarAPersona(juan_polito_wpp, mensaje_prueba)).thenReturn(true);
        SendGridEmailer mail = mock(SendGridEmailer.class);
        ;
        when(mail.enviarAPersona(luca, mensaje_prueba)).thenReturn(true);
        when(mail.enviarAPersona(marto, mensaje_prueba)).thenReturn(true);
        luca.setMetodoAviso(sendWpp);
        luca.setMetodoAviso(mail);
        marto.setMetodoAviso(mail);
        luca.denunciarIncidente(retiro, linea_mitre, banio);
        marto.denunciarIncidente(retiro, carrefour, banio);
        juan_polito_wpp.denunciarIncidente(retiro, santander, ascensor);
        luca.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        luca.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        luca.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        RankingCantidadIncidentes rankingCantidadIncidentes = new RankingCantidadIncidentes();
         Assertions.assertEquals(santander.getNombre(),rankingCantidadIncidentes.generarRankingCantidadDeIncidentes().get(0).getEntidad().getNombre());
    }
    @Test
    public void rankingSegundoCriterio(){
        SendWpp sendWpp =  mock(SendWpp.class);;
        when(sendWpp.enviarAPersona(juan_polito_wpp,mensaje_prueba)).thenReturn(true);
        SendGridEmailer mail =  mock(SendGridEmailer.class);;
        when(mail.enviarAPersona(luca,mensaje_prueba)).thenReturn(true);
        when(mail.enviarAPersona(marto,mensaje_prueba)).thenReturn(true);
        luca.setMetodoAviso(sendWpp);
        luca.setMetodoAviso(mail);
        marto.setMetodoAviso(mail);
        luca.denunciarIncidente(retiro,linea_mitre,banio);
        marto.denunciarIncidente(retiro,carrefour,banio);
        marto.denunciarIncidente(retiro,carrefour,ascensor);
        juan_polito_wpp.denunciarIncidente(retiro,santander,ascensor);
        marto.cerrarIncidente(comunidadTest.getIncidentes().get(1));
        marto.cerrarIncidente(comunidadTest.getIncidentes().get(1));
        marto.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        marto.cerrarIncidente(comunidadTest.getIncidentes().get(0));

        RankingAfectados ranking = new RankingAfectados();
        Assertions.assertEquals(linea_mitre.getNombre(),ranking.GenerarRankingAfectados().get(0).getEntidad().getNombre());
   }
    @Test
    public void rankingTercerCriterio(){
        SendWpp sendWpp =  mock(SendWpp.class);;
        when(sendWpp.enviarAPersona(juan_polito_wpp,mensaje_prueba)).thenReturn(true);
        SendGridEmailer mail =  mock(SendGridEmailer.class);;
        when(mail.enviarAPersona(luca,mensaje_prueba)).thenReturn(true);
        when(mail.enviarAPersona(marto,mensaje_prueba)).thenReturn(true);
        luca.setMetodoAviso(sendWpp);
        luca.setMetodoAviso(mail);
        marto.setMetodoAviso(mail);
        luca.denunciarIncidente(retiro,linea_mitre,banio);
        marto.denunciarIncidente(retiro,carrefour,banio);
        juan_polito_wpp.denunciarIncidente(retiro,santander,ascensor);
        luca.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        luca.cerrarIncidente(comunidadTest.getIncidentes().get(0));
        luca.cerrarIncidente(comunidadDos.getIncidentes().get(0));

        RankingPromedio  ranking = new RankingPromedio();
        Assertions.assertEquals(linea_mitre.getNombre(),ranking.generarRanking().get(0).getEntidad().getNombre());
    }
     /*@Test
    public void testAPIFusionNoCoinciden() throws IOException {
        ObtenerComunidadesAFusion comunidadesAFusion = new ObtenerComunidadesAFusion();
        ComunidadRepositorio.getInstancia().persistir(comunidadTest);
        ComunidadRepositorio.getInstancia().persistir(comunidadTres);
        ComunidadRepositorio.getInstancia().persistir(comunidadDos);
         System.out.print(comunidadesAFusion.obtenerComunidadesAFucionar());
        Assertions.assertTrue(true);
    }*//*
    @Test
    public void testAPIFusionCoinciden() throws IOException {
        ObtenerComunidadesAFusion comunidadesAFusion = new ObtenerComunidadesAFusion();
        SugerenciaDeFusion.instancia().setPorcentajeEstablecimientos(0.0);
        SugerenciaDeFusion.instancia().setPorcentajeServicios(0.0);
        SugerenciaDeFusion.instancia().setPorcentajePersonas(0.0);
        ComunidadRepositorio.getInstancia().persistirComunidad(comunidadTest);
        ComunidadRepositorio.getInstancia().persistirComunidad(comunidadTres);
        ComunidadRepositorio.getInstancia().persistirComunidad(comunidadDos);
        System.out.print(comunidadesAFusion.obtenerComunidadesAFucionar());
        Assertions.assertTrue(true);
    }*/

    @Test
    public void testSoloApiCoinciden() throws IOException{
        SugerenciaDeFusion instacia = SugerenciaDeFusion.instancia();
        instacia.setPorcentajePersonas(0.0);
        instacia.setPorcentajeServicios(0.0);
        instacia.setPorcentajeEstablecimientos(0.0);

        List<Comunidad> comunidades = new ArrayList<>();
        comunidadTest.setId(0l);
        comunidadDos.setId(1l);
        comunidadTres.setId(2l);
        comunidadDos.getIncidentes().add(incidente_LineaA);
        comunidadTres.getIncidentes().add(incidente_LineaA);

        comunidades.add(comunidadTres);
        comunidades.add(comunidadDos);
        comunidades.add(comunidadTest);
        System.out.println(instacia.ListaDeComunidadesAFusionar(comunidades));
        Assertions.assertEquals(instacia.ListaDeComunidadesAFusionar(comunidades).size(),6);
    }

    @Test
    public void servicioTres() throws IOException{
        ServicioTres servicioTres = ServicioTres.instancia();


        List<Comunidad> comunidades = new ArrayList<>();
        comunidadTest.setId(0l);
        comunidadDos.setId(1l);
        comunidadTres.setId(2l);
        comunidadDos.getIncidentes().add(incidente_LineaA);
        comunidadTres.getIncidentes().add(incidente_LineaA);
        comunidades.add(comunidadTres);
        comunidades.add(comunidadDos);
        comunidades.add(comunidadTest);


        System.out.println(servicioTres.generarRanking(comunidades,intereses,25).getRanking().get(0).getEntidadId());

        Assertions.assertEquals( servicioTres.generarRanking(comunidades,intereses,25).getRanking().get(0).getEntidadId(),0);
    }
}
