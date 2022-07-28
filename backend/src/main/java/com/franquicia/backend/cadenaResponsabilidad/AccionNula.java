package com.franquicia.backend.cadenaResponsabilidad;

import org.json.JSONObject;

public class AccionNula implements Executor{

    public AccionNula(){}

    @Override
    public void method(JSONObject cuerpo1) {
        if (cuerpo1.get("accion").equals("nada")){
            System.out.println("Nada para hacer, por el momento...");
        }
    }
}
