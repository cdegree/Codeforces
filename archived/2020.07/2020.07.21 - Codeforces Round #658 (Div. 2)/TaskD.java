package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] a = in.nextLine().toCharArray();
            char[] b = in.nextLine().toCharArray();
            Vector<Integer> res = new Vector<>();
            int left = 1;
            int right = n - 1;
            for (int i = 0; i < n; ++i) {
                int j = n - i - 1;
                int next;
                if (i % 2 == 1) {
                    next = left++;
                } else {
                    next = right--;
                }
                if (j == 0) {
                    if (a[0] != b[0]) {
                        res.add(1);
                        break;
                    }
                }
                if (a[0] == b[j]) {
                    res.add(1);
                    invert(a, 0);
                }
                res.add(j + 1);
                a[0] = a[next];
                if (i % 2 == 0) {
                    invert(a, 0);
                }
            }
            out.print(res.size() + " ");
            for (int i = 0; i < res.size(); ++i) {
                out.print(res.get(i) + " ");
            }
            out.println();
        }
    }

    void invert(char[] s, int pos) {
        if (s[pos] == '1') s[pos] = '0';
        else s[pos] = '1';
    }
}
