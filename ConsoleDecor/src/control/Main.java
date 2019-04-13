package control;

import fileIo.FileIO;
import sout.SoutLog;
import java.util.List;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private final String BLOCK_TEXT = "headBlock(int,char,String)";
    private final String HEAD_TEXT = "headLine(int,char,String)";
    private final String TITLE_TEXT = "titleLine(int,char,String)";
    private final String FOOTER_TEXT = "footerLine(int,char,String)";

    private final SoutLog SOUTLOG_EXAMPLE = new SoutLog();

    private final String INPUT_URL = "src/datas/example.txt";
    private final String OUTPUT_URL = "src/datas/OUTPUT.txt";

    private final FileIO FILEIO_EXAMPLE = new FileIO(INPUT_URL, OUTPUT_URL);

    private static List<String> dataList;

    private void fileIOExample() {
        dataList = FILEIO_EXAMPLE.inputFile();
        FILEIO_EXAMPLE.deleteFile();
        dataList.forEach((l) -> {
            FILEIO_EXAMPLE.appendFile(l);
        });
    }

    private void soutlogExammple() {
        SOUTLOG_EXAMPLE.soutln(SOUTLOG_EXAMPLE.headBlock(BLOCK_TEXT));
        SOUTLOG_EXAMPLE.soutln(SOUTLOG_EXAMPLE.headLine(HEAD_TEXT));
        SOUTLOG_EXAMPLE.soutln(SOUTLOG_EXAMPLE.titleLine(TITLE_TEXT));
        SOUTLOG_EXAMPLE.soutln(SOUTLOG_EXAMPLE.footerLine(FOOTER_TEXT));
    }

    private void start() {
        soutlogExammple();
        fileIOExample();
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
