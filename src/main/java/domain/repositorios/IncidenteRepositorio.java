package domain.repositorios;

import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.Incidente;
import domain.organismo_de_control.Organismo;
import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class IncidenteRepositorio extends BaseRepositorio<Incidente> implements WithSimplePersistenceUnit {
    private static IncidenteRepositorio instancia;

    public static IncidenteRepositorio getInstancia(){
        if (instancia == null){
            instancia = new IncidenteRepositorio();
        }
        return instancia;
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }


    public Incidente save(Incidente entidad) {
        try {
            EntityManager entityManager = getEntityManager();
            if (entidad.getId() == 0L) {
                // Si la entidad no tiene una ID asignada, se trata de una nueva entidad.
                entityManager.persist(entidad);
            } else {
                // Si la entidad ya tiene una ID, se actualiza en la base de datos.
                entidad = entityManager.merge(entidad);
            }
           } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores adecuado aquí
        }
        return entidad;
    }

}


