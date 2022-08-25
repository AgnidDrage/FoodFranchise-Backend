package com.franquicia.backend.venta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franquicia.backend.producto.Producto;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@ToString
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto menus;
    private double precio;
    private Timestamp fechaVenta = Timestamp.from(Instant.now());

}
