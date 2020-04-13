package product.model;

import java.io.Serializable;

public class Product implements Serializable,Comparable<Product> {
    private static final long serialVersionUID = 1L;
    private String barCode;
    private String name;
    private String brand;
    private int price;
    private String description;

    public Product(String barCode, String name, String brand, int price, String description) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.barCode = barCode;
        this.description = description;
    }

    public Product() {
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
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
                "Name: " + name + "\n" +
                "Brand: " + brand + "\n" +
                "Price: " + price + "\n" +
                "Bar Code: '" + barCode + "\n" +
                "Description: " + description;
    }

    @Override
    public int compareTo(Product anotherProduct) {
        if (this.name.compareTo(anotherProduct.getName()) > 0) {
            return 1;
        } else if (this.name.compareTo(anotherProduct.getName()) < 0) {
            return -1;
        }
        return 0;
    }
}
