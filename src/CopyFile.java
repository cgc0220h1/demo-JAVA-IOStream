import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class CopyFile {
    public String read(String pathSource) throws IOException {
        File fileToRead = new File(pathSource);
        if (!fileToRead.exists()) {
            throw new FileNotFoundException();
        }
        FileReader fileReader = new FileReader(pathSource);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lines;
        StringBuilder data = new StringBuilder();
        while ((lines = bufferedReader.readLine()) != null) {
            data.append(lines).append("\n");
        }
        bufferedReader.close();
        return data.toString();
    }

    public void write(String data, String pathDest) throws IOException {
        File fileToWrite = new File(pathDest);
        if (fileToWrite.exists()) {
            throw new FileAlreadyExistsException(pathDest);
        }
        FileWriter fileWriter = new FileWriter(fileToWrite);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    public void copy(String pathSource, String pathDest) throws IOException {
        String data = read(pathSource);
        write(data,pathDest);
    }
}
