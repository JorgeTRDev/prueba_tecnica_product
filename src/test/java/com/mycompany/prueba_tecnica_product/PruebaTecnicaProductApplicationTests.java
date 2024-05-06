package com.mycompany.prueba_tecnica_product;

import com.mycompany.prueba_tecnica_product.model.Producto;
import com.mycompany.prueba_tecnica_product.repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PruebaTecnicaProductApplicationTests {

	@Mock
	private ProductoRepository productoRepository;

	@Test
	@Transactional
	public void testInsertarProducto() {
		Producto productoInput = new Producto();
		productoInput.setNombre("TEST_PRODUCT");
		productoInput.setFec_registro("2024-05-05");

		List<Producto> productList = productoRepository.insertarProducto(productoInput);

		assertEquals(0, productList.size());
	}

	@Test
	@Transactional
	public void testInsertarProducto_Error() {
		when(productoRepository.insertarProducto(any(Producto.class)))
				.thenThrow(new RuntimeException("ERROR_INSERT"));

		Producto productoInput = new Producto();
		productoInput.setNombre("TEST_PRODUCT");
		productoInput.setFec_registro("2024-05-05");

		Throwable exception = assertThrows(RuntimeException.class, () -> {
			productoRepository.insertarProducto(productoInput);
		});

		assertTrue(exception.getMessage().contains("ERROR_INSERT"));
	}
}
