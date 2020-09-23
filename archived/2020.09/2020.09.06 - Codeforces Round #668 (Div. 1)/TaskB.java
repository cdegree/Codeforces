package current;

import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskB {
    int maxLength;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int da = in.nextInt();
            int db = in.nextInt();
            LinkedList<Integer>[] adj = new LinkedList[n + 1];
            for (int i = 0; i <= n; ++i) {
                adj[i] = new LinkedList<>();
            }
            for (int i = 1; i < n; ++i) {
                int u = in.nextInt();
                int v = in.nextInt();
                adj[u].addLast(v);
                adj[v].addLast(u);
            }
            boolean win = true;
            if (getDisFromU(a, b, adj) > da) {
                maxLength = 0;
                getDepth(a, a, 1, adj);
                if (db >= 2 * da + 1 && maxLength >= 2 * da + 2) {
                    win = false;
                }
                //Utils.pf("a = %d b = %d maxLength = %d",a, b,maxLength);
            }
            out.println(win ? "Alice" : "Bob");
        }
    }

    int getDisFromU(int u, int v, LinkedList<Integer>[] adj) {
        LinkedList<Integer> dq = new LinkedList<>();
        boolean[] vis = new boolean[adj.length];
        vis[u] = true;
        dq.addLast(u);
        dq.addLast(0);
        int ret = 0;
        while (!dq.isEmpty()) {
            int cur = dq.removeFirst();
            int dis = dq.removeFirst();
            if (cur == v) {
                ret = dis;
                break;
            }
            for (int nv : adj[cur]) {
                if (!vis[nv]) {
                    vis[nv] = true;
                    dq.addLast(nv);
                    dq.addLast(dis + 1);
                }
            }
        }
        //Utils.pf("%d %d = %d",u,v,ret);
        return ret;
    }

    int getDepth(int pa, int u, int depth, LinkedList<Integer>[] adj) {
        int maxDepth = 0;
        int secDepth = 0;
        for (int v : adj[u]) {
            if (v == pa) continue;
            int dp = getDepth(u, v, depth + 1, adj);
            if (dp > maxDepth) {
                secDepth = maxDepth;
                maxDepth = dp;
            } else if (dp > secDepth) {
                secDepth = dp;
            }
        }

        maxLength = Math.max(maxLength, maxDepth + secDepth + 1);
        return maxDepth + 1;
    }
}
