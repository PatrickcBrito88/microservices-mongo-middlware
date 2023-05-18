package br.com.clientes.bff.mongo.services.implementation;

import br.com.clientes.bff.mongo.dtos.StarWarsDto;
import br.com.clientes.bff.mongo.repository.OpenFeignTest;
import org.springframework.stereotype.Service;

@Service
public class StarWarsImpl implements StarWars{

    private final OpenFeignTest openFeignTest;

    public StarWarsImpl(OpenFeignTest openFeignTest) {
        this.openFeignTest = openFeignTest;
    }

    @Override
    public StarWarsDto buscarDto(String peopleId) {
        return openFeignTest.buscarStarWars(peopleId);
    }
}
