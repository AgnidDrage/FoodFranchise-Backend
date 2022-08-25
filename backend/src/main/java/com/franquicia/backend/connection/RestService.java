package com.franquicia.backend.connection;

import com.franquicia.backend.franquicia.Franquicia;
import lombok.Data;
import lombok.ToString;
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
        String requestJson = "{\"accion\": \"uuid\", \"grupo\": \"Grupo 2 - Sanchez Mariano, Barroso Oriel\"}";
        String url = "http://10.101.102.1:8080/api/authenticate/obtener-uuid";
        HttpHeaders headers = new HttpHeaders(); //Instancia header
        headers.setContentType(MediaType.APPLICATION_JSON); //Setea config header
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvcmllbGJhcnJvc28iLCJhdXRoIjoiIiwiZXhwIjoxOTc2ODE3MTY2fQ.SZ40no8SezkBd_7xa1QpSMVxlbaKtI_jKc8vE7VGCQliCKBcKWstKmUzzltsZqL_e27dDam-cHLNGK2EKM4dSg");

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers); //Empaqueta header y json en una entity
        Franquicia data = this.restTemplate.postForObject(url, entity, Franquicia.class); //envia la entity, recive responce y mapea en Franquicia
        System.out.println(data);
        return data;
    }

    public String consultar(String uuid) {
        String requestJson = String.format("{\"accion\": \"consulta\", \"franquiciaID\": \"%s\"}", uuid);
        String url = "http://10.101.102.1:8080/api/accion";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvcmllbGJhcnJvc28iLCJhdXRoIjoiIiwiZXhwIjoxOTc2ODE3MTY2fQ.SZ40no8SezkBd_7xa1QpSMVxlbaKtI_jKc8vE7VGCQliCKBcKWstKmUzzltsZqL_e27dDam-cHLNGK2EKM4dSg");
        HttpEntity<String> entity = new HttpEntity(requestJson, headers);
        String data = this.restTemplate.postForObject(url, entity, String.class);
        System.out.println(data);
        return data;
    }


}
