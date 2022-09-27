package com.franquicia.backend.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franquicia.backend.venta.Venta;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Boolean activo;
    private Timestamp creado;
    private Timestamp actualizado;

    @JsonIgnore
    @OneToOne(mappedBy = "menu")
    private Venta venta;
}