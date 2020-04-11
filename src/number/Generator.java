package number;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Random;

public class Generator {
    public Generator() {
    }

    public void randomNumber(String filePath, int amount, int range, boolean append) throws IOException {
        File text = new File(filePath);
        if (text.exists()) {
            throw new FileAlreadyExistsException(filePath);
        }
        text.createNewFile();
        FileWriter fileWriter = new FileWriter(text,append);
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
