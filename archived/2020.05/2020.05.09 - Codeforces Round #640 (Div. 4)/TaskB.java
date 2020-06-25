package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            if (k > n) {
                out.println("NO");
            } else {
                if (n % 2 == 1 && k % 2 == 0) {
                    out.println("NO");
                } else if (n % 2 == 0 && k % 2 == 1) {
                    if (n < 2 * k) {
                        out.println("NO");
                    } else {
                        out.println("YES");
                        for (int i = 1; i < k; ++i) {
                            out.print(2 + " ");
                        }
                        out.println(n - 2 * (k - 1));
                    }
                } else {
                    out.println("YES");
                    // odd, odd                  even even
                    for (int i = 1; i < k; ++i) {
                        out.print(1 + " ");
                    }
                    out.println(n - (k - 1));
                }
            }
        }
    }
}
