package files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileByte {
    private static final int BUFFER_SIZE = 8192; //8KB
    public void copyFilesIO (String pathSource, String pathDest) throws IOException {
        InputStream source = new BufferedInputStream(new FileInputStream(pathSource));
        OutputStream dest = new BufferedOutputStream(new FileOutputStream(pathDest));
        byte[] buffer = new byte[BUFFER_SIZE];
        while (source.read(buffer) != -1) {
            dest.write(buffer);
        }
    }

    public void copyFilesNIO (String pathSource, String pathDest) throws IOException {
        byte[] allBytes = Files.readAllBytes(Paths.get(pathSource));
        Files.write(Paths.get(pathDest),allBytes);
    }


}
