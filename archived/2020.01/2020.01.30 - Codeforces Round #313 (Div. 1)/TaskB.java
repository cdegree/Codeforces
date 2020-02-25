package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    String ToEquivalent(String s) {
        if (s.length() % 2 == 1) {
            return s;
        }
        String s1 = ToEquivalent(s.substring(0, s.length() / 2));
        String s2 = ToEquivalent(s.substring(s.length() / 2));
        if (s1.compareTo(s2) < 0) {
            return s1 + s2;
        } else {
            return s2 + s1;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        out.print(ToEquivalent(s1).equals(ToEquivalent(s2)) ? "YES" : "NO");
    }
}
