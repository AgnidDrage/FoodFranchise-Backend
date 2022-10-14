package com.reportes.reporte.Reporte.historico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReporteHistoricoRepository extends JpaRepository<ReporteHistorico, Long> {
    List<ReporteHistorico> findByFechaInicio(Date fechaInicio);

    List<ReporteHistorico> findByActive(Boolean active);

}
