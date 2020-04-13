package product.controller;

import product.model.Product;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

public class ProductManager implements Serializable {
    private static final long serialVersionUID = 1L;
    protected HashMap<Integer, Product> productMap = new LinkedHashMap<>();
    private HashMap<Integer, Product> subList = new LinkedHashMap<>();

    public void add(Integer ID, Product product) {
        productMap.put(ID, product);
    }

    public void remove(Integer ID) {
        productMap.remove(ID);
    }

    public Product searchByID(Integer ID) {
        return productMap.get(ID);
    }

    public HashMap<Integer, Product> searchByName(String name) {
        String regex = "\\B" + name + "|" + name + "\\B";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        subList = (HashMap<Integer, Product>) productMap.clone();
        Iterator<Map.Entry<Integer, Product>> iterator = subList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> entry = iterator.next();
            if (!pattern.matcher(entry.getValue().getName()).find()) {
                iterator.remove();
            }
        }
        return subList;
    }

    public HashMap<Integer, Product> searchByBrand(String brand) {
        String regex = "\\B" + brand + "|" + brand + "\\B";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        subList = (HashMap<Integer, Product>) productMap.clone();
        Iterator<Map.Entry<Integer, Product>> iterator = subList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> entry = iterator.next();
            if (!pattern.matcher(entry.getValue().getBrand()).find()) {
                iterator.remove();
            }
        }
        return subList;
    }

    public HashMap<Integer, Product> searchByPrice(int low, int high) {
        subList = (HashMap<Integer, Product>) productMap.clone();
        Iterator<Map.Entry<Integer, Product>> iterator = subList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> entry = iterator.next();
            if (low > entry.getValue().getPrice() || high < entry.getValue().getPrice()) {
                iterator.remove();
            }
        }
        return subList;
    }

    public String toString() {
        String format = "|%3s |%21s |%12s |%10d |%21s |%13s |\n";
        StringBuilder data = new StringBuilder();
        StringBuilder header = new StringBuilder();
        StringBuilder footer = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Set<Integer> keySet = productMap.keySet();
        header.append("+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        header.append("| ID |         Name         |     Brand   |   Price   |        Bar Code      |  Description |\n");
        header.append("+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        footer.append("+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        footer.append("Tổng số: ").append(keySet.size()).append(" sản phẩm ").append("\n");
        for (Integer key : keySet) {
            data.append(String.format(format
                    , key
                    , productMap.get(key).getName()
                    , productMap.get(key).getBrand()
                    , productMap.get(key).getPrice()
                    , productMap.get(key).getBarCode()
                    , productMap.get(key).getDescription()));
        }
        result.append(header).append(data).append(footer);
        return result.toString();
    }

    public HashMap<Integer, Product> toHashMap() {
        return productMap;
    }

    public int size() {
        return productMap.size();
    }
}
