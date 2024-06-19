package domain.repositorios;
import domain.prestadora_de_servicio.Prestadora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PrestadoraRepositorio extends BaseRepositorio<Prestadora> implements WithSimplePersistenceUnit {
    private static PrestadoraRepositorio instancia;

    public static PrestadoraRepositorio getInstancia(){
        if (instancia == null){
            instancia = new PrestadoraRepositorio();
        }
        return instancia;
    }
    public void borrarLogico(Prestadora entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
        }

    public Prestadora save(Prestadora entidad) {
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