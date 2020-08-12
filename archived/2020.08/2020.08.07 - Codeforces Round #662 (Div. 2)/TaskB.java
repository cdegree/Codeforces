package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        TreeMap<Integer,Integer> set = new TreeMap<>();
        set.con
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] cnt = new int[100005];
        TreeSet<Long> pq = new TreeSet<>(Comparator.reverseOrder());
        for (int x : a) {
            pq.remove(encode(x, cnt[x]));
            cnt[x]++;
            pq.add(encode(x, cnt[x]));
        }
        int q = in.nextInt();
        while (q-- > 0) {
            char op = in.next().toCharArray()[0];
            int x = in.nextInt();
            if (op == '-') {
                pq.remove(encode(x, cnt[x]));
                cnt[x]--;
                pq.add(encode(x, cnt[x]));
            } else {
                pq.remove(encode(x, cnt[x]));
                cnt[x]++;
                pq.add(encode(x, cnt[x]));
            }
            if (!pq.isEmpty()) {
                Vector<Integer> length = new Vector<>();
                int c = 0;
                for (long v : pq) {
                    int[] r = decode(v);
                    length.add(r[1]);
                    ++c;
                    if (c >= 3) {
                        break;
                    }
                }
                boolean ok1 = false;
                if (length.get(0) >= 4) {
                    ok1 = true;
                    length.set(0, length.get(0) - 4);
                }
                int p = 0;
                for (int v : length) {
                    p += v / 2;
                }
                boolean ok2 = false;
                if (p >= 2) {
                    ok2 = true;
                }
                if (ok1 && ok2) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            } else {
                out.println("NO");
            }
        }
    }

    long encode(int x, int cnt) {
        return (1L << 30) * cnt + x;
    }

    int[] decode(long y) {
        int x = (int) (y % (1L << 30));
        int cnt = (int) (y / (1L << 30));
        return new int[]{x, cnt};
    }
}
