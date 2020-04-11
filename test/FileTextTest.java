import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import text.FileText;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

class FileTextTest {

    @Test
    void testReadFromEmptyFile() throws IOException {
        File emptyFile = new File("text/EmptyFile.txt");
        FileText fileText = new FileText();
        try {
            emptyFile.createNewFile();
        } catch (FileAlreadyExistsException ex) {
            emptyFile.delete();
            emptyFile.createNewFile();
        } finally {
            String data = fileText.readFile(emptyFile.getAbsolutePath());
            assertEquals("",data);
        }
    }

    @Test
    void testReadFromFile() {
        FileText fileText = new FileText();
        String data = "";
        try {
            data = fileText.readFile("text/HelloWorld.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("Hello World\n", data);
    }

    @Test
    void writeToFile() {
        FileText fileText = new FileText();
        String data = "Hello World\nKonichiwa\nXinchao\nA hi hi Do ngoc";
        try {
            fileText.writeToFile(data, "text/Demo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File text = new File("text/Demo");
        assertTrue(text.exists());
    }

    @DisplayName("Đếm số ký tự dùng regex")
    @Test
    void testCountCharacter() {
        String data = "Hello, cac 'ban'.\nXin chao 123";
        FileText fileText = new FileText();
        int expectedCount = 25;
        assertEquals(expectedCount, fileText.countCharacter(data));
    }

    @DisplayName("Đếm số ký tự không dùng regex")
    @Test
    void testCountCharacterMethod2() {
        String path = "text/nam.dat";
        FileText fileText = new FileText();
        int expectedCount = 71;
        try {
            assertEquals(expectedCount, fileText.countCharsUseFileReader(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @DisplayName("Đếm số ký tự sử dụng Scanner")
    @Test
    void testCountCharacterMethod3() {
        String path = "text/nam.dat";
        FileText fileText = new FileText();
        int expectedCount = 71;
        try {
            assertEquals(expectedCount, fileText.countCharsUseScanner(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void readCSV() throws IOException {
        FileText fileText = new FileText();
        String[][] data = fileText.readCSV("text/country.csv");
        for (String[] row : data) {
            for(String string : row) {
                System.out.printf("| %12s |",string);
            }
            System.out.println();
        }
    }
}