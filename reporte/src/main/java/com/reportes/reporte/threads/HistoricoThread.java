package com.reportes.reporte.threads;

import com.reportes.reporte.Reporte.historico.ReporteHistorico;
import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import com.reportes.reporte.dtos.VentaDTO;
import com.reportes.reporte.dtos.VentasDTO;
import com.reportes.reporte.conection.RestService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HistoricoThread extends Thread{
    private Long reporteId;
    private ReporteHistoricoService reporteHistoricoService;
    private final RestService restService = new RestService();
    private final JSONObject reporte = new JSONObject();
    private Date fechaInicio;
    private Date fechaFinal;

    @Autowired
    public HistoricoThread(ReporteHistoricoService reporteHistoricoService) {
        this.reporteHistoricoService = reporteHistoricoService;
    }

    @Override
    public void run(){
        VentasDTO ventasDTO = this.restService.getVentas(this.fechaInicio.toString(), this.fechaFinal.toString());
        List<VentaDTO> ventas = ventasDTO.getVentas();
        List<JSONObject> productos = new ArrayList<>();
        ventas.forEach(venta -> {
            System.out.println(venta.toString());
            JSONObject data = new JSONObject();
            data.put("fecha", venta.getFechaVenta());
            data.put("ventaId", venta.getVentaId());
            data.put("menu", venta.getMenu().getId());
            data.put("precio", venta.getMenu().getPrecio());
            productos.add(data);
        });
        this.reporte.put("accion", "respuesta_reporte");
        this.reporte.put("datos", productos);
        this.restService.sendReporte(this.reporte);
        Optional<ReporteHistorico> dbReporte = this.reporteHistoricoService.findById(this.reporteId);
        dbReporte.get().setId(dbReporte.get().getId());
        dbReporte.get().setActive(false);
        dbReporte.get().setProcesando(false);
        this.reporteHistoricoService.addReporteHistorico(dbReporte.get());
    }

}
