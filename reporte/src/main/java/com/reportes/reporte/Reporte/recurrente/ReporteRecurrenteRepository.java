package com.reportes.reporte.Reporte.recurrente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReporteRecurrenteRepository extends JpaRepository<ReporteRecurrente, Long> {
    List<ReporteRecurrente> findByFechaInicio(Date fechaInicio);
    List<ReporteRecurrente> findByActive(Boolean active);
}
