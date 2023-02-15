package com.franquicia.backend.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* Para formatear usar Ctrl+Shift+Alt+L */

@RestController
public class ProductoController {
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping(path = "/api/productos")
    public List<Producto> getProductos() {
        return productoService.productoList();
    }

    @GetMapping(path = "/api/productosById/{id}")
    public Optional<Producto> getProductoById(@PathVariable(value = "id") Long id){
        return productoService.productById(id);
    }

    @GetMapping(path = "/api/productosByNombre/{nombre}")
    public Optional<Producto> getProductoByNombre(@PathVariable(value = "nombre") String nombre){
        return productoService.productoByNombre(nombre);
    }

    @GetMapping(path = "/api/productosByEstado/{activo}")
    public List<Producto> getProductoByActivo(@PathVariable(value = "activo") Boolean activo){
        return productoService.productoByActivo(activo);
    }

    @PostMapping(path = "/api/addProducto")
    public HttpStatus addProducto(@RequestBody Producto producto){
        return productoService.addProducto(producto);
    }
}
