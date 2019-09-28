package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskC {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        LinkedList<Integer> res;
        res = new LinkedList<>();
        int[] p = new int[n + 1];
        int[] c = new int[n + 1];
        LinkedList<Integer>[] adj = new LinkedList[n + 1];
        for (int i = 1; i <= n; ++i) {
            p[i] = in.nextInt();
            if (p[i] != -1) {
                if (adj[p[i]] == null) {
                    adj[p[i]] = new LinkedList<>();
                }
                adj[p[i]].add(i);
            }
            c[i] = in.nextInt();
        }
        for (int i = 1; i <= n; ++i) {
            if (c[i] == 1) {
                boolean all = true;
                if (adj[i] != null) {
                    for (int v : adj[i]) {
                        if (c[v] == 0) {
                            all = false;
                            break;
                        }
                    }
                }
                if (all) {
                    res.add(i);
                    if (adj[i] != null) {
                        for (int v : adj[i]) {
                            p[v] = p[i];
                        }
                    }
                }
            }
        }
        if (res.isEmpty()) {
            out.println(-1);
        } else {
            for (int v : res) {
                out.print(v + " ");
            }
        }
    }
}
