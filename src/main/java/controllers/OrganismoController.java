package controllers;


import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.incidentes.Incidente;
import domain.incidentes.TipoEstado;
import domain.organismo_de_control.AdministrarOrganismo;
import domain.organismo_de_control.CargaMasivaWeb;
import domain.organismo_de_control.Organismo;
import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.*;
import domain.services.OrganismoService;
import domain.services.PrestadoraService;

import domain.servicio.Servicio;
import domain.servicio.TipoServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import org.modelmapper.ModelMapper;
import server.utils.ICrudViewsHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class OrganismoController implements ICrudViewsHandler, WithSimplePersistenceUnit {
    private OrganismoService organismoService ;

    private ModelMapper modelMapper;

    public OrganismoController(OrganismoService organismoService,ModelMapper modelMapper, OrganismoRepositorio organismoRepositorio) {
        this.organismoService = organismoService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("logeado",true);
        model.put("administrador",esAdmin(context));
        context.render("crearOrganismo.hbs",model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }
    @Override
    public void save(Context context) {
        try (
                InputStream archivoOrganismo = context.uploadedFile("archivo_organismos").content();
                InputStream archivoPrestadoras = context.uploadedFile("archivo_prestadoras").content();
                InputStream archivoEntidades = context.uploadedFile("archivo_entidades").content();
                InputStream archivoEstablecimientos = context.uploadedFile("archivo_establecimientos").content();
                InputStream archivoServicios = context.uploadedFile("archivo_servicios").content()
        ) {
            withTransaction(() -> {
                try {
                    cargarArchivo(archivoOrganismo, Organismo.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cargarArchivo(archivoPrestadoras, Prestadora.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cargarArchivo(archivoEntidades, Entidad.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cargarArchivo(archivoEstablecimientos, Establecimiento.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cargarArchivo(archivoServicios, Servicio.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            // Manejo de errores: Registra el error o notifica al usuario
        }
        context.redirect("/login");
    }

    private void cargarArchivo(InputStream archivo, Class<?> clase) throws IOException {
        AdministrarOrganismo administrar = new AdministrarOrganismo();
        if (archivo != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(",");
                    String tipoClase = campos[0].trim();

                    // Asegúrate de que la clase proporcionada coincida con el tipo de clase leído
                    if (clase.getSimpleName().equalsIgnoreCase(tipoClase)) {
                        switch (tipoClase) {
                            case "ORGANISMO":
                                administrar.cargarOrganismo(campos);
                                break;
                            case "PRESTADORA":
                                administrar.cargarPrestadora(campos);
                                break;
                            case "ENTIDAD":
                                administrar.cargarEntidad(campos);
                                break;
                            case "ESTABLECIMIENTO":
                                administrar.cargarEstablecimiento(campos);
                                break;
                            case "SERVICIO":
                                administrar.cargarServicio(campos);
                                break;
                            default:
                                // Manejo de error: Tipo de clase no reconocido
                                break;
                        }
                    } else {
                        // Manejo de error: La clase proporcionada no coincide con el tipo de clase leído
                    }
                }
            }
        }
    }

    private void cerrarRecurso(InputStream recurso) {
        if (recurso != null) {
            try {
                recurso.close();
            } catch (IOException e) {
                // Manejo de errores al cerrar el archivo
            }
        }
    }

    private void llenarOrganismo(Organismo organismo, Context context) {
        if (!Objects.equals(context.formParam("nombre"), "")) {
            organismo.setNombre(context.formParam("nombre"));
        }
        organismo.setPrestadoras(null);
    }


    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
