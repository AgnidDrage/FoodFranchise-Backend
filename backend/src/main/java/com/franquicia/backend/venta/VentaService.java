package com.franquicia.backend.venta;

import com.franquicia.backend.logger.LoggingManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private LoggingManager logger = new LoggingManager(VentaService.class);

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> ventaList() { return ventaRepository.findAll(); }

    public Optional<Venta> ventaById(Long id) { return ventaRepository.findById(id); }

    public HttpStatus addVenta(Venta venta) {
        logger.info("Generando venta.");
        ventaRepository.save(venta);
        return HttpStatus.CREATED;
    }

    public List<Venta> ventasFecha(Date fechaInicio, Date fechaFinal) {
        return ventaRepository.findVentaByFechaVentaBetween(fechaInicio, fechaFinal);
    }
}
