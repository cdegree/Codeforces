package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int[] prefixSum = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                prefixSum[i] = prefixSum[i - 1] + (s[i - 1] - '0');
            }
            TreeMap<Integer, Integer> prefixSumIndex = new TreeMap<>();
            prefixSumIndex.put(0, 1);
            long res = 0;
            for (int i = 1; i <= n; ++i) {
                int key = prefixSum[i] - i;
                //System.out.println(String.format("%d  %d = %d", i, key, prefixSumIndex.getOrDefault(key, 0)));
                res += prefixSumIndex.getOrDefault(key, 0);
                prefixSumIndex.put(key, prefixSumIndex.getOrDefault(key, 0) + 1);
            }
            out.println(res);
        }
    }
}
