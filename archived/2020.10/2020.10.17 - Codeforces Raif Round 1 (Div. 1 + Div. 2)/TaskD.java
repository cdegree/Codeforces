package current;

import algorithms.Pair;
import fastio.InputReader;

import java.io.PrintWriter;
import java.util.*;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray1(n);
        ArrayList<Pair<Integer,Integer>> pairs = new ArrayList<>();
        TreeSet<Integer> emptyRowsCols = new TreeSet<>();
        TreeSet<Integer> emptyCols = new TreeSet<>();

        int rows = n;
        int cols = n;
        boolean valid = true;
        boolean[] added = new boolean[n + 1];

        for (int i = n; i >= 0; --i) {
            if (a[i] == 1) {
                pairs.add(new Pair(rows, i));
                emptyRowsCols.add(pairs.size() - 1);
                --rows;
            } else if (a[i] == 2) {
                if (emptyRowsCols.isEmpty()) {
                    valid = false;
                } else {
                    int idx = emptyRowsCols.pollFirst();
                    pairs.add(new Pair(pairs.get(idx).x, i));
                    emptyCols.add(pairs.size() - 1);
                }
            } else if (a[i] == 3) {
                if (emptyCols.isEmpty() && emptyRowsCols.isEmpty()) {
                    valid = false;
                } else if (!emptyCols.isEmpty()) {
                    int idx = emptyCols.pollFirst();
                    pairs.add(new Pair(rows, i));
                    emptyCols.add(pairs.size() - 1);
                    pairs.add(new Pair(rows, pairs.get(idx).y));
                    --rows;
                } else if (!emptyRowsCols.isEmpty()) {
                    int idx = emptyRowsCols.pollFirst();
                    pairs.add(new Pair(rows, i));
                    emptyCols.add(pairs.size() - 1);
                    pairs.add(new Pair(rows, pairs.get(idx).y));
                    --rows;
                }
            }
        }
        if (valid) {
            Collections.sort(pairs);
            out.println(pairs.size());
            for (Pair pair : pairs) {
                out.println(pair.x + " " + pair.y);
            }
        } else {
            out.println(-1);
        }
    }

}
