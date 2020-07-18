package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int m = n / 2;
            while (true) {
                int larger = countLarger(a);
                int less = countLess(a);
                if (larger < m) {
                    for (int i = 0; i < n; ++i) {
                        if (i > 0 && Math.abs(a[i]) > Math.abs(a[i - 1]) && i - 1 < n && Math.abs(a[i]) > Math.abs(a[i + 1])) {

                        } else if (i > 0 && Math.abs(a[i]) > Math.abs(a[i - 1])) {
                            if (a[i] < 0) {
                                a[i] = -a[i];
                                ++larger;
                            }
                        } else if (i - 1 < n && Math.abs(a[i]) > Math.abs(a[i + 1])) {
                            if (a[i] > 0) {
                                a[i] = -a[i];
                                ++larger;
                            }
                        }
                        if (larger == m) {
                            break;
                        }
                    }
                } else if (less < m) {
                    for (int i = 0; i < n; ++i) {
                        if (i > 0 && Math.abs(a[i]) > Math.abs(a[i - 1]) && i - 1 < n && Math.abs(a[i]) > Math.abs(a[i + 1])) {

                        } else if (i > 0 && Math.abs(a[i]) > Math.abs(a[i - 1])) {
                            if (a[i] > 0) {
                                a[i] = -a[i];
                                ++less;
                            }
                        } else if (i - 1 < n && Math.abs(a[i]) > Math.abs(a[i + 1])) {
                            if (a[i] < 0) {
                                a[i] = -a[i];
                                ++less;
                            }
                        }
                        if (less == m) {
                            break;
                        }

                    }
                } else {
                    break;
                }
            }
            for(int x:a){
                out.print(x+" ");
            }
            out.println();
        }
    }


    int countLarger(int[] a) {
        int ret = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] - a[i - 1] >= 0) ++ret;
        }
        return ret;
    }

    int countLess(int[] a) {
        int ret = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] - a[i - 1] <= 0) ++ret;
        }
        return ret;
    }
}
