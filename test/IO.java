import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class IO {
    public static void main(String[] args) {
        CopyFile copyFile = new CopyFile();
        String source = "text/HelloWorld.txt";
        String dest = "resources/HelloWorld.txt";
        try {
            copyFile.copy(source, dest);
        } catch (FileNotFoundException ex) {
            System.out.println("Source File not exist!");
        } catch (FileAlreadyExistsException ex) {
            System.out.print("Destination File exist! Overwrite? (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            char choice = scanner.next().toLowerCase().charAt(0);
            if (choice == 'y') {
                File file = new File(dest);
                if (file.delete()) {
                    try {
                        copyFile.copy(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("File deleted!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
