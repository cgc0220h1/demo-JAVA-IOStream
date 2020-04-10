import java.io.*;

public class Calculator {
    public Calculator() {
    }

    public int calculateFromFile(String pathName) throws IOException {
        int total = 0;
        FileReader fileReader = new FileReader(pathName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lines;
        while ((lines = bufferedReader.readLine()) != null) {
            int number = Integer.parseInt(lines);
            total += number;
        }
        bufferedReader.close();
        return total;
    }


}
