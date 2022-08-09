package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.producto.Producto;
import com.franquicia.backend.producto.ProductoService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Executor{

    private Executor next;
    private List<JSONObject> listaProductos;

    private ProductoService productoService;

    private List<Producto> productosViejos;

    private List<Producto> productosActuales;

    @Autowired
    public Menu(Executor exe, ProductoService productoService){
        this.next = exe;
        this.listaProductos = new ArrayList<JSONObject>();
        this.productoService = productoService;
        this.productosViejos = new ArrayList<Producto>();
        this.productosActuales = new ArrayList<Producto>();
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
            castearJsonProducto(listaProductos);
            traerProductosViejos();
            updateProductos();
        }else{
            next.method(json);
        }
    }

    public void castearJsonProducto(List listaProductos){
        Gson gson = new Gson();
        for (int i = 0; i < listaProductos.size(); i++) {
            this.productosActuales.add(gson.fromJson(listaProductos.get(i).toString(), Producto.class)); //Casteo del objeto json al Objeto producto
        }
    }
    public void traerProductosViejos(){
        this.productosViejos = this.productoService.productoByEstado(true); //Traigo los prod de la base de datos
    }

    public void updateProductos (){
        System.out.println(this.productosActuales + " productos actuales");
        System.out.println(this.productosViejos + " productos viejos");
        //Agregar query en repositorio para traer solo 3 campos(nombre, descripcion, precio)
    }

}
