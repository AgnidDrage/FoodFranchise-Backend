package com.reportes.reporte.Reporte.historico;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@ToString
public class ReporteHistorico {
    @Id
    private Long id;
    private Date fechaInicio;
    private Date fechaFinal;
    private Boolean active = true;
    private Boolean procesando = false;
}
