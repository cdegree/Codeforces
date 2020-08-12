package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int[] cnt = new int[n + 1];
            for (int x : a) {
                cnt[x]++;
            }
            int maxElement = ArrayUtils.maxElement(cnt, 0, n + 1);
            int maxCount = 0;
            for (int x : a) {
                if (cnt[x] == maxElement) {
                    ++maxCount;
                }
            }
            maxCount /= maxElement;
            //System.out.println(String.format("maxEle = %d maxCount = %d", maxElement, maxCount));
            int m = n - maxElement - maxCount + 1;
            int segment = maxElement - 1;
            int res = m / segment;
            out.println(res);
        }
    }
}
