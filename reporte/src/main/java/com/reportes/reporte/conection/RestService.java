package com.reportes.reporte.conection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reportes.reporte.dtos.VentasDTO;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Data
public class RestService {
    private final RestTemplate restTemplate = new RestTemplate();

    public VentasDTO getVentas(String fechaInicio, String fechaFinal){
        String url = "http://localhost:8080/api/ventaByFecha?inicio="+fechaInicio+"&fin="+fechaFinal;
        System.out.println(url);
        VentasDTO response = this.restTemplate.getForObject(url, VentasDTO.class);
        return response;
    }

    public void sendReporte (JSONObject reporte) {
        String url = "http://10.101.102.1:8080/api/reporte/datos";
        String data = reporte.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(data, headers);
        //this.restTemplate.postForObject(url, entity, String.class);
        System.out.println(data);
    }
}
