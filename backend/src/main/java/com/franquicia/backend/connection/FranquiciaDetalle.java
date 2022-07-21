package com.franquicia.backend.connection;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FranquiciaDetalle implements Serializable {
    private String accion;
    private String grupo;
}
