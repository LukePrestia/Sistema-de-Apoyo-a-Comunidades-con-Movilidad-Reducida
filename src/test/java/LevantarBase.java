import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.persona.Persona;
import domain.persona.Rol;
import domain.persona.Usuario;
import domain.repositorios.*;
import domain.services.georef.Localizacion;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceUnit;

public class LevantarBase implements WithSimplePersistenceUnit {
    @Test
    void  levantarBaseBuena(){
        withTransaction(()-> {

            Localizacion ubicacion = new Localizacion();
            Municipio municipio = new Municipio();
            Provincia provincia = new Provincia();
            TipoServicio bano = new TipoServicio();
            TipoServicio ascensor = new TipoServicio();
            TipoServicio escalera = new TipoServicio();
            Servicio servicioBano = new Servicio();
            Servicio servicioEscalera = new Servicio();
            Establecimiento Carrefour = new Establecimiento();
            Establecimiento Santander = new Establecimiento();
            Entidad BancoSantander = new Entidad();
            Entidad Carrefour_SA = new Entidad();
            Localizacion aaaa = new Localizacion();
            Provincia provincia1 = new Provincia();
            Municipio munnicipio1 = new Municipio();
            aaaa.setProvincia(provincia1);
            aaaa.setMunicipio(munnicipio1);
            provincia1.setId(1);
            munnicipio1.setId(1);
            municipio.setId(0);
            provincia.setId(0);
            Santander.setNombre("Caja Santander Saavedra");
            Carrefour.setUbicacion(aaaa);
            Santander.setUbicacion(ubicacion);
            Santander.setEntidad(BancoSantander);
            Santander.getServicios().add(servicioBano);
            BancoSantander.setNombre("Banco Santander");
            BancoSantander.getListEstablecimiento().add(Santander);
            provincia1.setNombre("Salta");
            munnicipio1.setNombre("Flores");
            municipio.setNombre("Saavedra");
            provincia.setNombre("Buenos Aires");
            Carrefour_SA.setNombre("Carrefour SA");
            Carrefour_SA.getListEstablecimiento().add(Carrefour);
            Carrefour.setNombre("Carrefour Flores");

            Carrefour.setEntidad(Carrefour_SA);
            Carrefour.getServicios().add(servicioBano);
            bano.setTipo("BANO");
            ascensor.setTipo("ASCENSOR");
            escalera.setTipo("ESCALERA");
            servicioBano.setServicio(bano);
            servicioBano.setNombre("Baño Carrefour");
            servicioBano.setEstablecimiento(Carrefour);
            servicioEscalera.setServicio(escalera);
            servicioEscalera.setNombre("Escalera Santander");
            servicioEscalera.setEstablecimiento(Santander);
            long nuemro = 9;
            LocalizacionRepositorio localizacionRepositorio = new LocalizacionRepositorio();
            localizacionRepositorio.persistir(ubicacion);
            localizacionRepositorio.persistir(aaaa);

            Usuario admin = new Usuario();
            Persona persona = new Persona();
            persona.setUbicacion(ubicacion);
            persona.setMail("dasdas@gmail.com");
            admin.setPersona(persona);
            admin.setRol(Rol.ADMIN);
            admin.setNombreUsuario("admin");
            admin.setContraseña("123");
            UsuarioRepositorio.getInstancia().persistir(admin);
            ServicioRepositorio servicioRepositorio = new ServicioRepositorio();
            EstablecimientoRepositorio establecimientoRepositorio = new EstablecimientoRepositorio();
            EntidadRepositorio entidadRepositorio = new EntidadRepositorio();
            TipoServicioRepositorio.getInstancia().persistir(bano);
            TipoServicioRepositorio.getInstancia().persistir(escalera);
            TipoServicioRepositorio.getInstancia().persistir(ascensor);
            servicioRepositorio.persistir(servicioBano);
            servicioRepositorio.persistir(servicioEscalera);
            establecimientoRepositorio.persistir(Santander);
            establecimientoRepositorio.persistir(Carrefour);
            entidadRepositorio.persistir(Carrefour_SA);
            entidadRepositorio.persistir(BancoSantander);
        });
        entityManager().clear();

    }

}
