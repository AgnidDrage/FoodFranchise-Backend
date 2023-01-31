package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.logger.LoggingManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class Reporte implements Executor{

    private Executor next;
    private RestService restService;
    private LoggingManager logger = new LoggingManager(Reporte.class);

    public Reporte(Executor exe, RestService restService){
        this.next = exe;
        this.restService = restService;
    }

    @Override
    public void method(JSONObject json){
        if(json.get("accion").equals("reporte")) {
            logger.info("Iniciando reporte.");
            this.restService.sendReport(json);
        }else{
            next.method(json);
        }
    }



}
