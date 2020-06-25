package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] c = in.nextIntArray(n);

            int p0 = 0;
            int p1 = n - 1;
            int a = 0;
            int b = 0;
            int cnt = 0;

            int last = 0;
            for (; p0 <= p1; cnt++) {
                if (cnt % 2 == 0) {
                    int eat = c[p0++];
                    while (eat <= last && p0 <= p1) {
                        eat += c[p0++];
                    }
                    a += eat;
                    last = eat;
                } else {
                    int eat = c[p1--];
                    while (eat <= last && p0 <= p1) {
                        eat += c[p1--];
                    }
                    b += eat;
                    last = eat;
                }
            }
            out.println(cnt + " " + a + " " + b);
        }
    }
}
