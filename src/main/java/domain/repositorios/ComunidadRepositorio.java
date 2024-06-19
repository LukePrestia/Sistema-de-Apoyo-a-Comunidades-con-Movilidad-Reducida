package domain.repositorios;

import domain.persona.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.*;

import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;

public class ComunidadRepositorio extends BaseRepositorio<Comunidad> implements WithSimplePersistenceUnit {
    private static ComunidadRepositorio instancia;

    public static ComunidadRepositorio getInstancia(){
        if (instancia == null){
            instancia = new ComunidadRepositorio();
        }
        return instancia;
    }
    public void borrarLogico(Comunidad entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
}

