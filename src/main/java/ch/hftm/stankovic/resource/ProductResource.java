package ch.hftm.stankovic.resource;

import ch.hftm.stankovic.module.Product;
import ch.hftm.stankovic.repository.ProductRepository;
import ch.hftm.stankovic.service.ProductMessageService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    @Inject
    ProductRepository repository;
    @Inject
    ProductMessageService productMessageService;
    @GET
    public List<Product> getAllProducts(){
        return repository.listAll();
    }
    @GET
    @Path("/{id}")
    public Product getProductById(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @POST
    @Transactional
    public Response createProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Product name must not be empty").build();
        }

        Product existingProduct = repository.findByName(product.getName());
        if (existingProduct != null) {
            return Response.status(Response.Status.CONFLICT).entity("Product with this name already exists").build();
        }

        if (product.getPrice() == null || product.getPrice() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Product price must be set").build();
        }

        repository.persist(product);
        productMessageService.sendProductCreate(product);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, Product product) {

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Product name must not be empty").build();
        }

        if (product.getPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Product price must be set").build();
        }

        Product existingProduct = repository.findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            productMessageService.sendProductUpdate(product);
            return Response.ok(existingProduct).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id) {
        Product product = repository.findById(id);
        if (product != null) {
            repository.delete(product);
            productMessageService.sendProductDelete(product);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
