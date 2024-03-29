package com.franquicia.backend.cadenaResponsabilidad;

import com.franquicia.backend.logger.LoggingManager;
import com.franquicia.backend.producto.Producto;
import com.franquicia.backend.producto.ProductoService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
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
    private LoggingManager logger = new LoggingManager(Menu.class);

    @Autowired
    public Menu(Executor exe, ProductoService productoService){
        this.next = exe;
        this.listaProductos = new ArrayList<>();
        this.productoService = productoService;
        this.productosViejos = new ArrayList<>(); //Productos que ya estan en la DB
        this.productosActuales = new ArrayList<>(); //Productos que vienen en el JSOn
    }
    @Override
    public void method(JSONObject json){
        if(json.get("accion").equals("menu")){
            JSONArray jarr = new JSONArray(json.get("menus").toString());
            for(int i=0; i<jarr.length();i++){
                listaProductos.add(jarr.getJSONObject(i));
            }
            logger.warn("Actualizando productos.");
            //Update process
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

    //Agrega o actualiza productos en la DB
    public void updateProductos (){
        List<Producto> productosActuales = this.productosActuales;
        productosActuales.forEach(producto -> {
            String nombre = producto.getNombre();
            Optional<Producto> dbProducto = this.productoService.productoByNombre(nombre);
            if (dbProducto.isPresent()) {
                producto.setId(dbProducto.get().getId());
            } else {
                logger.warn("Se agrega producto a la base de datos: " + producto.getNombre());
            }
            producto.setActivo(true);
            this.productoService.addProducto(producto);
        });

    }

    public void desactivarProductos () {
        List<Producto> productosActuales = this.productosActuales;
        List<Producto> productosActiv = this.productoService.productoByActivo(true);
        for (Producto prodAct : productosActiv) {
            Boolean shouldDeactivate = true;
            System.out.println(prodAct);
            for (Producto producto : productosActuales) {

                if (producto.getNombre().equals(prodAct.getNombre())) {
                    shouldDeactivate = false;
                    break;
                }
            }
            if (shouldDeactivate) {
                logger.warn("Desactivando producto: " + prodAct.getNombre());
                prodAct.setActivo(false);
                this.productoService.addProducto(prodAct);
            }
        }
    }
}
