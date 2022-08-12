package com.franquicia.backend.venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VentaController {
    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping(path = "/api/ventas")
    public List<Venta> getVentas() { return ventaService.ventaList(); }

    @GetMapping(path = "/api/ventaById/{id}")
    public Optional<Venta> getVentaById(@PathVariable(value = "id") Long id) {
        return ventaService.ventaById(id);
    }

    @PostMapping(path = "/api/addVenta")
    public HttpStatus addVenta(@RequestBody Venta venta) { return  ventaService.addVenta(venta); }
}
