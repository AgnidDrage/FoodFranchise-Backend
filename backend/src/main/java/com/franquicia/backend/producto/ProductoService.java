package com.franquicia.backend.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> productoList() {
        return productoRepository.findAll();
    }

    public Optional<Producto> productById(Long id) {return productoRepository.findById(id);}

    public Optional<Producto> productoByNombre(String nombre) {return productoRepository.findByNombre(nombre);}

    public List<Producto> productoByActivo(Boolean activo) {return productoRepository.findByActivo(activo);}

    public HttpStatus addProducto(Producto producto) {
        productoRepository.save(producto);
        return HttpStatus.CREATED;
    }
}
