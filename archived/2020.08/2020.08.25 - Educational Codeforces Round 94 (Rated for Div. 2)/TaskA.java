package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            char[] w = new char[n];
            for (int i = 0; i < n; ++i) {
                w[i] = s[n - 1];
            }
            out.println(w);
        }
    }
}
