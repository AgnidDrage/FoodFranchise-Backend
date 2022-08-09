package com.franquicia.backend.producto;

import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductoDTO{

    public void updateProductos(List<JSONObject> jsonObjects, ProductoRepository productoRepository){
        System.out.println(productoRepository.findAll());
    }


}
