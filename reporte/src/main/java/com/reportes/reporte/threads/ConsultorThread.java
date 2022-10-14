package com.reportes.reporte.threads;

import com.reportes.reporte.Reporte.historico.ReporteHistorico;
import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrente;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrenteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class ConsultorThread extends Thread {

    private ReporteHistoricoService historicoService;
    private ReporteRecurrenteService recurrenteService;

    @Autowired
    public  ConsultorThread(ReporteHistoricoService historicoService, ReporteRecurrenteService recurrenteService) {
        this.historicoService = historicoService;
        this.recurrenteService = recurrenteService;
    }

    @Override
    public void run() {
        List<ReporteHistorico> reportesHistoricos = this.historicoService.findActives(true);
        List<ReporteRecurrente> reportesRecurrentes = this.recurrenteService.findRecurrenteByActive(true);
        reportesHistoricos.forEach(reporte -> {
            reporte.setId(reporte.getId());
            reporte.setProcesando(false);
            this.historicoService.addReporteHistorico(reporte);
        });
        reportesRecurrentes.forEach(reporteRec -> {
            reporteRec.setId(reporteRec.getId());
            reporteRec.setProcesando(false);
            this.recurrenteService.addReporteRecurrente(reporteRec);
        });
        while (true){
            try {
                Date fechaActual = new Date();
                reportesHistoricos = this.historicoService.findActives(true);
                reportesRecurrentes = this.recurrenteService.findRecurrenteByActive(true);
                reportesHistoricos.forEach(reporte -> {
                    if (reporte.getFechaInicio().compareTo(fechaActual) <= 0 && !reporte.getProcesando()) {
                        System.out.println("Codigo que instancia hilo para procesar reporte historico");
                    }
                });
                reportesRecurrentes.forEach(reporteRec -> {
                    if (reporteRec.getFechaInicio().compareTo(fechaActual) <= 0 && !reporteRec.getProcesando()) {
                        System.out.println("Codigo que instancia hilo para procesar reporte recurrente");
                    }
                });
                ConsultorThread.sleep(300000);
            } catch (RuntimeException | InterruptedException err) {
                throw new RuntimeException(err);
            }
        }
    }

}
