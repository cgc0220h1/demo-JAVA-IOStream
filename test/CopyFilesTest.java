import java.io.*;

public class CopyFilesTest {
    public static void main(String[] args) {
        String inputFile = "test/project.rar";
        String outputFile = "test/project1.rar";

        try (
                InputStream inputStream = new FileInputStream(inputFile);
                OutputStream outputStream = new FileOutputStream(outputFile);
        ) {
            long fileSize = new File(inputFile).length();
            System.out.println(fileSize);
            byte[] buffer = new byte[4096];
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
