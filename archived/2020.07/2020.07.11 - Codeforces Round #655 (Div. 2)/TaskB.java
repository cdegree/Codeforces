package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            long n = in.nextLong();
            long a = 0;
            long b = 0;
            for (long i = 2; i * i <= n; ++i) {
                if (n % i == 0) {
                    a = n / i;
                    b = n - n / i;
                    break;
                }
            }
            if (a == 0) {
                a = 1;
                b = n - 1;
            }
            out.println(a+" "+b);
        }
    }
}
