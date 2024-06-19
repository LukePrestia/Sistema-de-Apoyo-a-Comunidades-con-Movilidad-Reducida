package controllers;

import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.Incidente;
import domain.incidentes.TipoEstado;
import domain.persona.Comunidad;
import domain.persona.Membresia;
import domain.persona.Persona;
import domain.persona.Usuario;
import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.*;
import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.modelmapper.ModelMapper;
import server.utils.ICrudViewsHandler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class IncidenteController implements ICrudViewsHandler, WithSimplePersistenceUnit {
    private IncidenteRepositorio incidenteRepositorio;
    private EstablecimientoRepositorio establecimientoRepositorio;
    private ServicioRepositorio servicioRepositorio;
    private EntidadRepositorio entidadRepositorio;
    private TipoServicioRepositorio tipoServicioRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private ModelMapper modelMapper;

    public IncidenteController(IncidenteRepositorio incidenteRepositorio, ModelMapper modelMapper) {
        this.incidenteRepositorio = incidenteRepositorio;
        this.establecimientoRepositorio = new EstablecimientoRepositorio();
        this.servicioRepositorio = new ServicioRepositorio();
        this.entidadRepositorio = new EntidadRepositorio();
        this.tipoServicioRepositorio = new TipoServicioRepositorio();
        this.usuarioRepositorio = new UsuarioRepositorio();
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        withTransaction(()-> {
            List<Incidente> TodosIncidentes = incidenteRepositorio.buscarTodos(Incidente.class);
           /* long id = context.sessionAttribute("usuario_id");
            UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstancia();
            Usuario usuarioActual = usuarioRepositorio.buscarPorId(Usuario.class,id);
            if (usuarioActual != null) {
                Persona persona = usuarioActual.getPersona();
                if (persona != null) {
                    List<Membresia> membresias = persona.getMembresias();
                    List<Comunidad> comunidades = new ArrayList<>();
                    membresias.forEach(membresia -> {Comunidad comunidad = membresia.getComunidad();comunidades.add(comunidad);});
                    List<Incidente> incidentes = new ArrayList<>();
                    comunidades.forEach(comunidad -> {List<Incidente> incidente = comunidad.getIncidentes();incidentes.addAll(incidente);});
                    List<Long> idsDeIncidentes = incidentes.stream()
                            .map(Incidente::getId)
                            .collect(Collectors.toList());

                    List<Incidente> incidentesFiltrados = TodosIncidentes.stream()
                            .filter(incidente -> idsDeIncidentes.contains(incidente.getId()))
                            .collect(Collectors.toList());
                    model.put("incidentes", TodosIncidentes);
                }else{
                    model.put("incidentes", TodosIncidentes);
                }}else{*/
            model.put("incidentes", TodosIncidentes);
          // }
            if(context.sessionAttribute("borrado")!=null){
                if(context.sessionAttribute("borrado").equals(true)){
                    model.put("cerro",true);
                    context.sessionAttribute("borrado",false);
                }
            }
            model.put("logeado",true);
            model.put("administrador",esAdmin(context));

        });
        context.render("administracionIncidentes/listaIncidentes.hbs", model);
    }



    @Override
    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();
        String estado = context.pathParam("estado");

        if(Objects.equals(estado,"cercano")){
            withTransaction(()-> {
                List<Incidente> TodosIncidentes = incidenteRepositorio.buscarTodos(Incidente.class);
                long id_usuario = context.sessionAttribute("usuario_id");
                Usuario usuario = (Usuario) this.usuarioRepositorio.buscarPorId(Usuario.class, id_usuario);
                List<Incidente> incidentesRevisables = TodosIncidentes.stream()
                        .filter(incidente -> incidente.getEstablecimiento().getUbicacion().equals(usuario.getPersona().getUbicacion()))
                        .collect(Collectors.toList());

                List<Incidente> incidentesRevisablesAbiertos = incidentesRevisables.stream()
                        .filter(incidente -> incidente.getEstado() == TipoEstado.INCIDENTE_ABIERTO)
                        .collect(Collectors.toList());
                model.put("incidentes", incidentesRevisablesAbiertos);
            });
        }else {
            withTransaction(()-> {

                List<Incidente> TodosIncidentes = incidenteRepositorio.buscarPorEstado(Incidente.class, estado);
               // System.out.println(TodosIncidentes);
                long id = context.sessionAttribute("usuario_id");
                UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstancia();
                Usuario usuarioActual = usuarioRepositorio.buscarPorId(Usuario.class,id);
                /*if (usuarioActual != null) {
                    Persona persona = usuarioActual.getPersona();
                    if (persona != null) {
                        List<Membresia> membresias = persona.getMembresias();
                        List<Comunidad> comunidades = new ArrayList<>();
                        membresias.forEach(membresia -> {Comunidad comunidad = membresia.getComunidad();comunidades.add(comunidad);});
                        List<Incidente> incidentes = new ArrayList<>();
                        comunidades.forEach(comunidad -> {List<Incidente> incidente = comunidad.getIncidentes();incidentes.addAll(incidente);});
                        List<Long> idsDeIncidentes = incidentes.stream()
                                .map(Incidente::getId)
                                .collect(Collectors.toList());

                        List<Incidente> incidentesFiltrados = TodosIncidentes.stream()
                                .filter(incidente -> idsDeIncidentes.contains(incidente.getId()))
                                .collect(Collectors.toList());
                        model.put("incidentes", incidentesFiltrados);
                    }else {
                        model.put("incidentes", TodosIncidentes);
                    } }*/
                model.put("incidentes", TodosIncidentes);

            });
        }



        model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        context.render("administracionIncidentes/listaIncidentes.hbs", model);

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        withTransaction(()-> {
                List<TipoServicio> servicoTipo = tipoServicioRepositorio.buscarTodos(TipoServicio.class);
            List<Establecimiento> establecimientos = EstablecimientoRepositorio.getInstancia().buscarTodos(Establecimiento.class);
            List<Entidad>  entidades = EntidadRepositorio.getInstancia().buscarTodos(Entidad.class);
            model.put("entidad",entidades);
            model.put("establecimiento",establecimientos);
            model.put("servicoTipo", servicoTipo);
        });
        model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        context.render("administracionIncidentes/aperturaIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        withTransaction(()-> {
                    long id_usuario = context.sessionAttribute("usuario_id");
                    Usuario usuario = this.usuarioRepositorio.buscarPorId(Usuario.class, id_usuario);
                    Map<String, Object> model = new HashMap<>();
                    Incidente incidente = new Incidente();
                    this.llenarIncidente(incidente, context);
                    usuario.getPersona().denunciarIncidente(incidente);
                    System.out.println(incidente);
                    this.incidenteRepositorio.persistir(incidente);
                });
        context.status(HttpStatus.CREATED);
        context.redirect("login");
    }


    @Override
    public void edit(Context context) {
        Map<String, Object> model = new HashMap<>();
        /*Incidente incidente = new Incidente();
        this.llenarIncidente(incidente, context);
        System.out.println(incidente);
        this.incidenteRepositorio.persistir(incidente);
        context.status(HttpStatus.CREATED);*/
        context.redirect("/incidentes");
    }

    @Override
    public void update(Context context) {
    }

    @Override
    public void delete(Context context) {
        withTransaction(()-> {
                    Incidente incidente = (Incidente) this.incidenteRepositorio.buscarPorId(Incidente.class, Long.parseLong(context.pathParam("id")));
                    Persona usuariopersona = usuarioRepositorio.buscarPorId(Usuario.class, context.sessionAttribute("usuario_id")).getPersona();
                    incidente.setEstado(TipoEstado.INCIDENTE_CERRADO);
                    incidente.setDuracion(Duration.between(incidente.getHoraInicio(), LocalDateTime.now()));
                    if (usuariopersona.getMembresias() != null) {
                        usuariopersona.denunciarIncidente(incidente);
                    }
                });
        context.sessionAttribute("borrado",true);
        context.redirect(("/incidentes"));
    }

    private void llenarIncidente(Incidente incidente, Context context) {
            if (!Objects.equals(context.formParam("entidad"), "")) {
                Entidad entidad = entidadRepositorio.buscarPorNombre(Entidad.class, (context.formParam("entidad"))).get(0);
                incidente.setEntidad(entidad);
            }
            if (!Objects.equals(context.formParam("servicio"), "")) {
                List<Servicio> servicios = servicioRepositorio.buscarTodos(Servicio.class);
                servicios.stream().filter(x -> x.getServicio().getTipo() == (context.formParam("servicio")));
                Servicio servicio = null;
                if (servicios != null) {
                    servicio = servicios.get(0);
                }
                incidente.setServicio(servicio);
            }
            if (!Objects.equals(context.formParam("establecimiento"), "")) {
                Establecimiento establecimiento = establecimientoRepositorio.buscarPorNombre(Establecimiento.class, (context.formParam("establecimiento"))).get(0);
                incidente.setEstablecimiento(establecimiento);
            }
            //incidente.setAfectados(incidente.getAfectados());
            incidente.setEstado(TipoEstado.INCIDENTE_ABIERTO);
    }


    public void sugerirIncidentes(Context ctx) {
        long id_usuario = ctx.sessionAttribute("usuario_id");
        Usuario usuario = (Usuario) this.usuarioRepositorio.buscarPorId(Usuario.class, id_usuario);
        List<Incidente> incidentes = incidenteRepositorio.buscarTodos(Incidente.class);
        List<Incidente> incidentesRevisables = incidentes.stream()
                .filter(incidente -> incidente.getEstablecimiento().getUbicacion().equals(usuario.getPersona().getUbicacion()))
                .collect(Collectors.toList());

        List<Incidente> incidentesRevisablesAbiertos = incidentesRevisables.stream()
                .filter(incidente -> incidente.getEstado() == TipoEstado.INCIDENTE_ABIERTO)
                .collect(Collectors.toList());

        if (incidentesRevisablesAbiertos.isEmpty()) {
            // Puedes configurar un mensaje o realizar alguna otra acción si no hay incidentes sugeridos.
            ctx.result("No hay incidentes sugeridos");
        } else {
            ctx.render("sugerirIncidente.hbs", new HashMap<String,   Object>() {{
                put("sugeridos", incidentesRevisablesAbiertos);
            }});
        }
    }
}

