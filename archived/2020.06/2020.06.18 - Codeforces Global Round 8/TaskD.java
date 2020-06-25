package current;

import algorithms.BitUtil;
import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] countOfBit = new int[22];
        for (int x : a) {
            for (int i = 0; i < 20; ++i) {
                if (BitUtil.testBit(x, i)) {
                    countOfBit[i]++;
                }
            }
        }
        long res = 0;
        while (true) {
            long cur = 0;
            for (int i = 20; i >= 0; --i) {
                if (countOfBit[i] > 0) {
                    --countOfBit[i];
                    cur = cur | (1L << i);
                }
            }
            if (cur == 0) {
                break;
            } else {
                res += cur * cur;
            }
        }
        out.println(res);
    }
}
