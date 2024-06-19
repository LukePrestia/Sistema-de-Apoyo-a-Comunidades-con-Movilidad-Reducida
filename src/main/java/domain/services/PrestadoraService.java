package domain.services;

import domain.prestadora_de_servicio.Prestadora;
import domain.repositorios.PrestadoraRepositorio;
import io.javalin.http.Context;
import org.modelmapper.ModelMapper;

public class PrestadoraService {
    private final PrestadoraRepositorio prestadoraRepositorio;
    private final ModelMapper modelMapper;


    public PrestadoraService(PrestadoraRepositorio prestadoraRepositorio, ModelMapper modelMapper) {
        this.prestadoraRepositorio = prestadoraRepositorio;
        this.modelMapper = modelMapper;

    }



}
