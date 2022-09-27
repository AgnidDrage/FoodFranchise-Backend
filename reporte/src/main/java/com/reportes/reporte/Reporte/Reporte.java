package com.reportes.reporte.Reporte;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reporte {

    private Long id;
    private String tipo;
    private Timestamp fechaInicio;
    private Timestamp fechaFinal;
    private String intervalo;

}
