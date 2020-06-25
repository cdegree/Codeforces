package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[][] a = new long[n][n];
        for (int i = 0; i < n; ++i) {
            a[i] = in.nextLongArray(n);
        }
        if (n == 1) {
            out.println(1);
            return;
        }
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            boolean OK = true;
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 0) {
                    OK = false;
                    break;
                }
            }
            if (OK) {
                for (int j = 0; j < n; ++j) {
                    sum += a[i][j];
                }
                break;
            }
        }
        long target = sum;
        long res = -1;
        for (int i = 0; i < n; ++i) {
            boolean OK = true;
            int idx = -1;
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 0) {
                    OK = false;
                    idx = j;
                    break;
                }
            }
            if (!OK) {
                for (int j = 0; j < n; ++j) {
                    if (j != idx) {
                        sum -= a[i][j];
                    }
                }
                res = a[i][idx] = sum;
            }
        }
        boolean OK = true;
        for (int i = 0; i < n; ++i) {
            sum = 0;
            for (int j = 0; j < n; ++j) sum += a[i][j];
            if (sum != target) OK = false;
        }
        for (int j = 0; j < n; ++j) {
            sum = 0;
            for (int i = 0; i < n; ++i) sum += a[i][j];
            if (sum != target) OK = false;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += a[i][i];
        }
        if (sum != target) OK = false;
        sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += a[i][n - i - 1];
        }
        if (sum != target) OK = false;
        if (res <= 0) OK = false;
        out.println(OK ? res : -1);
    }
}
