package domain.repositorios;

import domain.services.georef.Localizacion;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;

public class LocalizacionRepositorio extends BaseRepositorio<Localizacion> implements WithSimplePersistenceUnit {
    private static domain.repositorios.LocalizacionRepositorio instancia;

    public static domain.repositorios.LocalizacionRepositorio getInstancia(){
        if (instancia == null){
            instancia = new domain.repositorios.LocalizacionRepositorio();
        }
        return instancia;
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager();
    }
    public void buscarInstancias(Localizacion localizacion){
        Provincia provinciaBD = entityManager().find(Provincia.class, localizacion.getProvincia().getIdp());
        if(provinciaBD!=null)
        {localizacion.setProvincia(provinciaBD);}
        Municipio municipioBD = entityManager().find(Municipio.class, localizacion.getMunicipio().getIdm());
        if(municipioBD!=null)
        {localizacion.setMunicipio(municipioBD);}
    }
}