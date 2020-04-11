package number;

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
        System.out.print("Enter Path: ");
        String path = scanner.nextLine();

        while (!isExit) {
            System.out.println("--------Menu--------");
            System.out.println("1. Generate File contains random number");
            System.out.println("2. Calculate Total");
            System.out.println("0. Exit!");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter random numbers: ");
                    int number = scanner.nextInt();
                    System.out.print("Enter random range: ");
                    int range = scanner.nextInt();
                    try {
                        generator.randomNumber(path, number, range,false);
                    } catch (FileAlreadyExistsException e) {
                        System.out.print("File Exist! Continue to write ? (yes/no): ");
                        char confirm = scanner.next().toLowerCase().charAt(0);
                        if (confirm == 'y') {
                            try {
                                generator.randomNumber(path,number,range,true);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        int result = calculator.calculateFromFile(path);
                        System.out.println("Total: " + result);
                    } catch (FileNotFoundException e) {
                        System.out.println("File Not Found!");
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
