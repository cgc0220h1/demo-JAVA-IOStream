import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IO {
    public static void main(String[] args) {
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
}
