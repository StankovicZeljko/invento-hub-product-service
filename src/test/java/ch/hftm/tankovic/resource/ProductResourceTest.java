package ch.hftm.tankovic.resource;

import ch.hftm.stankovic.module.Product;
import ch.hftm.stankovic.repository.ProductRepository;
import ch.hftm.stankovic.resource.ProductResource;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductResourceTest {
    @InjectMocks
    private ProductResource productResource;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1", 19.99);
        Product product2 = new Product(2L, "Product 2", 29.99);
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.listAll()).thenReturn(products);

        List<Product> result = productResource.getAllProducts();

        assertEquals(products, result);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Product 1", 19.99);

        when(productRepository.findById(1L)).thenReturn(product);

        Product result = productResource.getProductById(1L);

        assertEquals(product, result);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "Product 1", 19.99);

        when(productRepository.findByName(product.getName())).thenReturn(null);

        Response response = productResource.createProduct(product);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        verify(productRepository, times(1)).persist(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, "Product 1", 19.99);

        when(productRepository.findById(1L)).thenReturn(product);

        Response response = productResource.updateProduct(1L, product);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(product, response.getEntity());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Product 1", 19.99);

        when(productRepository.findById(1L)).thenReturn(product);

        Response response = productResource.deleteProduct(1L);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(productRepository, times(1)).delete(product);
    }
}