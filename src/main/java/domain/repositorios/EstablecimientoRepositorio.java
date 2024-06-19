package domain.repositorios;

import domain.establecimientos.Establecimiento;
import domain.services.georef.Localizacion;
import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PrePersist;

public class EstablecimientoRepositorio extends BaseRepositorio<Establecimiento> implements WithSimplePersistenceUnit {
    private static EstablecimientoRepositorio instancia;

    public static EstablecimientoRepositorio getInstancia(){
        if (instancia == null){
            instancia = new EstablecimientoRepositorio();
        }
        return instancia;
    }
    public void borrarLogico(Establecimiento entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
        }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
}