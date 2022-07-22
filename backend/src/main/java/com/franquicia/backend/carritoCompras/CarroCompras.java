package com.franquicia.backend.carritoCompras;

import com.franquicia.backend.producto.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class CarroCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long id_carrito;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="menu", nullable = false)
    private Producto menu;
    private double precio_unitario;
}
