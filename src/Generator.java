import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Random;

public class Generator {
    public Generator() {
    }

    public void randomNumber(String filePath, int amount, int range) throws IOException {
        File text = new File(filePath);
        if (text.exists()) {
            throw new FileAlreadyExistsException(filePath);
        }

        FileWriter fileWriter = new FileWriter(text);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Random random = new Random();

        for (int index = 0; index < amount; index++) {
            int numberToWrite = random.nextInt(range);
            bufferedWriter.write(String.valueOf(numberToWrite));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
