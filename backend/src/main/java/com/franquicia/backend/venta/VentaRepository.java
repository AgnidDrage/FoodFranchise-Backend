package com.franquicia.backend.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    //Para realizar el reporte historico
    List<Venta> findVentaByFechaVentaBetween(Date fechaVenta, Date fechaVenta2);

}
