package com.reportes.reporte.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ventaDTO {
    private Long ventaId;
    private MenuDTO menu;
    private String fechaVenta;
}
