package com.reportes.reporte.Reporte;

import com.reportes.reporte.cadenaReportes.Cliente;
import com.reportes.reporte.threads.ConsultorThread;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ReporteService {
    private Cliente cliente;
    private ConsultorThread consultorThread;

    @Autowired
    public ReporteService(Cliente cliente, ConsultorThread consultorThread) {
        this.cliente = cliente;
        this.consultorThread = consultorThread;
    }

    public HttpStatus reporClassificator(JSONObject reporte) throws ParseException {
        this.cliente.method(reporte);
        return HttpStatus.ACCEPTED;
    }

    @Bean
    public void startReportesBackend() {
        this.consultorThread.start();
    }





}
