package br.com.clientes.bff.mongo.services.implementation;

import org.bson.Document;

import java.util.List;

public interface RestauranteService {

    List<Document> getAllRestaurants();
}
