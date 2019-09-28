package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextInt();
        long k = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        long mx = 0;
        long inf = 1L<<60;
        long mn = inf;

        int[] f = {-1, 1};

        for (int j = 0; j < 2; ++j) {
            long x = (1 + k + a * f[j]);
            if (x > k * n) {
                x -= k * n;
            }
            long y = 0;
            for (int i = 0; i < n; ++i) {
                for (int q = 0; q < 2; ++q) {
                    y = i * k + 1 + b * f[q];
//                    out.println(x + "  " + y);
                    if (y <= 0) {
                        y += k * n;
                    }
                    if (y > k * n) {
                        y -= k * n;
                    }
                    {
                        long dis = Math.abs(x - y);
                        long ans = 0;
                        if (dis > 0) {
                            ans = getx(dis, n * k);
                            if (ans < 0) {
                                out.println(x + "  " + y + " dis=" + dis + "ans=" + ans);
                            }
                            mn = Math.min(mn, ans);
                            mx = Math.max(mx, ans);
                        }
                        dis = n * k - dis;
                        if (dis > 0) {
                            ans = getx(dis, n * k);
//                            out.println(x + "  " + y + " dis=" + dis + " ans=" + ans);
                            mn = Math.min(mn, ans);
                            mx = Math.max(mx, ans);
                        }
                    }
                }
            }
        }
        out.println(mn + " " + mx);
    }

    long gcd(long x, long y) {
        if (x > y) {
            long t = x;
            x = y;
            y = t;
        }
        if (x == 0) {
            return y;
        }
        return gcd(y % x, x);
    }

    long getx(long dis, long n) {
        return 1L * n / gcd(dis, n);
    }
}
