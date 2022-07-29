package com.franquicia.backend.threads;

import com.franquicia.backend.cadenaResponsabilidad.Cliente;
import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.franquicia.Franquicia;
import com.franquicia.backend.producto.ProductoService;
import lombok.NoArgsConstructor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ThreadConsulta extends Thread{
    private RestService restService;
    private Franquicia franquicia;

    private ProductoService productoService;

    @Autowired
    public  ThreadConsulta(RestService restService, Franquicia franquicia, Cliente cliente, ProductoService productoService) {
        this.restService = restService;
        this.franquicia = franquicia;
        this.productoService = productoService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = restService.consultar(franquicia.getFranquiciaID());
                JSONObject json = new JSONObject(data);
                // formatear datos go here
                //System.out.println(json);
                Cliente cliente = new Cliente(this.productoService);
                cliente.method(json);
                ThreadConsulta.sleep(3600000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
