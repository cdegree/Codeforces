package current;

import algorithms.ArrayUtils;
import algorithms.Utils;
import fastio.InputReader;

import javax.rmi.CORBA.Util;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] x = in.nextIntArray(n);
            int[] y = in.nextIntArray(n);
            ArrayUtils.mergeSort(x);
            int p1 = 0;
            int p2 = 0;
            int[] cover = new int[n];
            while (p1 < n) {
                int left = x[p1];
                while (p2 + 1 < n && x[p2 + 1] - left <= k) ++p2;
                cover[p1] = p2;
                ++p1;
            }
            int[] maxBehind = new int[n + 1];
            for (int i = n - 1; i >= 0; --i) {
                maxBehind[i] = Math.max(maxBehind[i + 1], cover[i] - i + 1);
            }
            int best = 0;
            //for (int i = 0; i < n; ++i) Utils.pf("%d", x[i]);

            for (int i = 0; i < n; ++i) {
                //Utils.pf("cover[%d] = %d, maxBehind[%d] = %d", i, cover[i], i, maxBehind[i]);
                best = Math.max(best, cover[i] - i + 1 + ((cover[i] + 1 < n) ? maxBehind[cover[i] + 1] : 0));
            }
            out.println(best);
        }
    }
}
