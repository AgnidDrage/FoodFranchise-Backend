package com.franquicia.backend.venta;

import com.franquicia.backend.producto.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaDTO {
    private Long ventaId;
    private String fechaVenta;
    private JSONObject menu;
}
