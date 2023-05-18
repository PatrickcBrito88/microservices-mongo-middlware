package br.com.clientes.bff.mongo.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MongoRestauranteRepositoryImpl implements MongoRestauranteRepository {

    private static final String COLLECTION_NAME = "restaurants";
    private static final String DATABASE_NAME = "sample_restaurants";

    private final MongoTemplate mongoTemplate;
    private final MongoConverter mongoConverter;

    @Autowired
    public MongoRestauranteRepositoryImpl(MongoTemplate mongoTemplate, MongoConverter mongoConverter) {
        this.mongoTemplate = mongoTemplate;
        this.mongoConverter = mongoConverter;
    }


//    @Override
//    public List<Document> getAllRestaurants() {
//        MongoCollection<Document> collection = getCollection();
//        return (List<Document>) collection.find().limit(5);
//        List<Document> restaurantes = new ArrayList<>();
//        for (Document document : iterable) {
//            restaurantes.add(document);
//        }
//        return restaurantes;

    //    @Override
//    public String alteraRestaurante() {
//        return null;
//    }
    @Override
    public MongoCollection<Document> getAll() {
        return getCollection();
    }

    @Override
    public void alterItem(String id, Document documentAtualizado) {
        Query query = new Query(Criteria.where("restaurant_id").is(id));
        Document documentoExistente = mongoTemplate.findOne(query, Document.class, COLLECTION_NAME);

        if (documentoExistente != null) {
            // Modificar os campos desejados
            for (String key : documentAtualizado.keySet()) {
                Object value = documentAtualizado.get(key);
                if (value != null) {
                    documentoExistente.put(key, value);
                }
            }
            mongoTemplate.save(documentoExistente, COLLECTION_NAME);
            System.out.println("Sucesso");
        }
    }

    private MongoCollection<Document> getCollection() {
        return mongoTemplate.getCollection(COLLECTION_NAME);
    }
}