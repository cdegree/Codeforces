package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        String os = s;
        int n = s.length();
        while (true) {
            int len = s.length();
            s = s.replace("//", "/");
            if (s.length() == len) {
                break;
            }
        }
        if (s.length() > 1 && s.charAt(s.length() - 1) == '/') {
            s = s.substring(0, s.length() - 1);
        }
        out.println(s);
    }
}
