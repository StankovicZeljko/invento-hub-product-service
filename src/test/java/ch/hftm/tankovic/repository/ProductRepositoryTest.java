package ch.hftm.tankovic.repository;

import ch.hftm.stankovic.module.Product;
import ch.hftm.stankovic.repository.ProductRepository;
import io.quarkus.test.InjectMock;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ProductRepositoryTest {
    @InjectMock
    ProductRepository productRepository;

    @Test
    public void testFindByName() {
        Product product = new Product(1L, "Test Product", 100.0);
        Mockito.when(productRepository.findByName("Test Product")).thenReturn(product);

        Product found = productRepository.findByName("Test Product");
        assertEquals(product, found);
    }
}
