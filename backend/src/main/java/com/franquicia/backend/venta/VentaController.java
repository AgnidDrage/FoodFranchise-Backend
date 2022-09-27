package com.franquicia.backend.venta;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public HttpStatus addVenta(@RequestBody Venta venta) { return  ventaService.addVenta(venta);}

    @GetMapping(path = "/api/ventaByFecha")
    /*
    * Ejemplo para usar endpoint (Escribir URL de esa manera):
    * http://localhost:8080/api/ventaByFecha?inicio=2022-08-24T12:12:12z&fin=2022-09-28T12:12:12z
    */
    public List<Venta> ventasByFecha(@RequestParam String inicio, @RequestParam String fin) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'z'");
        Date fechaInicio = df.parse(inicio);
        Date fechaFinal = df.parse(fin);
        return ventaService.ventasFecha(fechaInicio, fechaFinal);
    }
}
