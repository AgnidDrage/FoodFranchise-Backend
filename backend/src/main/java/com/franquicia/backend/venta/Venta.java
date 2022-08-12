package com.franquicia.backend.venta;

import com.franquicia.backend.producto.Producto;
import lombok.*;

import javax.persistence.*;

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
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu", nullable = false)
    private Producto menu;
    private double precio;

}
