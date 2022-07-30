package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.producto.Producto;
import com.franquicia.backend.producto.ProductoService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Executor{

    private Executor next;
    private List<JSONObject> listaProductos;

    private ProductoService productoService;
    @Autowired
    public Menu(Executor exe, ProductoService productoService){
        this.next = exe;
        this.listaProductos = new ArrayList<JSONObject>();
        this.productoService = productoService;
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
            System.out.println("menu");
            updateProductos(listaProductos);
        }else{
            next.method(json);
        }
    }

    public void updateProductos (List listaProductos){
        List<Producto> productosActuales = new ArrayList<Producto>();
        for (int i = 0; i < listaProductos.size(); i++) {
            Gson gson = new Gson();
            productosActuales.add(gson.fromJson(listaProductos.get(i).toString(), Producto.class)); //Casteo del objeto json al Objeto producto
        }
        List<Producto> productosViejos = this.productoService.productoList(); //Traigo los prod de la base de datos
        System.out.println(productosActuales + " productosActuales");
        System.out.println(productosViejos + " productosViejos");

        for (int i = 0; i < productosActuales.size(); i++) {
            for (int j = 0; j < productosViejos.size(); j++) {
                //Tratar de comparar ambos productos
            }
        }
    }

}
