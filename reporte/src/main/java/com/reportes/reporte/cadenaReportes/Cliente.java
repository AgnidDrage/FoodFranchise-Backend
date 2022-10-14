package com.reportes.reporte.cadenaReportes;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class Cliente implements Executor {

    public Cliente(){
    }

    @Override
    public void method(JSONObject json) {
        CancelarReporte cancelarReporte = new CancelarReporte();
        Recurrente recurrente = new Recurrente(cancelarReporte);
        Historico historico = new Historico(recurrente);
        historico.method(json);
    }
}