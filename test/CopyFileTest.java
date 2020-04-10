import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CopyFileTest {

    @Test
    void read() {
        CopyFile copyFile = new CopyFile();
        String data = "";
        try {
            data = copyFile.read("text/HelloWorld.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(data);
    }

    @Test
    void write() {
        CopyFile copyFile = new CopyFile();
        String data = "Hello World\nKonichiwa\nXinchao\nA hi hi Do ngoc";
        try {
            copyFile.write(data, "text/Demo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}