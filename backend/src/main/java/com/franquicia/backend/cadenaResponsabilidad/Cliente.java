package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.connection.RestService;
import com.franquicia.backend.producto.ProductoService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class Cliente implements Executor {

    private ProductoService productoService;
    private RestService restService;

    public Cliente(ProductoService productoService, RestService restService){
        this.productoService = productoService;
        this.restService = restService;
    }
    @Override
    public void method(JSONObject json) {
        AccionNula accionNula = new AccionNula();
        Reporte reporte = new Reporte(accionNula, this.restService);
        Menu menu = new Menu(reporte, this.productoService);
        menu.method(json);
    }
}
