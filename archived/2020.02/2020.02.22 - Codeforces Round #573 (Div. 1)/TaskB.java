package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Arrays.sort(a);
        int last = -1;
        int equalCount = 0;
        boolean OK = true;
        for (int i = 0; i < n - 1; ++i) {
            if (a[i] == a[i + 1]) {
                if (last == a[i] || a[i] == 0 || last == a[i] - 1) {
                    OK = false;
                    break;
                }
                ++equalCount;
            }
            last = a[i];
        }
        if (equalCount >= 2) {
            OK = false;
        }
        if (OK) {
            long left = 0;
            for (int i = 0; i < n; ++i) {
                left += a[i] - i;
            }
            if (left <= 0 || left % 2 == 0) {
                OK = false;
            }
        }
        out.println(OK ? "sjfnb" : "cslnb");
    }
}
