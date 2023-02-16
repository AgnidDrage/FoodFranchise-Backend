package com.franquicia.backend.venta;
import com.franquicia.backend.producto.Producto;
import com.franquicia.backend.producto.ProductoDTO;
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
import java.util.ArrayList;
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
    * http://localhost:8080/api/ventaByFecha?inicio=2022-08-24 12:12:12&fin=2022-09-28 12:12:12
    */
    public String ventasByFecha(@RequestParam String inicio, @RequestParam String fin) throws ParseException {
        JSONObject data = new JSONObject();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = df.parse(inicio);
        Date fechaFinal = df.parse(fin);
        List<Venta> ventas = ventaService.ventasFecha(fechaInicio, fechaFinal);
        List<JSONObject> ventasJson = new ArrayList<>();
        ventas.forEach(venta -> {
            Producto oldMenu = venta.getMenu();
            ProductoDTO menu = new ProductoDTO(oldMenu.getId(), oldMenu.getPrecio());
            Long id = venta.getVentaId();
            String ventaToken = venta.getVentaToken();
            JSONObject menuJson = menu.toJson();
            String fechaVenta = venta.getFechaVenta().toString();
            ventasJson.add(new JSONObject(new VentaDTO(id, fechaVenta, menuJson, ventaToken)));
        });
        data.put("ventas", ventasJson);
        return data.toString();
    }
}
