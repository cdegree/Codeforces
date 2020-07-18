package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = in.nextIntArray(n);
            TreeMap<Integer, Integer> need = new TreeMap<>();
            for (int x : a) {
                int diff = (k - x % k) % k;
                need.put(diff, need.getOrDefault(diff, 0) + 1);
            }
            long maxCount = 1;
            long maxDiff = -1;
            for (int key : need.keySet()) {
                if (key == 0) continue;
                if (maxCount <= need.get(key)) {
                    maxCount = need.get(key);
                    maxDiff = key;
                }
            }
            if (maxDiff != -1) {
                long res = (maxCount - 1) * k + maxDiff + 1;
                out.println(res);
            }
            else{
                out.println(0);
            }
        }
    }
}
