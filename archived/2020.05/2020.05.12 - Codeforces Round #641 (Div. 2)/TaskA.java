package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int d = 2;
            while (n % d != 0) ++d;
            int res = n;
            res += d;
            res += (k - 1)* 2;
            out.println(res);
        }
    }
}
