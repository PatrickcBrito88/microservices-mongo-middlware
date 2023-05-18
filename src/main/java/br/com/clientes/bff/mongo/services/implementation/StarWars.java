package br.com.clientes.bff.mongo.services.implementation;

import br.com.clientes.bff.mongo.dtos.StarWarsDto;

public interface StarWars {

    StarWarsDto buscarDto(String peopleId);

}
