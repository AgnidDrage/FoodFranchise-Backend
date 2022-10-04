package com.reportes.reporte.Reporte;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    public HttpStatus reporClassificator(JSONObject reporte) {
        System.out.println(reporte);
        return HttpStatus.ACCEPTED;
    }

}
