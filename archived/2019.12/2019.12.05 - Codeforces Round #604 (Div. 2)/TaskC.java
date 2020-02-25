package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int g = 0;
            int s = 0;
            int b = 0;
            boolean OK = true;
            int n = in.nextInt();
            int[] p = in.readIntArray(n);


            int mx = p[0];
            int cntG = 0;
            int k = 0;
            while (k < n && p[k] == p[0]) {
                ++k;
            }
            g = k;
            cntG = p[0];
            int j = k + 1;
            while (j < n && j - k <= g) {
                ++j;
            }
            s = j - k;
            if (s <= g) {
                OK = false;
                out.println("0 0 0");
            } else {
                while (j < n && p[j] == p[j - 1]) {
                    ++j;
                }
                s = j - k;
                if (s <= g) {
                    OK = false;
                    out.println("0 0 0");
                } else {
                    int q = j + 1;
                    while (q < n && q - j <= g) {
                        ++q;
                    }
                    while (q < n && p[q] == p[q - 1]) {
                        ++q;
                    }
                    b = q - j;
                    if (g + s + b > n / 2) {
                        OK = false;
                        out.println("0 0 0");
                    } else {
                        for (int i = q; i < n; ++i) {
                            if (p[i] != p[i - 1]) {
                                int tb = i - j;
                                if (g + s + tb <= n / 2) {
                                    b = tb;
                                }
                            }
                        }
                        out.println(g + " " + s + " " + b);
                    }
                }
            }
        }
    }
}
