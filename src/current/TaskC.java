package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            char[] s = in.next().toCharArray();
            char[] target = new char[n];
            for (int i = 0; i < (k - 1) * 2; i += 2) {
                target[i] = '(';
                target[i + 1] = ')';
            }
            for (int i = 2 * k - 2, j = n - 1; i < j; ++i, --j) {
                target[i] = '(';
                target[j] = ')';
            }
            int len = 0;
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; ++i) {
                if (s[i] != target[i]) {
                    int idx = i;
                    for (int j = i + 1; j < n; ++j) {
                        if (s[j] == target[i]) {
                            idx = j;
                            break;
                        }
                    }
                    a[len] = i + 1;
                    b[len] = idx + 1;
                    len++;
                    int ti = i;
                    int tj = idx;
                    for (int p = 0; p < (idx - i + 1) / 2; ++p) {
                        char t = s[ti];
                        s[ti] = s[tj];
                        s[tj] = t;
                        ++ti;
                        --tj;
                    }
                }
            }
            out.println(len);
            for (int i = 0; i < len; ++i) {
                out.println(a[i] + " " + b[i]);
            }
        }
    }
}
