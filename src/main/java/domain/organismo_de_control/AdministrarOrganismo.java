package domain.organismo_de_control;
import domain.entidad.Entidad;
import domain.establecimientos.Establecimiento;
import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.*;
import domain.servicio.Servicio;
import domain.servicio.TipoServicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AdministrarOrganismo {

    public void cargarOrganismo(String[] campos) {
        String nombreOrganismo = campos[1].trim();

        // Verificar si el organismo ya existe antes de guardarlo
        if (OrganismoRepositorio.getInstancia().buscarPorNombre(Organismo.class, nombreOrganismo).isEmpty()) {
            Organismo organismo = new Organismo();
            organismo.setNombre(nombreOrganismo);
            organismo.setBorrado(false);
            organismo.setPrestadoras(new ArrayList<>());
            OrganismoRepositorio.getInstancia().persistir(organismo);
        }
    }

    public void cargarPrestadora(String[] campos) {
        String nombreOrganismo = campos[1].trim();
        String nombrePrestadoras = campos[2].trim();

        Organismo organismo = OrganismoRepositorio.getInstancia().buscarPorNombre(Organismo.class, nombreOrganismo).get(0);

        // Dividir el campo de nombrePrestadoras por el separador ";"
        String[] nombresPrestadoras = nombrePrestadoras.split(";");
        for (String nombrePrestadora : nombresPrestadoras) {
            Prestadora prestadora = new Prestadora();
            prestadora.setNombre(nombrePrestadora.trim());
            prestadora.setOrganismo(organismo);
            prestadora.setEntidades(new ArrayList<>());
            prestadora.setBorrado(false);
            prestadora.setUltimoRanking(new ArrayList<>());

            // Verificar si la prestadora ya existe en el organismo antes de agregarla
            if (!organismo.getPrestadoras().contains(prestadora)) {
                organismo.getPrestadoras().add(prestadora);
            }
        }
    }

    public void cargarEntidad(String[] campos) {
        String nombrePrestadora = campos[1].trim();
        String nombreEntidades = campos[2].trim();

        Prestadora prestadora = PrestadoraRepositorio.getInstancia().buscarPorNombre(Prestadora.class, nombrePrestadora).get(0);

        // Dividir el campo de nombreEntidades por el separador ";"
        String[] nombresEntidades = nombreEntidades.split(";");
        for (String nombreEntidad : nombresEntidades) {
            Entidad entidad = new Entidad();
            entidad.setPrestadora(prestadora);
            entidad.setNombre(nombreEntidad.trim());
            entidad.setBorrado(false);
            entidad.setListEstablecimiento(new ArrayList<>());

            // Verificar si la entidad ya existe en la prestadora antes de agregarla
            if (!prestadora.getEntidades().contains(entidad)) {
                prestadora.getEntidades().add(entidad);
            }
        }
    }

    public void cargarEstablecimiento(String[] campos) {
        String nombreEntidad = campos[1].trim();
        String nombreEstablecimientos = campos[2].trim();
        Entidad entidad = EntidadRepositorio.getInstancia().buscarPorNombre(Entidad.class, nombreEntidad).get(0);

        // Dividir el campo de nombreEstablecimientos por el separador ";"
        String[] nombresEstablecimientos = nombreEstablecimientos.split(";");
        for (String nombreEstablecimiento : nombresEstablecimientos) {
            Establecimiento establecimiento = new Establecimiento();
            establecimiento.setEntidad(entidad);
            establecimiento.setNombre(nombreEstablecimiento.trim());
            establecimiento.setBorrado(false);
            establecimiento.setServicios(new ArrayList<>());
            establecimiento.setUbicacion(null);
            // Verificar si el establecimiento ya existe en la entidad antes de agregarlo
            if (!entidad.getListEstablecimiento().contains(establecimiento)) {
                entidad.getListEstablecimiento().add(establecimiento);
            }
        }
    }

    public void cargarServicio(String[] campos) {
        String nombreEstablecimiento = campos[1].trim();
        String nombresServicios = campos[2].trim();

        Establecimiento establecimiento = EstablecimientoRepositorio.getInstancia().buscarPorNombre(Establecimiento.class, nombreEstablecimiento).get(0);

        // Dividir el campo de nombresServicios por el separador ";"
        String[] nombresServiciosArray = nombresServicios.split(";");
        for (String nombreServicio : nombresServiciosArray) {
            Servicio servicio = new Servicio();
            TipoServicio servicioTipo = new TipoServicio();
            servicioTipo.setTipo(nombreServicio.trim());
            servicio.setServicio(servicioTipo);
            servicio.setEstablecimiento(establecimiento);
            servicio.setFunciona(true);
            servicio.setBorrado(false);

            // Verificar si el servicio ya existe en el establecimiento antes de agregarlo
            if (!establecimiento.getServicios().contains(servicio)) {
                establecimiento.getServicios().add(servicio);
            }
        }
    }

}