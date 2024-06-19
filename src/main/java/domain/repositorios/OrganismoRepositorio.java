package domain.repositorios;

import domain.organismo_de_control.Organismo;
import domain.prestadora_de_servicio.Prestadora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;

import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;

public class OrganismoRepositorio extends BaseRepositorio<Organismo> implements WithSimplePersistenceUnit {
    private static OrganismoRepositorio instancia;

    public static OrganismoRepositorio getInstancia(){
        if (instancia == null){
            instancia = new OrganismoRepositorio();
        }
        return instancia;
    }
    public void borrarLogico(Organismo entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
        }
    public Organismo save(Organismo entidad) {
            EntityManager entityManager = getEntityManager();
            if (entidad.getId() == 0L) {
                // Si la entidad no tiene una ID asignada, se trata de una nueva entidad.
                entityManager.persist(entidad);
            } else {
                // Si la entidad ya tiene una ID, se actualiza en la base de datos.
                entidad = entityManager.merge(entidad);
            }
            return entidad;
        }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
}