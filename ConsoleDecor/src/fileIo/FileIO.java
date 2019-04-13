package fileIo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class FileIO {

    private String inputUrl;
    private String outputUrl;

    private final String MODE_R = "r";
    private final String MODE_RW = "rw";

    private RandomAccessFile f;

    public FileIO(String inputFile, String outputFile) {
        this.inputUrl = inputFile;
        this.outputUrl = outputFile;
    }

    public void deleteFile() {
        File file = new File(outputUrl);
        if (file.exists()) {
            file.delete();
        }
    }

    public List<String> inputFile() {
        List<String> inputList = new ArrayList<>();
        try {
            f = new RandomAccessFile(inputUrl, MODE_R);
            String sor;
            while ((sor = f.readLine()) != null) {
                inputList.add(sor);
            }
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        System.out.println(inputUrl + "... beolvasva\n");
        return inputList;
    }

    /*
    str = str.replace("ő", String.valueOf((char) 245));
    str = str.replace("Ő", String.valueOf((char) 213));
    str = str.replace("ű", String.valueOf((char) 251));
    str = str.replace("Ű", String.valueOf((char) 219));
     */
    public void appendFile(String str) {
        File file = new File(outputUrl);
        str = str.replace('ő', 'ö');
        str = str.replace('Ő', 'Ö');
        str = str.replace('ű', 'ü');
        str = str.replace('Ű', 'Ü');
        long fileLength = file.length();
        try {
            f = new RandomAccessFile(outputUrl, MODE_RW);
            f.seek(fileLength);
            f.writeBytes(str + "\n");
            f.close();
        } catch (IOException e) {
            System.err.println("Fájl íráási hiba: : " + e.getMessage());
            System.exit(-1);
        }
    }
}
