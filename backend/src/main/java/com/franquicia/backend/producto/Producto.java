package com.franquicia.backend.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franquicia.backend.venta.Venta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String urlImagen;
    private Boolean activo;
    private Timestamp creado;
    private Timestamp actualizado;

    @JsonIgnore
    @OneToOne(mappedBy = "menu")
    private Venta venta;
}