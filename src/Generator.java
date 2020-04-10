import java.io.*;
import java.util.Random;

import static sun.java2d.cmm.ColorTransform.Out;

public class Generator {
    public Generator() {
    }

    public void randomNumber(String filePath, int amount) throws IOException {
        File text = new File(filePath);
        FileWriter fileWriter = new FileWriter(text);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Random random = new Random();

        for (int index = 0; index < amount; index++) {
            int numberToWrite = random.nextInt(10);
            bufferedWriter.write(String.valueOf(numberToWrite));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
