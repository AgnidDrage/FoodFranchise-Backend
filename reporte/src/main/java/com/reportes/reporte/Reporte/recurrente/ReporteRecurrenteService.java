package com.reportes.reporte.Reporte.recurrente;

import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteRecurrenteService {
    private final ReporteRecurrenteRepository repository;

    @Autowired
    public ReporteRecurrenteService(ReporteRecurrenteRepository repository){
        this.repository = repository;
    }

    public List<ReporteRecurrente> findReportesRecurrentes(Date fechaInicial) {
        return repository.findByFechaInicio(fechaInicial);
    }

    public List<ReporteRecurrente> findRecurrenteByActive(Boolean active) {
        return repository.findByActive(active);
    }

    public void addReporteRecurrente(ReporteRecurrente reporteRecurrente){
        this.repository.save(reporteRecurrente);
    }

}
