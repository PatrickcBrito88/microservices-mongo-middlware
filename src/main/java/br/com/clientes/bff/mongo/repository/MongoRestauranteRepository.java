package br.com.clientes.bff.mongo.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface MongoRestauranteRepository {

    MongoCollection<Document> getAll();

    void alterItem(String id, Document documentAtualizado);
}
