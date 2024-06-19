package domain.services;


import domain.organismo_de_control.Organismo;
import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.OrganismoRepositorio;
import domain.repositorios.PrestadoraRepositorio;
import org.modelmapper.ModelMapper;

public class OrganismoService {
    private  final OrganismoRepositorio organismoRepositorio;
    private  final ModelMapper modelMapper;


    public OrganismoService(OrganismoRepositorio organismoRepositorio, ModelMapper modelMapper) {
        this.organismoRepositorio = organismoRepositorio;
        this.modelMapper = modelMapper;

    }


/*
    public OrganismoDTO guardarOrganismo(OrganismoDTO organismoDTO) {

        Organismo organismo = modelMapper.map(organismoDTO, Organismo.class);

        Organismo nuevoOrganismo = organismoRepositorio.save(organismo);

        OrganismoDTO orgDTOnew = modelMapper.map(nuevoOrganismo, OrganismoDTO.class);

        return orgDTOnew;
    }*/

}