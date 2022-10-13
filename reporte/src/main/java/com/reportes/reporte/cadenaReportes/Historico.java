package com.reportes.reporte.cadenaReportes;

import org.json.JSONObject;

public class Historico implements Executor {

    private Executor next;

    public Historico(Executor exe){
        this.next = exe;
    }
    @Override
    public void method(JSONObject json) {
            if (json.getJSONObject("reporte").
                     getString("tipo").
                     equals("historico")){
                System.out.println("Reporte Historico");
            }else{
                this.next.method(json);
            }
        }
    }
