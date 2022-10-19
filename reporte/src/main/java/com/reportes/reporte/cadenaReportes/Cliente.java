package com.reportes.reporte.cadenaReportes;

import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class Cliente implements Executor {

    private ReporteHistoricoService historicoService;

    @Autowired
    public Cliente(ReporteHistoricoService historicoService){
        this.historicoService = historicoService;
    }

    @Override
    public void method(JSONObject json) throws ParseException {
        CancelarReporte cancelarReporte = new CancelarReporte();
        Recurrente recurrente = new Recurrente(cancelarReporte);
        Historico historico = new Historico(this.historicoService, recurrente);
        historico.method(json);
    }
}