package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int q = in.nextInt();
        while (q-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            int len = r - l + 1;
            long sum = 0;
            if (l % 2 == 0) {
                sum = -len / 2;
            } else {
                sum = len / 2;
            }
            if (len % 2 == 1) {
                if (r % 2 == 1) {
                    sum += -r;
                } else {
                    sum += r;
                }
            }
            out.println(sum);
        }
    }
}
