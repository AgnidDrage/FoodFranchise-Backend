package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.producto.Producto;
import com.franquicia.backend.producto.ProductoService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu implements Executor{

    private final Executor next;
    private final List<JSONObject> listaProductos;

    private final ProductoService productoService;

    private List<Producto> productosViejos;

    private final List<Producto> productosActuales;
    private Producto producto;

    @Autowired
    public Menu(Executor exe, ProductoService productoService){
        this.next = exe;
        this.listaProductos = new ArrayList<>();
        this.productoService = productoService;
        this.productosViejos = new ArrayList<>();
        this.productosActuales = new ArrayList<>();
    }
    @Override
    public void method(JSONObject json){
        if(json.get("accion").equals("menu")){
            JSONArray jarr = new JSONArray(json.get("listado").toString());
            for(int i=0; i<jarr.length();i++){
                listaProductos.add(jarr.getJSONObject(i));
            }
            System.out.println("menu");
            castearJsonProducto(listaProductos);
            desactivarProductos();
            updateProductos();
        } else {
            next.method(json);
        }
    }

    public void castearJsonProducto(List<JSONObject> listaProductos){
        Gson gson = new Gson();
        for (Object listaProducto : listaProductos) {
            this.productosActuales.add(gson.fromJson(listaProducto.toString(), Producto.class)); //Casteo del objeto json al Objeto producto
        }
    }


    public void updateProductos (){
        List<Producto> productosActuales = this.productosActuales;
        productosActuales.forEach(producto -> {
            String nombre = producto.getNombre();
            Optional<Producto> dbProducto = this.productoService.productoByNombre(nombre);
            if (dbProducto.isPresent()) {
                producto.setId(dbProducto.get().getId());
            }
            producto.setEstado(true);
            this.productoService.addProducto(producto);
        });

    }

    public void desactivarProductos () {
        List<Producto> productosActuales = this.productosActuales;
        List<Producto> productosActiv = this.productoService.productoByEstado(true);
        for (Producto prodAct : productosActiv) {
            Boolean shouldDeactivate = true;
            for (Producto producto : productosActuales) {
                if (producto.getNombre().equals(prodAct.getNombre())) {
                    shouldDeactivate = false;
                    break;
                }
            }
            if (shouldDeactivate) {
                prodAct.setEstado(false);
                this.productoService.addProducto(prodAct);
            }
        }
    }
}
