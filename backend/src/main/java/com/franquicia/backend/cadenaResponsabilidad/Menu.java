package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.producto.ProductoDTO;
import com.franquicia.backend.producto.ProductoRepository;
import com.franquicia.backend.producto.ProductoService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Executor{

    private Executor next;
    private List<JSONObject> listaProductos;

    public Menu(Executor exe){
        this.next = exe;
        this.listaProductos = new ArrayList<JSONObject>();
    }
    @Override
    public void method(JSONObject json){
        if(json.get("accion").equals("menu")){
            //System.out.println("accion " + json.get("accion"));
            //System.out.println("Listado "+ json.get("listado")); //Printeo el listado
            JSONArray jarr = new JSONArray(json.get("listado").toString());
            for(int i=0; i<jarr.length();i++){
                listaProductos.add(jarr.getJSONObject(i));
            }
            ProductoDTO productoDTO = new ProductoDTO().updateProductos(listaProductos);


            //Obtengo el listado. Para poder obtenerlo debo convertirlo a String. Esto es debido a
            //que es un Array Json
        }else{
            next.method(json);
        }
    }

}
