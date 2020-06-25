package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            char[] s = in.nextLine().toCharArray();
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            char[] t = new char[n];
            boolean[] used = new boolean[n];
            TreeMap<Character, Integer> chCnt = new TreeMap<>();
            for (char c : s) {
                chCnt.put(c, chCnt.getOrDefault(c, 0) + 1);
            }
            while (true) {
                Vector<Integer> idxs = new Vector<>();
                for (int i = 0; i < n; ++i) {
                    if (!used[i] && a[i] == 0) {
                        idxs.add(i);
                    }
                }
                if (idxs.isEmpty()) {
                    break;
                } else {
                    while (chCnt.lastEntry().getValue() < idxs.size()) {
                        chCnt.remove(chCnt.lastKey());
                    }

                    for (int idx : idxs) {
                        used[idx] = true;
                        t[idx] = chCnt.lastKey();
                        for (int i = 0; i < n; ++i) {
                            a[i] -= Math.abs(i - idx);
                        }
                    }
                    chCnt.remove(chCnt.lastKey());
                }
            }
            out.println(t);
        }
    }
}
