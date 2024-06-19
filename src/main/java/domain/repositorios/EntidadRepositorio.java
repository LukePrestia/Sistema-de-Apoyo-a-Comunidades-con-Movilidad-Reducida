package domain.repositorios;

import domain.entidad.Entidad;
import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntidadRepositorio extends BaseRepositorio<Entidad> implements WithSimplePersistenceUnit {
    private static EntidadRepositorio instancia;

    public static EntidadRepositorio getInstancia(){
        if (instancia == null){
            instancia = new EntidadRepositorio();
        }
        return instancia;
    }
    public void borrarLogico(Entidad entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
}
