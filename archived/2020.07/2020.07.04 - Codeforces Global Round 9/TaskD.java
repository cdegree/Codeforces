package current;

import fastio.InputReader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            Vector<Integer> res = new Vector<>();
            int type = -1;
            while (true) {
                boolean OK = true;
                for (int i = 1; i < n; ++i) {
                    if (a[i] < a[i - 1]) {
                        OK = false;
                        break;
                    }
                }
                if (!OK) {
                    int mex = mex(a);
                    int index = 0;
                    if (mex == n) type = 1;
                    if (type == -1) {
                        int[] cnt = new int[n + 1];
                        for (int i = 0; i < n; ++i) {
                            ++cnt[a[i]];
                            if (cnt[a[i]] > 1 || a[i] > mex) {
                                index = i;
                                break;
                            }
                        }
                    } else {
                        for (int i = n - 1; i >= 0; --i) {
                            if (a[i] != i) {
                                index = i;
                                break;
                            }
                        }
                        if (mex < index) {
                            index = mex;
                        }
                    }

                    a[index] = mex;
                    res.add(index + 1);

                } else {
                    break;
                }

            }
            out.println(res.size());
            for (int x : res) {
                out.print(x + " ");
            }
            out.println();
        }
    }

    int mex(int[] a) {
        boolean[] used = new boolean[a.length + 1];
        Arrays.fill(used, false);
        for (int i = 0; i < a.length; ++i) {
            used[a[i]] = true;
        }
        for (int i = 0; i <= a.length; ++i) {
            if (!used[i]) return i;
        }
        return a.length + 1;
    }
}
