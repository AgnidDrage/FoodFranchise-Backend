package com.franquicia.backend.threads;

import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.franquicia.Franquicia;
import com.franquicia.backend.franquicia.FranquiciaService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ThreadConsulta extends Thread{
    private RestService restService;
    private Franquicia franquicia;

    @Autowired
    public  ThreadConsulta(RestService restService, Franquicia franquicia) {
        this.restService = restService;
        this.franquicia = franquicia;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = restService.consultar(franquicia.getFranquiciaID());
                // formatear datos go here
                System.out.println(data);
                ThreadConsulta.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
