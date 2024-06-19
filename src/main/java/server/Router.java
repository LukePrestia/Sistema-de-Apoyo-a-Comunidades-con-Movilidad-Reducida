package server;
import controllers.*;
import domain.repositorios.OrganismoRepositorio;
import domain.repositorios.PrestadoraRepositorio;
import domain.services.OrganismoService;
import domain.services.PrestadoraService;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.modelmapper.ModelMapper;
import retrofit2.http.POST;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router implements WithSimplePersistenceUnit {

    public void init() {

        Server.app().routes(() -> {
            get("/", ((LandingPageController) FactoryController.controller("LandingPage"))::index);
            get("login", ((LoginController) FactoryController.controller("Login"))::index);
            post("login", ((LoginController) FactoryController.controller("Login"))::update);

            get("usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::index);
            get("usuarios/admin", ((UsuariosController) FactoryController.controller("Usuarios"))::update);

            post("usuarios/crear", ((UsuariosController) FactoryController.controller("Usuarios"))::save);
            get("usuarios/{id}/eliminar", ((UsuariosController) FactoryController.controller("Usuarios"))::delete);
            get("usuarios/{id}", ((UsuariosController) FactoryController.controller("Usuarios"))::show);
            post("usuarios/{id}/editar", ((UsuariosController) FactoryController.controller("Usuarios"))::edit);

            get("/organismos", ((OrganismoController) FactoryController.controller("Organismo"))::index);
            post("/organismos/crear", ((OrganismoController) FactoryController.controller("Organismo"))::save);

            get("/incidentes/abrir", ((IncidenteController) FactoryController.controller("Incidentes"))::create);
            //get("/incidentes/cerrar", ((IncidenteController) FactoryController.controller("Incidentes"))::edit);

            post("/incidentes", ((IncidenteController) FactoryController.controller("Incidentes"))::save);
            get("/incidentes/{id}/cerrar", ((IncidenteController) FactoryController.controller("Incidentes"))::delete);
            get("/incidentes", ((IncidenteController) FactoryController.controller("Incidentes"))::index);
            get("/incidentes/{estado}/mostrar", ((IncidenteController) FactoryController.controller("Incidentes"))::show);

            get("/ranking", ((RankingController) FactoryController.controller("Rankings"))::index);
            get("/ranking/{tipo}", ((RankingController) FactoryController.controller("Rankings"))::show);
            get("/comunidades", ((ComunidadController) FactoryController.controller("Comunidad"))::index);
            post("/comunidades", ((ComunidadController) FactoryController.controller("Comunidad"))::update);
            get("/comunidades/crear", ((ComunidadController) FactoryController.controller("Comunidad"))::create);
            post("/comunidades/crear", ((ComunidadController) FactoryController.controller("Comunidad"))::save);

            after(x->{
                entityManager().clear();
            });
            /*OrganismoController organismoController = new OrganismoController(new OrganismoService(new OrganismoRepositorio(),new ModelMapper()) );
            post("/organismo", organismoController.guardarPrestadora);*/






           /* get("servicios/crear", ((LoginController) FactoryController.controller("Servicios"))::create);
            get("servicios/{id}", ((LoginController) FactoryController.controller("Servicios"))::show);
            get("servicios/{id}/editar", ((LoginController) FactoryController.controller("Servicios"))::edit);
            post("servicios/{id}", ((LoginController) FactoryController.controller("Servicios"))::update);
            post("servicios", ((LoginController) FactoryController.controller("Servicios"))::save);
/*
            path("servicios/{id}/tareas", () -> {
                get(((TareasController) FactoryController.controller("Tareas"))::index);
            });*/
        });
    }
}
