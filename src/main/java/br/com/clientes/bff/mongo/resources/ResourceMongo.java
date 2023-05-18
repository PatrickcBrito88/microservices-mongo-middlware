package br.com.clientes.bff.mongo.resources;

import br.com.clientes.bff.mongo.repository.MongoRestauranteRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("teste-mongo")
public class ResourceMongo {

    private final MongoRestauranteRepository mongoRestauranteRepository;

    public ResourceMongo(MongoRestauranteRepository mongoRestauranteRepository) {
        this.mongoRestauranteRepository = mongoRestauranteRepository;
    }

    @GetMapping
    public String getTest(){
        return "Teste";
    }

    @GetMapping("/list")
    public List<Document> testeListDocument(){
        MongoCollection<Document> collection = mongoRestauranteRepository.getAll();
        List<Document> restaurantes = new ArrayList<>();
        FindIterable<Document> iterable = collection.find().limit(5);
        for (Document document : iterable) {
            restaurantes.add(document);
        }
        return restaurantes;
    }

    @PutMapping("/altera-restaurante/{id}")
    public String alteraRestaurante(@PathVariable String id, @RequestBody Document documentAtualizado){
        mongoRestauranteRepository.alterItem(id, documentAtualizado);
        return "Sucesso";
    }

}
