import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Generator generator = new Generator();
        Calculator calculator = new Calculator();
        int choice;
        boolean isExit = false;
        System.out.print("Nhập đường dẫn file: ");
        String path = scanner.nextLine();

        while (!isExit) {
            System.out.println("--------Menu--------");
            System.out.println("1. Tạo file số random");
            System.out.println("2. Tính tổng");
            System.out.println("0. Exit!");
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Nhập số lượng số random: ");
                        int number = scanner.nextInt();
                        System.out.print("Nhập khoảng random: ");
                        int range = scanner.nextInt();
                        generator.randomNumber(path, number, range);
                    } catch (FileAlreadyExistsException e) {
                        System.out.println("File đã tồn tại");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        int result = calculator.calculateFromFile(path);
                        System.out.println("Tổng bằng: " + result);
                    } catch (FileNotFoundException e) {
                        System.out.println("Không tìm thấy File");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Exit Program!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
