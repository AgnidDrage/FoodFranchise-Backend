package com.franquicia.backend.cadenaResponsabilidad;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class Cliente implements Executor {

    @Override
    public void method(JSONObject cuerpo1) {
        AccionNula accionNula = new AccionNula();
        Reporte reporte = new Reporte(accionNula);
        Menu menu = new Menu(reporte);
        menu.method(cuerpo1);
    }
}
