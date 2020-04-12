package product.model;

public class Product {
    private String ID;
    private String name;
    private String brand;
    private int price;
    private String description;

    public Product(String ID, String name, String brand, int price, String description) {
        this.ID = ID;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
    }

    public Product() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "Product Info: " + "\n" +
                "ID: '" + ID + "\n" +
                "Name: " + name + "\n" +
                "Brand: " + brand + "\n" +
                "Price: " + price + "\n" +
                "Description: " + description;
    }
}
