package com.franquicia.backend.cadenaResponsabilidad;

import org.json.JSONObject;

public class Reporte implements Executor{

    private Executor next;

    public Reporte(Executor exe){
        this.next = exe;
    }

    @Override
    public void method(JSONObject cuerpo1){
        if(cuerpo1.get("accion").equals("reporte")){
            System.out.println("Reporte");
        }else{
            next.method(cuerpo1);
        }
    }

}
