package com.reportes.reporte.Reporte.historico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteHistoricoService {
    private ReporteHistoricoRepository repository;

    @Autowired
    public ReporteHistoricoService(ReporteHistoricoRepository repository){
        this.repository = repository;
    }

    public List<ReporteHistorico> findReportes(Date fechaInicio) {
        return this.repository.findByFechaInicio(fechaInicio);
    }

    public List<ReporteHistorico> findActives(Boolean active) {
        return this.repository.findByActive(active);
    }

    public void addReporteHistorico(ReporteHistorico reporteHistorico) {
        this.repository.save(reporteHistorico);
    }


}
