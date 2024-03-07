package ch.hftm.stankovic.service;

import ch.hftm.stankovic.module.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class ProductMessageService {

    @Inject
    @Channel("product-create")
    Emitter<Product> productCreateEmitter;

    @Inject
    @Channel("product-update")
    Emitter<Product> productUpdateEmitter;

    @Inject
    @Channel("product-delete")
    Emitter<Product> productDeleteEmitter;


    public void sendProductCreate(Product product) {
        this.productCreateEmitter.send(product);
    }

    public void sendProductUpdate(Product product) {
        this.productUpdateEmitter.send(product);
    }

    public void sendProductDelete(Product product) {
        this.productDeleteEmitter.send(product);
    }

}