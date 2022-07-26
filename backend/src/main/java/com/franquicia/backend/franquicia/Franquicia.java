package com.franquicia.backend.franquicia;

import lombok.*;

        import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Franquicia implements Serializable {
    private String accion;
    private String franquiciaID;
}
