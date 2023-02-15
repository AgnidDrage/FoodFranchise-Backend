package com.reportes.reporte.conection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reportes.reporte.dtos.VentasDTO;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
public class RestService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${server.backend.url}")
    private String server_backend;
    @Value("${server.principal.url}")
    private String server_principal;
    @Value("${token}")
    private String token;

    public VentasDTO getVentas(String fechaInicio, String fechaFinal){
        System.out.println(server_backend);
        String url = server_backend + "/ventaByFecha?inicio="+fechaInicio+"&fin="+fechaFinal;
        VentasDTO response = this.restTemplate.getForObject(url, VentasDTO.class);
        return response;
    }

    public void sendReporte (JSONObject reporte) {
        String url = server_principal + "/reporte/datos";
        String data = reporte.toString();
        //try {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity(data, headers);
        System.out.println(data);
        System.out.println(reporte);
        String response = this.restTemplate.postForObject(url, entity, String.class);
        //}catch (RuntimeException e) {
        //    System.out.println(e);
        //    System.out.println("Fallo el envio");
        //    System.out.println(data);
        //}

    }

}
