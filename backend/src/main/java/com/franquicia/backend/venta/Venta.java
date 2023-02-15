package com.franquicia.backend.venta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franquicia.backend.producto.Producto;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Producto menu;
    private Double precio;
    //private Timestamp fechaVenta = Timestamp.from(Instant.now());
    private Date fechaVenta;

    {
        try {
            fechaVenta = getRealDate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Date getRealDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaActual = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaActual);
        cal.add(Calendar.HOUR_OF_DAY, -3);
        String fechaReal = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) +1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return df.parse(fechaReal);


    }

}
