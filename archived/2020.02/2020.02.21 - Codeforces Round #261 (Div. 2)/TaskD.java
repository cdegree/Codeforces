package current;

import algorithms.BinaryIndexedTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.TreeMap;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = new int[n];
        for (int i = 0; i < n; ++i) {
            b[i] = a[i];
        }
        Arrays.sort(b);
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        int m = 1;
        for (int i = 0; i < n; ++i) {
            if (!mp.containsKey(b[i])) {
                mp.put(b[i], m++);
            }
        }
        for (int i = 0; i < n; ++i) {
            a[i] = mp.get(a[i]);
        }
        int[] fi = new int[n];
        int[] fj = new int[n];
        int[] cnt = new int[m];
        for (int i = 0; i < n; ++i) {
            fi[i] = ++cnt[a[i]];
        }
        Arrays.fill(cnt, 0);
        for (int i = n - 1; i >= 0; --i) {
            fj[i] = ++cnt[a[i]];
        }
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        long res = 0;
        for (int i = n - 1; i > 0; --i) {
            bit.insertElement(fj[i]);
            res += bit.findRank(fi[i - 1] - 1);
        }
        out.println(res);
    }
}
