package com.reportes.reporte.cadenaReportes;

import com.reportes.reporte.Reporte.historico.ReporteHistorico;
import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Historico implements Executor {

    private ReporteHistoricoService historicoService;
    private Executor next;

    public Historico(ReporteHistoricoService historicoService, Executor exe){
        this.next = exe;
        this.historicoService = historicoService;
    }
    @Override
    public void method(JSONObject json) throws ParseException {
            if (json.getJSONObject("reporte").
                     getString("tipo").
                     equals("historico")){
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Long reporteId = json.getJSONObject("reporte").getLong("id");
                String fechaInicioString = json.getJSONObject("reporte").getString("fechaInicio");
                String fechaFinalString = json.getJSONObject("reporte").getString("fechaFin");
                Date fechaInicio = df.parse(fechaInicioString);
                Date fechaFin = df.parse(fechaFinalString);
                ReporteHistorico reporteHistorico = new ReporteHistorico(reporteId, fechaInicio, fechaFin);
                this.historicoService.addReporteHistorico(reporteHistorico);
            }else{
                this.next.method(json);
            }
        }
    }
