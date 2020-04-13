package product.controller;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class Serializator {
    private ProductManager productManager;
    private File file;

    public Serializator(ProductManager productManager) {
        this.productManager = productManager;
    }

    public Serializator() {
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public File getFile() throws IOException {
        if (file == null) {
            throw new FileNotFoundException("File not found");
        }
        return file;
    }

    public ProductManager readFromFile(String pathSource) throws IOException, ClassNotFoundException {
        ObjectInputStream object = new ObjectInputStream(new FileInputStream(pathSource));
        productManager = (ProductManager) object.readObject();
        object.close();
        file = new File(pathSource);
        return productManager;
    }

    public void writeToFile(String pathDest) throws IOException, ClassCastException {
        file = new File(pathDest);
        if (file.exists()) {
            throw new FileAlreadyExistsException(pathDest);
        }
        ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(pathDest));
        object.writeObject(productManager);
        object.close();
    }

    public void writeToFile() throws IOException, ClassCastException {
        ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));
        object.writeObject(productManager);
        object.close();
    }

    public void exportToText(String pathDest, Object object) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathDest));
        writer.write(object.toString());
        writer.close();
    }
}
