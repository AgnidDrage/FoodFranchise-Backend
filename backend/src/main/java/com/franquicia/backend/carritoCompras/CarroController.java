package com.franquicia.backend.carritoCompras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CarroController {

    private final CarroService carroService;

    @Autowired
    public CarroController(CarroService carroService){
        this.carroService = carroService;
    }

    @GetMapping(path = "/api/carroById/{id}")
    public Optional<CarroCompras> getCarroById(@PathVariable(value = "id") Long id){
        return carroService.carroById(id);
    }

    @PostMapping(path = "/api/postCarro/")
    public HttpStatus addProductToCarro(@RequestBody CarroCompras carroCompras){
        return carroService.addItem(carroCompras);
    }

}
