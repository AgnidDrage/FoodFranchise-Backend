package com.reportes.reporte.cadenaReportes;

import org.json.JSONObject;

public class CancelarReporte implements Executor{

    public CancelarReporte(){}

    @Override
    public void method(JSONObject json) {
        if(json.getJSONObject("reporte").
                getString("tipo").
                equals("cancelar")){
            System.out.println("Cancelar reporte");
        }

    }



}
