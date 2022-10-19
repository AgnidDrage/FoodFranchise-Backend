package com.reportes.reporte.Reporte;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class ReporteController {

    public ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostMapping(path="/api/reporte")
    public HttpStatus reporte(@RequestBody String reporte) throws ParseException {
        JSONObject data = new JSONObject(reporte);
        // Cliente cliente = new Cliente();
        // Para probar la cadena de responsabilidad: cliente.method(data);
        return reporteService.reporClassificator(data);
    }

}
