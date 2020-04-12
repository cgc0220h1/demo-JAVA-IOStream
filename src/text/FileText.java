package text;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileText {
    public String readFile(String pathSource) throws IOException {
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

    public void writeToFile(String data, String pathDest) throws IOException {
        File fileToWrite = new File(pathDest);
        if (fileToWrite.exists()) {
            throw new FileAlreadyExistsException(pathDest);
        }
        FileWriter fileWriter = new FileWriter(fileToWrite);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    public void copyToFile(String pathSource, String pathDest) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathSource));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathDest));
        String lines;
        while ((lines = bufferedReader.readLine()) != null) {
            bufferedWriter.write(lines);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    public int countCharacter(String data) {
        int count = 0;
        Pattern pattern = Pattern.compile("[^\\s]");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public String[][] readCSV(String path) throws IOException {
        File csv = new File(path);
        Scanner scanLine = new Scanner(csv);
        Scanner countLine = new Scanner(csv);
        String data;
        int count = 0;
        while (countLine.hasNextLine()) {
            count++;
            countLine.nextLine();
        }
        String[][] result = new String[count][];
        count = 0;
        while (scanLine.hasNext()) {
            data = scanLine.nextLine();
            String[] strings = data.split(",");
            for (int index = 0; index<strings.length;index++) {
                strings[index] = strings[index].replaceAll("\"","");
            }
            result[count] = strings;
            count++;
        }
        return result;
    }

    public int countCharsUseFileReader(String path) throws IOException {
        int count = 0;
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String data;
        while ((data = bufferedReader.readLine()) != null) {
            String[] strings = data.split(" ");
            for (String string : strings) {
                count += string.length();
            }
        }
        return count;
    }

    public int countCharsUseScanner(String path) throws IOException {
        int count = 0;
        Scanner scanner = new Scanner(new File(path));
        String data;
        while (scanner.hasNext()) {
            data = scanner.nextLine();
            String[] strings = data.split(" ");
            for (String string : strings) {
                count += string.length();
            }
        }
        return count;
    }

    public void copyToFileUseMemory(String pathSource, String pathDest) throws IOException {
        String data = readFile(pathSource);
        writeToFile(data,pathDest);
    }
}