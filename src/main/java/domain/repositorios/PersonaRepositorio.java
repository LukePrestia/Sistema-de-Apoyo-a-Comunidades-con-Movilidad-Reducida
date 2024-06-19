package domain.repositorios;
import domain.persona.Persona;
import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersonaRepositorio extends BaseRepositorio<Persona> implements WithSimplePersistenceUnit {
    private static PersonaRepositorio instancia;

    public static PersonaRepositorio getInstancia(){
        if (instancia == null){
            instancia = new PersonaRepositorio();
        }
        return instancia;
    }
    public void borrarLogico(Persona entidad) {
        entidad.setBorrado(true);
        getEntityManager().merge(entidad);
        }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
}
