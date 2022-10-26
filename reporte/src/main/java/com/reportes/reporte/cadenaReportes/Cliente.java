package com.reportes.reporte.cadenaReportes;

import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrenteService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class Cliente implements Executor {

    private ReporteHistoricoService historicoService;
    private ReporteRecurrenteService recurrenteService;

    @Autowired
    public Cliente(ReporteHistoricoService historicoService, ReporteRecurrenteService recurrenteService){
        this.historicoService = historicoService;
        this.recurrenteService = recurrenteService;
    }

    @Override
    public void method(JSONObject json) throws ParseException {
        CancelarReporte cancelarReporte = new CancelarReporte(this.historicoService, this.recurrenteService);
        Recurrente recurrente = new Recurrente(this.recurrenteService, cancelarReporte);
        Historico historico = new Historico(this.historicoService, recurrente);
        historico.method(json);
    }
}