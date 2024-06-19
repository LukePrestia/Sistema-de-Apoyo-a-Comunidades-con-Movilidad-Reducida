package controllers;

import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.Incidente;
import domain.persona.Comunidad;
import domain.persona.Usuario;
import domain.repositorios.ComunidadRepositorio;
import domain.repositorios.EstablecimientoRepositorio;
import domain.repositorios.ServicioRepositorio;
import domain.repositorios.UsuarioRepositorio;
import domain.servicio.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

import java.util.*;

public class ComunidadController implements ICrudViewsHandler, WithSimplePersistenceUnit {
    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        withTransaction(()-> {
            List<Comunidad> TodosIncidentes = ComunidadRepositorio.getInstancia().buscarTodos(Comunidad.class);
            model.put("comunidades", TodosIncidentes);
        });
        if(context.sessionAttribute("unido")!=null){
            if(context.sessionAttribute("unido").equals(true)){
                model.put("unido",true);
                context.sessionAttribute("unido",false);
            }
        }

        model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        context.render("comunidades/comunidades.hbs", model);
    }



    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        withTransaction(()-> {
            List<Establecimiento> todosEstablecimientos = EstablecimientoRepositorio.getInstancia().buscarTodos(Establecimiento.class);
            List<Servicio> todosServicios = ServicioRepositorio.getInstancia().buscarTodos(Servicio.class);
            model.put("establecimientos", todosEstablecimientos);
            model.put("servicios", todosServicios);

        });
        if(context.sessionAttribute("creado")!=null){
            if(context.sessionAttribute("creado").equals(true)){
                model.put("creado",true);
                context.sessionAttribute("creado",false);
            }
        }

        model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        context.render("comunidades/crearcomunidades.hbs", model);
    }

    @Override
    public void save(Context context) {
        Map<String, Object> model = new HashMap<>();
        Servicio servicio;
        Establecimiento establecimiento;
        Integer confianza;
        String nombre;
        if (!Objects.equals(context.formParam("establecimiento"), null)) {
            long idestablecimiento = Long.valueOf(context.formParam("establecimiento"));
            establecimiento = EstablecimientoRepositorio.getInstancia().buscarPorId(Establecimiento.class,idestablecimiento);
        } else {
            establecimiento = null;
        }
        if (!Objects.equals(context.formParam("servicio"), null)) {
            long idservicio = Long.valueOf(context.formParam("servicio"));
            servicio = ServicioRepositorio.getInstancia().buscarPorId(Servicio.class,idservicio);
        } else {
            servicio = null;
        }
        if (!Objects.equals(context.formParam("confianza"), null)) {
            confianza = Integer.valueOf(context.formParam("confianza"));
        } else {
            confianza = null;
        }
        if (!Objects.equals(context.formParam("nombre"), null)) {
            nombre= context.formParam("nombre");
        }else {
            nombre = null;
        }
        List<Establecimiento> establecimientosObservados = new ArrayList<>();
        List<Servicio> serviciosObservados = new ArrayList<>();
        establecimientosObservados.add(establecimiento);
        serviciosObservados.add(servicio);
        withTransaction(()-> {
            Comunidad comunidad = new Comunidad();
            comunidad.setConfianza(confianza);
            comunidad.setNombre(nombre);
            comunidad.setServiciosObservados(serviciosObservados);
            comunidad.setEstablecimientosObservados(establecimientosObservados);
            ComunidadRepositorio.getInstancia().persistir(comunidad);
        });
        context.sessionAttribute("creado",true);
        context.redirect("/comunidades/crear");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {
        long id = context.sessionAttribute("usuario_id");
        UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstancia();
        Usuario usuario = usuarioRepositorio.buscarPorId(Usuario.class,id);
        Comunidad comunidad;
        Boolean rol;
        if (!Objects.equals(context.formParam("comunidad"), null)) {
           long idcomunidad = Long.valueOf(context.formParam("comunidad"));
             comunidad = ComunidadRepositorio.getInstancia().buscarPorId(Comunidad.class,idcomunidad);
        } else {
            comunidad = null;
        }
        if (!Objects.equals(context.formParam("rol"), null)) {
            rol =  Boolean.valueOf(context.formParam("rol"));
        } else {
            rol = false;
        }
        withTransaction(()-> {
            usuario.getPersona().entrarComunidad(comunidad,rol);
        });
        context.sessionAttribute("unido",true);
        context.redirect("/comunidades");
    }

    @Override
    public void delete(Context context) {

    }
}
