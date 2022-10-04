package com.reportes.reporte.Reporte;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@ToString
public class Reporte_historico {
    @Id
    private Long id;
    private String tipo;
    private Timestamp fechaInicio;
    private Timestamp fechaFinal;
    private String intervalo;

}
