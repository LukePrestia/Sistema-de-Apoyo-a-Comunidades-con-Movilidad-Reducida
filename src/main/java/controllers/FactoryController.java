package controllers;


import domain.repositorios.*;
import domain.services.OrganismoService;
import domain.services.PrestadoraService;
import org.modelmapper.ModelMapper;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Login": controller = new LoginController(new UsuarioRepositorio()); break;
            case "Usuarios": controller = new UsuariosController(new UsuarioRepositorio()); break;
            case "Organismo": controller = new OrganismoController(new OrganismoService(new OrganismoRepositorio(),new ModelMapper()), new ModelMapper(), new OrganismoRepositorio());break;
            case "Incidentes": controller = new IncidenteController(new IncidenteRepositorio(),new ModelMapper());break;
            case "Rankings": controller = new RankingController(new RankingRepositorio(),new ModelMapper());break;
            case "LandingPage": controller = new LandingPageController();break;
            case "Comunidad": controller = new ComunidadController();break;

        }
        return controller;
    }
}
