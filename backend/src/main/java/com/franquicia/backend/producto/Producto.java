package com.franquicia.backend.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franquicia.backend.venta.Venta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

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
    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Venta> venta;
}