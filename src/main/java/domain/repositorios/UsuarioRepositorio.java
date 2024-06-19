package domain.repositorios;

import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import domain.persona.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsuarioRepositorio extends BaseRepositorio<Usuario> implements WithSimplePersistenceUnit {

    private static UsuarioRepositorio instancia;

    public static UsuarioRepositorio getInstancia(){
        if (instancia == null){
            instancia = new UsuarioRepositorio();
        }
        return instancia;
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }

    public Usuario usuarioRegistrado(Usuario usuario,List<Usuario> usuarios){
        Usuario usuarioFinal = null;

        for (Usuario usuario1 : usuarios) {
            if (usuario1.getNombreUsuario().equals(usuario.getNombreUsuario()) && usuario1.getContraseña().equals(usuario.getContraseña())) {
                usuarioFinal = usuario1;
            }
        }
        return usuarioFinal;
    }

}