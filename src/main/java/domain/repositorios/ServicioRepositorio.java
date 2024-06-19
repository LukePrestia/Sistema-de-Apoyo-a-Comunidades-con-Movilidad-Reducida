package domain.repositorios;

import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ServicioRepositorio extends BaseRepositorio<Servicio> implements WithSimplePersistenceUnit {
    private static ServicioRepositorio instancia;

    public static ServicioRepositorio getInstancia(){
        if (instancia == null){
            instancia = new ServicioRepositorio();
        }
        return instancia;
    }

    public void buscarInstancia(Servicio servicio)
    {
        TipoServicio tipoServicioBD = entityManager().find(TipoServicio.class, servicio.getServicio().getTipo());
        if(tipoServicioBD!=null)
        {servicio.setServicio(tipoServicioBD);}
    }
    public void borrarLogico(Servicio entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
        }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
}