import files.FileByte;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileByteTest {
    private FileByte fileByte = new FileByte();

    @DisplayName("Copy file dung lượng nhỏ bằng gói java.io")
    @Test
    void copyFilesIO() {
        String pathSource = "test/filesCopy/small.dat";
        String pathDest = "test/filesCopy/small1.dat";
        try {
            fileByte.copyFilesIO(pathSource, pathDest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @DisplayName("Copy file dung lượng nhỏ bằng gói java.nio")
    @Test
    void copyFilesNIO() {
        String pathSource = "test/filesCopy/small.dat";
        String pathDest = "test/filesCopy/small1.dat";
        try {
            fileByte.copyFilesNIO(pathSource, pathDest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @DisplayName("Copy file dung lượng lớn bằng gói java.io")
    @Test
    void copyFilesIOLarge() {
        String pathSource = "test/filesCopy/large.dat";
        String pathDest = "test/filesCopy/large1.dat";
        try {
            fileByte.copyFilesIO(pathSource, pathDest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @DisplayName("Copy file dung lượng lớn bằng gói java.nio")
    @Test
    void copyFilesNIOLarge() {
        String pathSource = "test/filesCopy/large.dat";
        String pathDest = "test/filesCopy/large1.dat";
        try {
            fileByte.copyFilesNIO(pathSource, pathDest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}