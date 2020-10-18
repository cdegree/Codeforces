package current;

import algorithms.ArrayUtils;
import algorithms.Pair;
import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeSet;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = in.nextLongArray(n);
        TreeSet<Pair<Long, Integer>> pairs = new TreeSet<>();
        long[] cuts = new long[n + 1];
        long cost = 0;
        for (int i = 0; i < n; ++i) {
            cost += a[i] * a[i];
            cuts[i] = 1;
            pairs.add(new Pair<>(bestCutCost(a[i], cuts[i] + 1) - bestCutCost(a[i], cuts[i]), i));
        }
        for (int i = 0; i < k - n; ++i) {
            Pair<Long, Integer> pair = pairs.pollFirst();
            cost += pair.x;
            int id = pair.y;
            cuts[id]++;
            pairs.add(new Pair<>(bestCutCost(a[id], cuts[id] + 1) - bestCutCost(a[id], cuts[id]), id));
        }
        out.println(cost);
    }

    long bestCutCost(long x, long cut) {
        long perPile = x / cut;
        long r = x % cut;
        long cntMore = r;
        long cntLess = cut - cntMore;
        return perPile * perPile * cntLess + (perPile + 1) * (perPile + 1) * cntMore;
    }
}
