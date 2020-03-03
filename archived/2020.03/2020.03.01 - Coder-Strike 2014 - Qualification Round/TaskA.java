package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] s = in.nextLine().toCharArray();
        boolean OK = true;
        if (s.length < 5) {
            OK = false;
        }
        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        for (int i = 0; i < s.length; ++i) {
            if (Character.isUpperCase(s[i])) upper = true;
            if (Character.isDigit(s[i])) digit = true;
            if (Character.isLowerCase(s[i])) lower = true;
        }
        OK = OK & upper & digit & lower;
        out.println(OK ? "Correct" : "Too weak");
    }
}
