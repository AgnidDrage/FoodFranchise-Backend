package com.franquicia.backend.threads;

import com.franquicia.backend.cadenaResponsabilidad.Cliente;
import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.franquicia.Franquicia;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ThreadConsulta extends Thread{
    private RestService restService;
    private Franquicia franquicia;

    private Cliente cliente;

    @Autowired
    public  ThreadConsulta(RestService restService, Franquicia franquicia, Cliente cliente) {
        this.restService = restService;
        this.franquicia = franquicia;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = restService.consultar(franquicia.getFranquiciaID());
                // formatear datos go here
                JSONObject json = new JSONObject(data);
                System.out.println(json.get("accion"));
                cliente.method(json);
                ThreadConsulta.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
