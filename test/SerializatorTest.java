import org.junit.jupiter.api.Test;
import product.controller.ProductManager;
import product.controller.Serializator;
import product.model.Product;

import java.io.IOException;

class SerializatorTest {

    @Test
    void readFromFile() {
        ProductManager productManager;
        Serializator serializator = new Serializator();
        String pathSource = "text/Demo";
        try {
            productManager = serializator.readFromFile(pathSource);
            System.out.println(productManager);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void writeToFile() {
        Product product1 = new Product("1309763","iphone","apple",10000000,"sang chanh");
        ProductManager productManager = new ProductManager();
        productManager.add(1,product1);
        String pathDest = "test/product/controller/man.dat";
        Serializator serializator = new Serializator(productManager);
        try {
            serializator.writeToFile(pathDest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}