package control;

import fileIo.FileIO;
import java.util.ArrayList;
import java.util.Collections;
import sout.SoutLog;
import keyboardIo.KeyBoardIO;
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

    private final String STRING_IN_LABEL = "Adatbevitel: ";
    private final String INT_IN_LABEL = "Egész szám bevitel: ";
    private final String DOUBLE_IN_LABEL = "Szám bevitel (tört is lehet): ";
    private final String YES_NO_LABEL = "Folytatás (i/n)";
    private final String YES_NO_TEXT = "in";
    private final String CHAR_SET_SCANNER = "Cp1250";
    private final KeyBoardIO KEYBOARD_EXAMPLE = new KeyBoardIO(CHAR_SET_SCANNER);

    private final String NO_ANSWER = "NA";
    private final String STRING_SUMofMONEY = "Pénz összesen: %d Ft";
    private final String STRING_MAXofRANGE = "Legnagyobb távolság: %.1f km";
    private final String STRING_MINofFUEL = "Legkisebb tankolás: %.2f liter";
    private final String REGEX = "\\s*\t\\s*";
    private static int formatCol1 = 0, formatCol2 = 0, formatCol3 = 0;
    private static int sumOFmoney = 0;
    private static double maxOFrange = 0;
    private static double minOFfuel = 1000;

    private static final boolean TOSTART = true;
    private static final boolean TOEND = false;
    private static final boolean ASCENDING = true;
    private static final boolean DESCENDING = false;

    private class DB_Row {

        public final int COL1_YEAR;
        public final double COL2_FUELat100;
        public final String COL3_FUELSTATION;

        private double countFuelAt100(double range, double quantity) {
            double fAt100 = 100 * quantity / range;
            return Math.round(fAt100 * 100) / 100.0;
        }

        public DB_Row(String line) {
            line = line.replace(',', '.');
            String[] datas = line.split(REGEX);
            COL1_YEAR = Integer.valueOf(datas[0]);
            COL2_FUELat100 = countFuelAt100(Double.valueOf(datas[1]),
                    Double.valueOf(datas[2]));
            COL3_FUELSTATION = datas[4];

            sumOFmoney += Integer.valueOf(datas[3]);

            maxOFrange = (maxOFrange > Double.valueOf(datas[1]))
                    ? maxOFrange : Double.valueOf(datas[1]);

            minOFfuel = (minOFfuel < Double.valueOf(datas[2]))
                    ? minOFfuel : Double.valueOf(datas[2]);

            int dummy = datas[0].length();
            formatCol1 = (formatCol1 > dummy) ? formatCol1 : dummy;
            dummy = (COL2_FUELat100 >= 10) ? 5 : 4;
            formatCol2 = (formatCol2 > dummy) ? formatCol2 : dummy;
            dummy = datas[4].length();
            formatCol3 = (formatCol3 > dummy) ? formatCol3 : dummy;
        }

        @Override
        public String toString() {
            String format = "| %" + formatCol1 + "d"
                    + " | %" + formatCol2 + ".2f"
                    + " | %" + formatCol3 + "s";
            return String.format(format, COL1_YEAR, COL2_FUELat100, COL3_FUELSTATION);
        }
    }

    private static final List<DB_Row> ROWS = new ArrayList<>();

    public Main() {
    }

    private void fileIOExample() {
        dataList = FILEIO_EXAMPLE.inputFile();
    }

    private void keyBoardIOexample() {
        do {
            KEYBOARD_EXAMPLE.getString(STRING_IN_LABEL, FILEIO_EXAMPLE);
            KEYBOARD_EXAMPLE.getInt(INT_IN_LABEL, FILEIO_EXAMPLE);
            KEYBOARD_EXAMPLE.getDouble(DOUBLE_IN_LABEL, FILEIO_EXAMPLE);
        } while (!KEYBOARD_EXAMPLE.getChar(YES_NO_LABEL,
                FILEIO_EXAMPLE, YES_NO_TEXT));
    }

    private void soutlogExammple() {
        FILEIO_EXAMPLE.printLine(SOUTLOG_EXAMPLE.headBlock(BLOCK_TEXT));
        FILEIO_EXAMPLE.printLine("");
        FILEIO_EXAMPLE.printLine(SOUTLOG_EXAMPLE.headLine(HEAD_TEXT));
        FILEIO_EXAMPLE.printLine("");
        FILEIO_EXAMPLE.printLine(SOUTLOG_EXAMPLE.titleLine(TITLE_TEXT));
        FILEIO_EXAMPLE.printLine("");
        FILEIO_EXAMPLE.printLine(SOUTLOG_EXAMPLE.footerLine(FOOTER_TEXT));
        FILEIO_EXAMPLE.printLine("");
    }

    private void sortedDataOutput() {
        dataList.forEach((s) -> {
            ROWS.add(new DB_Row(s));
        });

        FILEIO_EXAMPLE.printLine(String.format(STRING_SUMofMONEY, sumOFmoney));
        FILEIO_EXAMPLE.printLine(String.format(STRING_MAXofRANGE, maxOFrange));
        FILEIO_EXAMPLE.printLine(String.format(STRING_MINofFUEL, minOFfuel));
        FILEIO_EXAMPLE.printLine("");

        boolean howTo = ASCENDING;
        boolean where = TOSTART;

        Collections.sort(ROWS, (o1, o2) -> {
            int o1col1 = o1.COL1_YEAR;
            int o2col1 = o2.COL1_YEAR;
            int o1col2 = (int) (100 * o1.COL2_FUELat100);
            int o2col2 = (int) (100 * o2.COL2_FUELat100);
            String o1col3 = o1.COL3_FUELSTATION;
            String o2col3 = o2.COL3_FUELSTATION;

            if (o1col3.equals(o2col3)) {
                if ((String.valueOf(o1col1)).equals(String.valueOf(o2col1))) {
                    return howTo ? o1col2 - o2col2 : o2col2 - o1col2;
                } else {
                    return howTo ? o1col1 - o2col1 : o2col1 - o1col1;
                }
            } else {
                if (o1col3.equals(NO_ANSWER) || o2col3.equals(NO_ANSWER)) {
                    return where ? o2col3.charAt(1) - o1col3.charAt(1)
                            : o1col3.charAt(1) - o2col3.charAt(1);
                } else if (o1col3.charAt(0) == o2col3.charAt(0)) {
                    return howTo ? Integer.valueOf(o1col3.substring(1))
                            - Integer.valueOf(o2col3.substring(1))
                            : Integer.valueOf(o2col3.substring(1))
                            - Integer.valueOf(o1col3.substring(1));
                } else {
                    return howTo ? o1col3.compareTo(o2col3)
                            : o2col3.compareTo(o1col3);
                }
            }
        });

        ROWS.forEach((r) -> {
            FILEIO_EXAMPLE.printLine(r.toString());
        });
        FILEIO_EXAMPLE.printLine("");
    }

    private void start() {
        FILEIO_EXAMPLE.deleteFile();
        soutlogExammple();
        keyBoardIOexample();
        fileIOExample();
        sortedDataOutput();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
