package com.franquicia.backend.carritoCompras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }
    public Optional<CarroCompras> carroById(Long id) {
        return carroRepository.findById(id);
    }

    public HttpStatus addItem(CarroCompras carroCompras){
        carroRepository.save(carroCompras);
        return HttpStatus.CREATED;
    }
}
