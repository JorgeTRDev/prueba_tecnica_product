package com.mycompany.prueba_tecnica_product.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Producto {

    private Integer idProducto;
    private String nombre;
    private String fec_registro;

}
