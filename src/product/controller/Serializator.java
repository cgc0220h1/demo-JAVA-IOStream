package product.controller;
import java.io.*;

public class Serializator {
    public ProductManager readFromFile(String pathSource) throws IOException, ClassNotFoundException {
        ObjectInputStream object = new ObjectInputStream(new FileInputStream(pathSource));
        ProductManager productManager = (ProductManager) object.readObject();
        object.close();
        return productManager;
    }

    public void writeToFile(String pathDest, ProductManager productManager) throws IOException, ClassCastException {
        ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(pathDest));
        object.writeObject(productManager);
        object.close();
    }

    public void exportToText(String pathDest, Object object) throws IOException {
        Writer out;
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathDest));
        writer.write(object.toString());
        writer.close();
    }
}
