package ch.hftm.tankovic.service;

import ch.hftm.stankovic.module.Product;
import ch.hftm.stankovic.service.ProductMessageService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
public class ProductMessageServiceTest {


    private ProductMessageService productMessageService;


    @BeforeEach
    public void init() {
        productMessageService = Mockito.mock(ProductMessageService.class);
    }

    @Test
    public void testSendProductCreate() {
        Product product = new Product(1L, "Product 1", 19.99);
        productMessageService.sendProductCreate(product);
        verify(productMessageService, times(1)).sendProductCreate(product);
    }

    @Test
    public void testSendProductUpdate() {
        Product product = new Product(1L, "Product 1", 19.99);
        productMessageService.sendProductUpdate(product);
        verify(productMessageService, times(1)).sendProductUpdate(product);
    }

    @Test
    public void testSendProductDelete() {
        Product product = new Product(1L, "Product 1", 19.99);
        productMessageService.sendProductDelete(product);
        verify(productMessageService, times(1)).sendProductDelete(product);
    }
}