package com.reportes.reporte.cadenaReportes;

import org.json.JSONObject;

public class Recurrente implements Executor {

    private Executor next;

    public Recurrente(Executor exe) {
        this.next = exe;
    }

    @Override
    public void method(JSONObject json) {
        if (json.getJSONObject("reporte").
                 getString("tipo").
                 equals("recurrente")) {
            System.out.println("Reporte recurrente");
        }else{
            this.next.method(json);
        }
    }
}