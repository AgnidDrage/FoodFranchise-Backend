package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.producto.ProductoService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class Cliente implements Executor {

    private ProductoService productoService;

    public Cliente(ProductoService productoService){
        this.productoService = productoService;
    }
    @Override
    public void method(JSONObject json) {
        AccionNula accionNula = new AccionNula();
        Reporte reporte = new Reporte(accionNula);
        Menu menu = new Menu(reporte, this.productoService);
        menu.method(json);
    }
}
