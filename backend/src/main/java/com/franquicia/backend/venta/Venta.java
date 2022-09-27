package com.franquicia.backend.venta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franquicia.backend.producto.Producto;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
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
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto menu;
    private double precio;
    //private Timestamp fechaVenta = Timestamp.from(Instant.now());
    private Date fechaVenta = new Date();

}
