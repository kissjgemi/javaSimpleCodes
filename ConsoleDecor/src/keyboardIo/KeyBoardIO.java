package keyboardIo;

import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class KeyBoardIO {

    private final String CHAR_SET;
    private final Scanner KB;

    public KeyBoardIO(String charSet) {
        this.CHAR_SET = charSet;
        KB = new Scanner(System.in, (this.CHAR_SET));
    }

    public String getString(String label, fileIo.FileIO io) {
        String result;
        io.printLine(label);
        do {
            System.out.print(" >");
            result = KB.nextLine();
            io.appendFile(" >" + result);
        } while (result.length() == 0);
        return result;
    }

    public boolean getChar(String label, fileIo.FileIO io, String in) {
        String input;
        do {
            input = getString(label, io);
        } while (!(in.contains(input) && input.length() == 1));
        return input.charAt(0) != in.charAt(0);
    }

    public int getInt(String label, fileIo.FileIO io) {
        boolean isInteger;
        int result;
        do {
            isInteger = true;
            result = 0;
            try {
                result = Integer.valueOf(getString(label, io));
            } catch (NumberFormatException e) {
                io.printLine("Egész számot kérek!");
                isInteger = false;
            }
        } while (!isInteger);
        return result;
    }

    public double getDouble(String label, fileIo.FileIO io) {
        boolean isNumber;
        double result;
        do {
            isNumber = true;
            result = 0;
            try {
                result = Double.valueOf(getString(label, io));
            } catch (NumberFormatException e) {
                io.printLine("Számot kérek!");
                isNumber = false;
            }
        } while (!isNumber);
        return result;
    }
}
