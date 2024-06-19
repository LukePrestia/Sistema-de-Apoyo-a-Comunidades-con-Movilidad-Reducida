package server;

import controllers.OrganismoController;
import domain.repositorios.OrganismoRepositorio;
import domain.repositorios.PrestadoraRepositorio;
import domain.services.OrganismoService;
import domain.services.PrestadoraService;
import io.javalin.Javalin;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        Server.init();

/*
        PrestadoraController prestadoraController = new PrestadoraController(new PrestadoraService(new PrestadoraRepositorio(),new ModelMapper()) );
        app.post("/prestadoras", prestadoraController.guardarPrestadora);


        OrganismoController organismoController = new OrganismoController(new OrganismoService(new OrganismoRepositorio(),new ModelMapper()) );
        app.post("/organismo", organismoController.guardarPrestadora);*/
    }
}