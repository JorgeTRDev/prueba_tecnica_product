package com.mycompany.prueba_tecnica_product.business;

import com.mycompany.prueba_tecnica_product.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    List<Producto> insertarProducto(Producto producto);

}
