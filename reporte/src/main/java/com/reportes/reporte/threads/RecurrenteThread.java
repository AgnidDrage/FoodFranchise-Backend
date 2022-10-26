package com.reportes.reporte.threads;

import com.reportes.reporte.Reporte.recurrente.ReporteRecurrente;
import com.reportes.reporte.Reporte.recurrente.ReporteRecurrenteService;
import com.reportes.reporte.conection.RestService;
import com.reportes.reporte.dtos.VentaDTO;
import com.reportes.reporte.dtos.VentasDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@AllArgsConstructor
@Service
public class RecurrenteThread extends Thread{
    private Long reporteId;
    private ReporteRecurrenteService recurrenteService;
    private final RestService restService = new RestService();
    private final JSONObject reporte = new JSONObject();
    private Date fechaCheckpoint;
    private Date fechaFinal;
    private String intervalo;

    @Autowired
    public RecurrenteThread(ReporteRecurrenteService recurrenteService) {
        this.recurrenteService = recurrenteService;
    }
    //"yyyy-MM-dd'T'HH:mm:ssZ"

    @SneakyThrows
    @Override
    public void run() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Duration intervalo = Duration.parse(this.intervalo);
        Calendar calPast = Calendar.getInstance();
        Calendar calFuture = Calendar.getInstance();
        calPast.setTime(this.fechaCheckpoint);
        calFuture.setTime(this.fechaCheckpoint);
        calPast.add(Calendar.HOUR_OF_DAY, -intervalo.toHoursPart());
        calFuture.add(Calendar.HOUR_OF_DAY, intervalo.toHoursPart());
        String pastTime = calPast.get(Calendar.YEAR) + "-" + (calPast.get(Calendar.MONTH) +1) + "-" + calPast.get(Calendar.DATE) + " " + calPast.get(Calendar.HOUR_OF_DAY) + ":" + calPast.get(Calendar.MINUTE) + ":" + calPast.get(Calendar.SECOND);
        String nextCheckpoint = calFuture.get(Calendar.YEAR) + "-" + (calFuture.get(Calendar.MONTH) +1) + "-" + calFuture.get(Calendar.DATE) + " " + calFuture.get(Calendar.HOUR_OF_DAY) + ":" + calFuture.get(Calendar.MINUTE) + ":" + calFuture.get(Calendar.SECOND);
        VentasDTO ventasDTO = this.restService.getVentas(pastTime, this.fechaCheckpoint.toString());
        List<VentaDTO> ventas = ventasDTO.getVentas();
        List<JSONObject> productos = new ArrayList<>();
        ventas.forEach(venta -> {
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

        Date nextCheckpointDate = df.parse(nextCheckpoint);

        Optional<ReporteRecurrente> dbReporte = this.recurrenteService.findById(this.reporteId);
        dbReporte.get().setId(dbReporte.get().getId());
        dbReporte.get().setFechaCheckpoint(nextCheckpointDate);
        dbReporte.get().setProcesando(false);
        if (nextCheckpointDate.compareTo(this.fechaFinal) > 0) {
            dbReporte.get().setActive(false);
        }
        this.recurrenteService.addReporteRecurrente(dbReporte.get());

    }

}
