package com.franquicia.backend.connection;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Service
public class RestService {
    private final RestTemplate restTemplate;

    private FranquiciaDetalle franquiciaDetalle;

    public RestService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }



    /*@Bean
    public FranquiciaDetalle getUuid() {
        String url = "http://127.0.0.1:5000/api/authenticate/operaciones";
        FranquiciaDetalle data = this.restTemplate.getForObject(url, FranquiciaDetalle.class);
        System.out.println(data);
        return data;
    }*/

    @Bean
    public FranquiciaDetalle postUuid() {
        String requestJson = "{\"accion\": \"uuid\", \"grupo\": \"Grupo X - Perez Pablo, Corvalán María\"}";
        String url = "http://127.0.0.1:5000/api/authenticate/operacionesPOST";
        HttpHeaders headers = new HttpHeaders(); //Instancia header
        headers.setContentType(MediaType.APPLICATION_JSON); //Setea config header

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers); //Empaqueta header y json en una entity
        FranquiciaDetalle data = this.restTemplate.postForObject(url, entity, FranquiciaDetalle.class); //envia la entity, recive responce y mapea en FranquiciaDetalle
        System.out.println(data);
        return data;
    }
}
