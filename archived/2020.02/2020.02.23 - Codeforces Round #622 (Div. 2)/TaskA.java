package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int[] a = in.nextIntArray(3);
            int res = 0;
            Arrays.sort(a);
            for (int i = 0; i < 3; ++i) {
                if (a[i] > 0) {
                    a[i]--;
                    ++res;
                }
            }
            for (int i = 2; i >= 0; --i) {
                for (int j = i - 1; j >= 0; --j) {
                    if (a[i] > 0 && a[j] > 0) {
                        --a[i];
                        --a[j];
                        ++res;
                    }
                }
            }
            if (a[0] > 0 && a[1] > 0 && a[2] > 0) {
                ++res;
            }
            out.println(res);
        }
    }
}
