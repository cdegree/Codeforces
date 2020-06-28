package current;

import algorithms.Pair;
import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.TreeSet;


public class TaskG {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        TreeSet<Pair> board = new TreeSet<>();
        int[] a = new int[n + n + 1];
        for (int i = 1; i <= n + n; ++i) a[i] = i - 1;
        SegmentTree st = new SegmentTree(n + n);
        st.build(a);
        TreeMap<Integer, Integer> slot = new TreeMap<>();
        while (m-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            Pair p = new Pair(x, y);
            int pos = y + Math.abs(k - x);
            if (board.contains(p)) {
                board.remove(p);
                st.update(1, pos, -1);
                slot.put(pos, slot.getOrDefault(pos, 0) - 1);
                if (slot.get(pos) == 0) {
                    slot.remove(pos);
                }
            } else {
                board.add(p);
                st.update(1, pos, 1);
                slot.put(pos, slot.getOrDefault(pos, 0) + 1);
            }
            if (slot.isEmpty()) {
                out.println(0);
            } else {
                int highest = slot.lastKey();
                int value = st.query(1, highest) - n;
                out.println(Math.max(value, 0));
            }
        }
    }
}
