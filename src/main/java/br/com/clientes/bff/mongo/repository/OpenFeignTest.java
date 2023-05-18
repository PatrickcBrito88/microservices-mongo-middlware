package br.com.clientes.bff.mongo.repository;

import br.com.clientes.bff.mongo.dtos.StarWarsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "star-wars", url = "${star.wars.url}")
public interface OpenFeignTest {

    @GetMapping(value = "/{peopleId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    StarWarsDto buscarStarWars(@PathVariable String peopleId);

}
