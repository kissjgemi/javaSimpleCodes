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

    private final String INPUT_URL;
    private final String OUTPUT_URL;

    private final String MODE_R = "r";
    private final String MODE_RW = "rw";

    private final String CHAR_SET = "UTF-8";

    private RandomAccessFile f;

    public FileIO(String inputFile, String outputFile) {
        this.INPUT_URL = inputFile;
        this.OUTPUT_URL = outputFile;
    }

    public void deleteFile() {
        File file = new File(OUTPUT_URL);
        if (file.exists()) {
            file.delete();
        }
    }

    public List<String> inputFile() {
        List<String> inputList = new ArrayList<>();
        try {
            f = new RandomAccessFile(INPUT_URL, MODE_R);
            String sor;
            while ((sor = f.readLine()) != null) {
                inputList.add(sor);
            }
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        printLine(INPUT_URL + "... beolvasva\n");
        return inputList;
    }

    public void appendFile(String str) {
        File file = new File(OUTPUT_URL);
        long fileLength = file.length();
        try {
            f = new RandomAccessFile(OUTPUT_URL, MODE_RW);
            f.seek(fileLength);
            f.write((str + "\n").getBytes());
            f.close();
        } catch (IOException e) {
            printLine("Fájl írási hiba: : " + e.getMessage());
            System.exit(-1);
        }
    }

    public void printLine(String str) {
        appendFile(str);
        sout.SoutLog.soutln(str);
    }
}
