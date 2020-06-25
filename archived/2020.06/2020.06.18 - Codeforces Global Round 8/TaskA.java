package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            long a = in.nextInt();
            long b = in.nextInt();
            long n = in.nextInt();
            if (a > b) {
                long t = a;
                a = b;
                b = t;
            }
            int res = 0;
            while (b <= n) {
                long c = a + b;
                ++res;
                a = b;
                b = c;
            }
            out.println(res);
        }
    }
}
