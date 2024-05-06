package com.mycompany.prueba_tecnica_product.business.impl;

import com.mycompany.prueba_tecnica_product.business.ProductoService;
import com.mycompany.prueba_tecnica_product.model.Producto;
import com.mycompany.prueba_tecnica_product.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {


    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> insertarProducto(Producto producto) {

        return productoRepository.insertarProducto(producto);

    }

}
