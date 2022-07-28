package com.franquicia.backend.franquicia;

import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.threads.ThreadConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class FranquiciaService {
    private final Franquicia franquicia;
    private RestService restService;
    private ThreadConsulta threadConsulta;

    @Autowired
    public FranquiciaService(Franquicia franquicia, RestService restService, ThreadConsulta threadConsulta) {
        this.franquicia = franquicia;
        this.restService = restService;
        this.threadConsulta = threadConsulta;
    }

    public String getUuid() {
        String uuid = franquicia.getFranquiciaID();
        return uuid;
    }

    @Bean
    public void runThreadConsulta() {
        threadConsulta.start();
    }

}
