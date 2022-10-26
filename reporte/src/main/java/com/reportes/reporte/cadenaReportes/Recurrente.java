package com.reportes.reporte.cadenaReportes;

import com.reportes.reporte.Reporte.recurrente.ReporteRecurrente;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrenteService;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recurrente implements Executor {

    private ReporteRecurrenteService recurrenteService;
    private Executor next;

    public Recurrente(ReporteRecurrenteService recurrenteService, Executor exe) {
        this.recurrenteService = recurrenteService;
        this.next = exe;
    }

    @Override
    public void method(JSONObject json) throws ParseException {
        if (json.getJSONObject("reporte").
                getString("tipo").
                equals("recurrente")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Long reporteId = json.getJSONObject("reporte").getLong("id");
            String fechaInicioString = json.getJSONObject("reporte").getString("fechaInicio");
            String fechaFinalString = json.getJSONObject("reporte").getString("fechaFin");
            Date fechaInicio = df.parse(fechaInicioString);
            Date fechaFin = df.parse(fechaFinalString);
            String intervalo = json.getJSONObject("reporte").getString("intervalo");
            ReporteRecurrente reporteRecurrente = new ReporteRecurrente(reporteId, fechaInicio, fechaFin, fechaInicio, intervalo, true, false);
            this.recurrenteService.addReporteRecurrente(reporteRecurrente);
        } else {
            this.next.method(json);
        }
    }
}