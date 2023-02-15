package com.franquicia.backend.threads;

import com.franquicia.backend.cadenaResponsabilidad.Manager;
import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.franquicia.Franquicia;
import com.franquicia.backend.logger.LoggingManager;
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
    private LoggingManager logger = new LoggingManager(ThreadConsulta.class);

    @Autowired
    public  ThreadConsulta(RestService restService, Franquicia franquicia, ProductoService productoService) {
        this.restService = restService;
        this.franquicia = franquicia;
        this.productoService = productoService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                logger.info("Consultando al servidor principal.");
                String data = restService.consultar(franquicia.getFranquiciaID());
                JSONObject json = new JSONObject(data);
                Manager manager = new Manager(this.productoService, this.restService);
                manager.method(json);
                ThreadConsulta.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
