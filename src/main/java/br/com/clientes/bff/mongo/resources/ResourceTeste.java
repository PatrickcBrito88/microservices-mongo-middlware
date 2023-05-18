package br.com.clientes.bff.mongo.resources;


import br.com.clientes.bff.mongo.dtos.StarWarsDto;
import br.com.clientes.bff.mongo.services.implementation.StarWars;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("teste")
public class ResourceTeste {

    private AmazonS3 s3Client;
    private Regions clientRegion;
    private String bucketName="testeurlambientedev";
    private StarWars starWars;

    public ResourceTeste(StarWars starWars) {
        this.starWars = starWars;
        this.initialize("sa-east-1");
    }

    private void setRegion(String regiao) {
        try {
            this.clientRegion = Regions.fromName(regiao);
        } catch (IllegalArgumentException var3) {
            throw new IllegalArgumentException("Região inexistente. Consulte a URL https://docs.aws.amazon.com/pt_br/general/latest/gr/rande.html para saber as regiões disponíveis.");
        }
    }

    private void initialize(String regiao) {
        this.setRegion(regiao);
        this.s3Client = (AmazonS3)((AmazonS3ClientBuilder)AmazonS3ClientBuilder.standard().withRegion(this.clientRegion)).build();
    }


    @GetMapping("/teste-get")
    public String teste(){
        return "Teste";
    }

    @PostMapping("/envia-arquivo-s3")
    public String enviaArquivoS3() throws IOException {
        File txtFile = new File("fileName");
        String objKey = "testes3";
        try (InputStream is = new ByteArrayInputStream("teste".getBytes())) {
            FileUtils.copyInputStreamToFile(is, txtFile);
            enviaArquivo(txtFile, objKey);
        } catch (Exception e) {
            throw new AmazonS3Exception("Um erro ocorreu ao enviar o arquivo do Txt para o S3. Causa: " + e.getLocalizedMessage());
        } finally {
            txtFile.delete();
        }
        return "Arquivo enviado com sucesso";
    }

    public void enviaArquivo(File arquivo, String objectKeyName) {
        this.s3Client.putObject(this.bucketName, objectKeyName, arquivo);
    }

    @GetMapping("/consome-api")
    public StarWarsDto consomeApi(){
        return starWars.buscarDto("1");
    }

}
