package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            if (n == 1) {
                out.println(0);
                continue;
            }
            int cnt2 = 0;
            int cnt3 = 0;
            int res;
            while (n % 2 == 0) {
                n /= 2;
                cnt2++;
            }
            while (n % 3 == 0) {
                n /= 3;
                cnt3++;
            }
            if (n > 1 || cnt2 > cnt3) {
                res = -1;
            } else {
                res = (cnt3 - cnt2) + cnt3;
            }
            out.println(res);
        }
    }
}
