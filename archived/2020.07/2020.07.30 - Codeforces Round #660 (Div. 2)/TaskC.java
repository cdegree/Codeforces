package current;

import algorithms.Pair;
import fastio.InputReader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    int[] p;
    int[] h;
    Vector<Integer>[] adj;
    boolean OK;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        Object obj = new Object();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            p = new int[n + 1];
            h = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                p[i] = in.nextInt();
            }
            for (int i = 1; i <= n; ++i) {
                h[i] = in.nextInt();
            }
            adj = new Vector[n + 1];
            for (int i = 0; i <= n; ++i) {
                adj[i] = new Vector<>();
            }
            for (int i = 0; i < n - 1; ++i) {
                int u = in.nextInt();
                int v = in.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            OK = true;
            dfs(1, 1);
            out.println(OK ? "YES" : "NO");
        }
    }

    Pair dfs(int cur, int pre) {
        Pair pair = new Pair();
        pair.x = 0;
        pair.y = 0;
        for (int v : adj[cur]) {
            if (v == pre) continue;
            Pair pv = dfs(v, cur);
            pair.x += pv.x;
            pair.y += pv.y;
        }
        //h  = good - bad + 2d, 0<=d<=bad
        //pair.x + pair.y + h[cur]
        //p[cur] = good(u)+bad(u)
        //diff = 2d
        if (h[cur] > pair.x + pair.y + p[cur] || h[cur] < pair.x - pair.y - p[cur]) {
            OK = false;
        } else {
            int good = pair.x;
            int bad = pair.y + p[cur];
            int diff = h[cur] - (good - bad);
            //System.out.println(String.format("h[cur] = %d cur = %d diff= %d pair.x = %d pair.y = %d p[cur] = %d", h[cur], cur, diff, pair.x, pair.y, p[cur]));
            if (diff % 2 == 0) {
                int d = diff / 2;
                good += d;
                bad -= d;
                //System.out.println(String.format("%d %d ", good, bad));
            } else {
                OK = false;
            }
            pair.x = good;
            pair.y = bad;
        }
        return pair;
    }
}
