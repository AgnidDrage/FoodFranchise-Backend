package com.reportes.reporte.conection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reportes.reporte.dtos.VentasDTO;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Data
public class RestService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("server.backend")
    private String server_backend;
    @Value("server.principal.url")
    private String server_principal;

    public VentasDTO getVentas(String fechaInicio, String fechaFinal){
        String url = server_backend + "/ventaByFecha?inicio="+fechaInicio+"&fin="+fechaFinal;
        System.out.println(url);
        VentasDTO response = this.restTemplate.getForObject(url, VentasDTO.class);
        return response;
    }

    public void sendReporte (JSONObject reporte) {
        String url = server_principal + "/reporte/datos";
        String data = reporte.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(data, headers);
        System.out.println(data);
        //this.restTemplate.postForObject(url, entity, String.class);
    }
}
