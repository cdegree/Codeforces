package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long mod = 1000000007;
        long res = 1;
        long e = 1;
        for (int i = 1; i <= n; ++i) {
            res = res * e % mod;
            e++;
        }
        e = 1;
        for (int i = 1; i <= n - 1; ++i) {
            e = e * 2 % mod;
        }
        res = (res - e + mod) % mod;
        out.println(res);
    }
}
