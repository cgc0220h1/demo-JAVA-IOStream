package product.controller;

import org.junit.jupiter.api.Test;
import product.model.Product;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    @Test
    void searchByName() throws IOException, ClassNotFoundException {
        ProductManager productManager = new ProductManager();
        Product product = new Product();
        product.setName("xiaomi A3");
        productManager.add(1,product);
        Product product1 = new Product();
        product1.setName("Nokia");
        productManager.add(2,product1);
        Product product2 = new Product();
        product2.setName("nokia");
        productManager.add(3,product2);
        Product product3 = new Product();
        product3.setName("iphone");
        productManager.add(4,product3);
        HashMap<Integer, Product> sublist = productManager.searchByName("no");
        System.out.println(sublist);
    }
}