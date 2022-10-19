package com.reportes.reporte.cadenaReportes;

import org.json.JSONObject;

import java.text.ParseException;

public interface Executor {
    public void method (JSONObject json) throws ParseException;
}
