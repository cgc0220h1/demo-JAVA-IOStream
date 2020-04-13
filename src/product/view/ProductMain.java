package product.view;

import product.controller.ProductManager;
import product.controller.Serializator;
import product.model.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class ProductMain {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductManager productManager;
    private static Serializator serializator = new Serializator();

    public static void main(String[] args) {
        boolean isNextOption = false;
        char confirm;
        String input;
        int ID;
        Product product;
        while (!isNextOption) {
            System.out.println("----------Main Menu----------");
            System.out.println("1. Mở file quản lý");
            System.out.println("2. Tạo file quản lý mới");
            System.out.println("0. Thoát chương trình");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Nhập đường dẫn file: ");
                    String source = scanner.nextLine();
                    try {
                        productManager = serializator.readFromFile(source);
                        isNextOption = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("File không tồn tại!");
                    } catch (ClassNotFoundException | StreamCorruptedException e) {
                        System.out.println("File không phù hợp!");
                    } catch (IOException e) {
                        System.out.println("Có lỗi xảy ra!");
                    }
                    break;
                case 2:
                    productManager = new ProductManager();
                    serializator = new Serializator(productManager);
                    isNextOption = true;
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp! Vui lòng chọn lại");
            }
        }

        if (productManager == null) {
            System.exit(0);
        }

        while (isNextOption) {
            displayList(productManager.toHashMap());
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Tìm sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Lưu dữ liệu vào file");
            System.out.println("6. Xuất bảng ra file văn bản");
            System.out.println("0. Thoát chương trình");
            System.out.println();
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    product = new Product();
                    System.out.print("Nhập ID sản phẩm: ");
                    ID = scanner.nextInt();
                    scanner.nextLine();
                    if (productManager.searchByID(ID) != null) {
                        System.out.println("Sản phẩm đã tồn tại. Vui lòng chọn ID khác!");
                        break;
                    }
                    updateData(product);
                    productManager.add(ID,product);
                    break;
                case 2:
                    System.out.println("1. Tìm sản phẩm theo tên");
                    System.out.println("2. Tìm sản phẩm theo nhãn hiệu");
                    System.out.println("3. Tìm sản phẩm theo khoảng giá");
                    System.out.println("4. Tìm sản phẩm theo ID");
                    System.out.print("Nhập lựa chọn: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên sản phẩm: ");
                            input = scanner.nextLine();
                            HashMap<Integer,Product> subList = productManager.searchByName(input);
                            System.out.println("Kết quả tìm kiếm: ");
                            displayList(subList);
                            break;
                        case 2:
                            System.out.println("Nhập hãng sản phẩm: ");
                            input = scanner.nextLine();
                            subList = productManager.searchByBrand(input);
                            System.out.println("Kết quả tìm kiếm: ");
                            displayList(subList);
                            break;
                        case 3:
                            System.out.println("Nhập khoảng dưới của giá: ");
                            int low = scanner.nextInt();
                            System.out.println("Nhập khoảng trên của giá: ");
                            int high = scanner.nextInt();
                            subList = productManager.searchByPrice(low,high);
                            displayList(subList);
                            break;
                        case 4:
                            System.out.print("Nhập ID sản phẩm: ");
                            ID = scanner.nextInt();
                            scanner.nextLine();
                            product = productManager.searchByID(ID);
                            System.out.println("Kết quả tìm kiếm: ");
                            System.out.println(product);
                            System.out.print("\nXuất dữ liệu ra file văn bản ? (yes/no): ");
                            confirm = scanner.next().toLowerCase().charAt(0);
                            if (confirm == 'y') {
                                String path = "src/product/storage/" + product.getName() + ".txt";
                                try {
                                    serializator.exportToText(path, product);
                                } catch (IOException ex) {
                                    System.out.println("Có lỗi xảy ra: " + ex.getMessage());
                                } finally {
                                    System.out.println("Hoàn thành!");
                                }
                            }
                            break;
                        default:
                            System.out.println("Lựa chọn không phù hợp!");
                    }
                    break;
                case 3:
                    System.out.print("Nhập ID sản phẩm: ");
                    ID = scanner.nextInt();
                    scanner.nextLine();
                    product = productManager.searchByID(ID);
                    updateData(product);
                    break;
                case 4:
                    System.out.print("Nhập ID sản phẩm: ");
                    ID = scanner.nextInt();
                    scanner.nextLine();
                    productManager.remove(ID);
                    break;
                case 5:
                    System.out.println("1. Lưu thành file mới");
                    System.out.println("2. Save lên file đã có sẵn");
                    System.out.print("Nhập lựa chọn: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("Nhập tên file: ");
                            String path = "src/product/storage/" + scanner.nextLine() + ".dat";
                            File file = new File(path);
                            try {
                                serializator.writeToFile(path);
                            } catch (FileAlreadyExistsException e) {
                                System.out.print("File đã tồn tại. Lưu đè ? (yes/no): ");
                                confirm = scanner.next().toLowerCase().charAt(0);
                                if (confirm == 'y') {
                                    file.delete();
                                    try {
                                        serializator.writeToFile(path);
                                    } catch (IOException ex) {
                                        System.out.println("Có lỗi xảy ra: " + ex.getMessage());
                                    }
                                }
                            } catch (IOException ex) {
                                System.out.println("Có lỗi xảy ra: " + ex.getMessage());
                            } finally {
                                System.out.println("Hoàn thành!");
                            }
                            break;
                        case 2:
                            try {
                                serializator.writeToFile();
                            } catch (IOException ex) {
                                System.out.println("Có lỗi xảy ra: " + ex.getMessage());
                            } finally {
                                System.out.println("Hoàn thành!");
                            }
                            break;
                    }
                    break;
                case 6:
                    System.out.print("Nhập tên file: ");
                    String path = "src/product/storage/" + scanner.nextLine() + ".txt";
                    File file = new File(path);
                    try {
                        serializator.exportToText(path,productManager);
                    } catch (FileAlreadyExistsException e) {
                        System.out.print("File đã tồn tại. Lưu đè ? (yes/no): ");
                        confirm = scanner.next().toLowerCase().charAt(0);
                        if (confirm == 'y') {
                            file.delete();
                            try {
                                serializator.exportToText(path,productManager);
                            } catch (IOException ex) {
                                System.out.println("Có lỗi xảy ra: " + ex.getMessage());
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Có lỗi xảy ra: " + ex.getMessage());
                    } finally {
                        System.out.println("Hoàn thành!");
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    isNextOption = false;
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp");
            }
        }
    }

    private static void updateData(Product product) {
        System.out.print("Nhập tên sản phẩm: ");
        product.setName(scanner.nextLine());
        System.out.print("Nhập hãng của sản phẩm: ");
        product.setBrand(scanner.nextLine());
        System.out.print("Nhập giá của sản phẩm: ");
        product.setPrice(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Nhập barcode của sản phẩm: ");
        product.setBarCode(scanner.nextLine());
        System.out.print("Nhập mô tả: ");
        product.setDescription(scanner.nextLine());
    }

    private static void displayList(HashMap<Integer, Product> list) {
        String format = "|%3s |%21s |%12s |%10d |%21s |%13s |\n";
        StringBuilder data = new StringBuilder();
        StringBuilder header = new StringBuilder();
        StringBuilder footer = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Set<Integer> keySet = list.keySet();
        header.append("\n+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        header.append("|                                  Product Manager v1.0                                     |\n");
        header.append("+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        header.append("| ID |         Name         |     Brand   |   Price   |        Bar Code      |  Description |\n");
        header.append("+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        for (Integer key : keySet) {
            data.append(String.format(format
                    , key
                    , list.get(key).getName()
                    , list.get(key).getBrand()
                    , list.get(key).getPrice()
                    , list.get(key).getBarCode()
                    , list.get(key).getDescription()));
        }
        footer.append("+----+----------------------+-------------+-----------+----------------------+--------------+\n");
        footer.append("Tổng số: ").append(keySet.size()).append(" sản phẩm ").append("\n");
        result.append(header).append(data).append(footer);
        System.out.println(result);
    }
}
