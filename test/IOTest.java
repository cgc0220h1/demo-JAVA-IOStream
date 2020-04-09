import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {
    @DisplayName("Get Stream From Website")
    @Test
    void getStreamFromWebsite() {
        try {
            URL url = new URL("http://example.com/");
            URLConnection connection = url.openConnection();

            InputStream webStream = connection.getInputStream();
            InputStreamReader webReader = new InputStreamReader(webStream);
            BufferedReader bufferedReader = new BufferedReader(webReader);

            String line;
            while (bufferedReader.readLine() != null) {
                line = bufferedReader.readLine();
                System.out.println(line);
            }
            webReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Write To File")
    @Test
    void writeToFile() {
        File dir = new File("text");
        boolean result = dir.mkdirs();
        System.out.println(result);
        File text = new File("text/HelloWorld.txt");
        try {
            FileWriter writer = new FileWriter(text);
            writer.write("Hello World");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Read From File")
    @Test
    void readFromFile() {
        try {
            FileReader reader = new FileReader("text/HelloWorld.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String data = buffer.readLine();
            System.out.println(data);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Read directory content")
    @Test
    void readDirectoryContent() {
        File dir = new File("E:/Games");
        String[] dirContents = dir.list();

        assert dirContents != null;
        for (String string : dirContents) {
            System.out.println(string);
        }
        System.out.println(dir.getAbsolutePath());
        System.out.println(dir.getPath());
    }

    @DisplayName("Write Object to File")
    @Test
    void writeObjectToFile() {
        Object anhNam = new Object() {
            private String name = "Nam";
            private int age = 25;
            private LocalDate birth = LocalDate.of(1992, 4, 20);
            private String address = "Planet Pixel";

            @Override
            public String toString() {
                return  "Tên là: " + name + "\n" +
                        "Tuổi: " + age + "\n" +
                        "Ngày sinh: " + birth + "\n" +
                        "Địa chỉ: " + address + "\n";
            }
        };

        try {
            File nam = new File("text/nam.dat");
            PrintWriter printWriter = new PrintWriter(nam);
            printWriter.println("Đây là bản sao của Đức: \n" + anhNam);
            printWriter.close();
            File nam1 = new File("text/duc.dat");
            FileWriter fileWriter = new FileWriter(nam);
            fileWriter.write("Đây là bản sao của bản sao của Đức: \n" + anhNam.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}