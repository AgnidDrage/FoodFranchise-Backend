package com.franquicia.backend.connection;

import com.franquicia.backend.franquicia.Franquicia;
import lombok.Data;
import lombok.ToString;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@ToString
@Service
public class RestService {
    private final RestTemplate restTemplate;
    @Value("${server.principal.url}")
    private String principal_server;
    @Value("${server.principal.token}")
    private String token;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }



    /*@Bean
    public Franquicia getUuid() {
        String url = "http://127.0.0.1:5000/api/authenticate/operaciones";
        Franquicia data = this.restTemplate.getForObject(url, Franquicia.class);
        System.out.println(data);
        return data;
    }*/

    @Bean
    public Franquicia postUuid() {
        try {
            String requestJson = "{\"accion\": \"uuid\", \"grupo\": \"Grupo 2 - Sanchez Mariano, Barroso Oriel\"}";
            String url = principal_server + "/authenticate/obtener-uuid";
            HttpHeaders headers = new HttpHeaders(); //Instancia header
            headers.setContentType(MediaType.APPLICATION_JSON); //Setea config header
            headers.setBearerAuth(token);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers); //Empaqueta header y json en una entity
            Franquicia data = this.restTemplate.postForObject(url, entity, Franquicia.class); //envia la entity, recibe responce y mapea en Franquicia
            return data;
        } catch (RuntimeException e) {
            Franquicia franq = new Franquicia("nada", "1234");
            return franq;
        }
    }

    public String consultar(String uuid) {
        String requestJson = String.format("{\"accion\": \"consulta\", \"franquiciaID\": \"%s\"}", uuid);
        String url = principal_server + "/accion";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity(requestJson, headers);
        String data = this.restTemplate.postForObject(url, entity, String.class);
        return data;
    }

    public void sendReport(JSONObject json){
        String data = json.toString();
        String url = principal_server + "reporte";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(data, headers);
        this.restTemplate.postForObject(url, entity, String.class);
    }

}
