package com.reportes.reporte.threads;

import com.reportes.reporte.Reporte.historico.ReporteHistorico;
import com.reportes.reporte.Reporte.historico.ReporteHistoricoService;
import com.reportes.reporte.dtos.VentaDTO;
import com.reportes.reporte.dtos.VentasDTO;
import com.reportes.reporte.conection.RestService;
import com.reportes.reporte.logger.LoggingManager;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Service
public class HistoricoThread extends Thread{
    private Long reporteId;
    private ReporteHistoricoService reporteHistoricoService;
    private final RestService restService;
    private final JSONObject reporte = new JSONObject();
    private Date fechaInicio;
    private Date fechaFinal;

    @Autowired
    public HistoricoThread(ReporteHistoricoService reporteHistoricoService, RestService restService) {
        this.reporteHistoricoService = reporteHistoricoService;
        this.restService = restService;
    }

    @Override
    public void run(){
        VentasDTO ventasDTO = this.restService.getVentas(this.fechaInicio.toString(), this.fechaFinal.toString());
        List<VentaDTO> ventas = ventasDTO.getVentas();
        System.out.println(ventas);
        List<JSONObject> productos = new ArrayList<>();
        ventas.forEach(venta -> {
            System.out.println(venta.toString());
            JSONObject data = new JSONObject();
            data.put("fecha", changeDateFormat(venta.getFechaVenta()));
            data.put("ventaId", venta.getVentaToken());
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

    public String changeDateFormat(String dateString) {
        try {
            // Create a SimpleDateFormat object for the input format
            SimpleDateFormat inputFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

            // Parse the input string into a Date object
            Date date = inputFormat.parse(dateString);

            // Create a Calendar object and set it to the UTC time zone
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            // Set the calendar's date and time to the input date
            calendar.setTime(date);

            // Create a SimpleDateFormat object for the output format
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            // Format the calendar's date and time as a string in the output format
            String outputString = outputFormat.format(calendar.getTime());

            // Return the output string
            return outputString;
        } catch (ParseException e) {
            // Handle exception
            return null;
        }
    }


    }


