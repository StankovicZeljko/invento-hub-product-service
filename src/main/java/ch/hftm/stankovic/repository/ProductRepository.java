package ch.hftm.stankovic.repository;

import ch.hftm.stankovic.module.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Product findByName(String productName){
        return find("name", productName).firstResult();
    }
}
