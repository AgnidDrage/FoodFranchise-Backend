package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.logger.LoggingManager;
import org.json.JSONObject;

public class AccionNula implements Executor{

    public AccionNula(){}
    private LoggingManager logger = new LoggingManager(AccionNula.class);

    @Override
    public void method(JSONObject json) {
        if (json.get("accion").equals("nada")){
            logger.info("Nada para hacer en este momento...");
        }
    }
}
