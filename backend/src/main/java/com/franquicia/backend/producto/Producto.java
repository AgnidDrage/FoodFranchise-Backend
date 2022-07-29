package com.franquicia.backend.producto;

import com.franquicia.backend.carritoCompras.CarroCompras;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String urlImagen;
    private Boolean estado;
}