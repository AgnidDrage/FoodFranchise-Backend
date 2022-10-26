package com.reportes.reporte.Reporte.recurrente;

import lombok.*;

import javax.persistence.*;
import java.time.Period;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@ToString
public class ReporteRecurrente {
    @Id
    private Long id;
    private Date fechaInicio;
    private Date fechaFinal;
    private Date fechaCheckpoint;
    private String intervalo;
    private Boolean active = true;
    private Boolean procesando = false;
}
