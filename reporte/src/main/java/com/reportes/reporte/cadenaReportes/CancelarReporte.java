package com.reportes.reporte.cadenaReportes;

import com.reportes.reporte.Reporte.historico.ReporteHistorico;
import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrente;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrenteService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public class CancelarReporte implements Executor{

    private ReporteHistoricoService historicoService;
    private ReporteRecurrenteService recurrenteService;

    @Autowired
    public CancelarReporte(ReporteHistoricoService historicoService, ReporteRecurrenteService recurrenteService){
        this.historicoService = historicoService;
        this.recurrenteService = recurrenteService;
    }

    @Override
    public void method(JSONObject json) throws ParseException {
        if(json.getJSONObject("reporte").
                getString("tipo").
                equals("cancelar")){
            System.out.println("Cancelar reporte");
            Long id = json.getJSONObject("reporte").getLong("reporteCanceladoId");
            Optional<ReporteHistorico> reporteH = this.historicoService.findById(id);
            if (reporteH.isEmpty()) {
                Optional<ReporteRecurrente> reporteR = this.recurrenteService.findById(id);
                Long canceledId = reporteR.get().getId();
                Date fechaInicio = reporteR.get().getFechaInicio();
                Date fechaFinal = reporteR.get().getFechaFinal();
                Date fechaCheckpoint = reporteR.get().getFechaCheckpoint();
                String intervalo = reporteR.get().getIntervalo();
                ReporteRecurrente reporteRCanceled = new ReporteRecurrente(canceledId, fechaInicio, fechaFinal, fechaCheckpoint, intervalo, false, false);
                this.recurrenteService.addReporteRecurrente(reporteRCanceled);
            } else {
                Long canceledId = reporteH.get().getId();
                Date fechaInicio = reporteH.get().getFechaInicio();
                Date fechaFinal = reporteH.get().getFechaFinal();
                ReporteHistorico reporteHCanceled = new ReporteHistorico(canceledId, fechaInicio, fechaFinal, false, false);
                this.historicoService.addReporteHistorico(reporteHCanceled);

            }

        }

    }



}
