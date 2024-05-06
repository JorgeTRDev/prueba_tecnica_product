package com.mycompany.prueba_tecnica_product.expose;

import com.mycompany.prueba_tecnica_product.business.ProductoService;
import com.mycompany.prueba_tecnica_product.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductoController {

    private final ProductoService productService;

    @Autowired
    public ProductoController(ProductoService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public List<Producto> agregarProducto(@RequestBody Producto producto) {
        return productService.insertarProducto(producto);
    }


}