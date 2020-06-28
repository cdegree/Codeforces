package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            char[] s = in.nextLine().toCharArray();
            int zero = 0;
            int one = 0;
            for (char c : s) {
                if (c == '0') ++zero;
                if (c == '1') ++one;
            }
            out.println(((Math.min(one,zero)) % 2 == 0) ? "NET" : "DA");
        }
    }
}
