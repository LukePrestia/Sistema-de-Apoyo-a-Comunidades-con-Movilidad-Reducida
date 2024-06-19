package domain.repositorios;

import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.servicio.TipoServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;

public class TipoServicioRepositorio extends BaseRepositorio<TipoServicio> implements WithSimplePersistenceUnit {

    private static TipoServicioRepositorio instancia;

    public static TipoServicioRepositorio getInstancia(){
        if (instancia == null){
            instancia = new TipoServicioRepositorio();
        }
        return instancia;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }

}