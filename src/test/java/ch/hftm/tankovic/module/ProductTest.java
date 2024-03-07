package ch.hftm.tankovic.module;

import ch.hftm.stankovic.module.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
@QuarkusTest
public class ProductTest {

    @Test
    public void testProduct(){
        Product product = new Product(1L,"Test Product", 20.00);
        Assertions.assertEquals(1L,product.getId());
        Assertions.assertEquals("Test Product", product.getName());
        Assertions.assertEquals(20.00, product.getPrice());
    }

}
