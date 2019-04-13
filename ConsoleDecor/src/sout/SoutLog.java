package sout;

/**
 *
 * @author KissJGabi
 */
public class SoutLog {

    private static int len = 60;

    private static final char HEAD_SIGN = '-';
    private static final char FOOTER_SIGN = '-';
    private static final char TITLE_SIGN = '=';
    private static final char BLOCK_SIGN = '*';

    public static int getLen() {
        return len;
    }

    public static void setLen(int len) {
        SoutLog.len = len;
    }

    public SoutLog() {

    }

    private String patternLine(int nr, char ch) {
        String str = "";
        for (int ii = 0; ii < nr; ii++) {
            str += ch;
        }
        return str;
    }

    public String titleLine(String s) {
        String str = patternLine(len, TITLE_SIGN);
        s = "  " + s + "  ";
        int first = (len - s.length()) / 2;
        int last = s.length() + first;
        str = str.substring(0, first) + s + str.substring(last);
        return str;
    }

    public String headLine(String s) {
        String str = patternLine(len, HEAD_SIGN);
        s = HEAD_SIGN + "| " + s + " |";
        int last = s.length();
        str = s + str.substring(last);
        return str;
    }

    public String footerLine(String s) {
        String str = patternLine(len, FOOTER_SIGN);
        s = "( " + s + " )" + FOOTER_SIGN;
        int first = len - s.length();
        str = str.substring(0, first) + s;
        return str;
    }

    public String headBlock(String s) {
        String[] line = s.split("\n");
        String str = patternLine(len, BLOCK_SIGN) + "\n";
        String empty = BLOCK_SIGN + patternLine(len - 2, ' ') + BLOCK_SIGN;
        str += empty + "\n";
        int first, last;
        String newLine;
        for (String l : line) {
            first = (len - l.length()) / 2;
            last = l.length() + first;
            newLine = empty.substring(0, first) + l + empty.substring(last);
            str += newLine + "\n";
        }
        str += empty + "\n";
        str += patternLine(len, BLOCK_SIGN);
        return str;
    }

    public void sout(String str) {
        System.out.print(str);
    }

    public void soutln(String str) {
        System.out.println(str);
    }
}
