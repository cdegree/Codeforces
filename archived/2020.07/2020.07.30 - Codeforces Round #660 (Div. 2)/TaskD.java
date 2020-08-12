package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    long[] a;
    int[] b;
    Vector<Integer> res = new Vector<>();
    Vector<Integer>[] adj;
    Vector<Integer> negatives = new Vector<>();
    long ans;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        a = in.nextLongArray1(n);
        b = in.nextIntArray1(n);
        ans = 0;
        adj = new Vector[n + 1];
        for (int i = 1; i <= n; ++i) {
            adj[i] = new Vector<>();
        }
        for (int i = 1; i <= n; ++i) {
            if (b[i] != -1) {
                adj[b[i]].add(i);
            }
        }
        for (int i = 1; i <= n; ++i) {
            if (b[i] == -1) {
                negatives.clear();
                dfs(i);
                Collections.reverse(negatives);
                for (int v : negatives) {
                    res.add(v);
                }
            }
        }
        out.println(ans);
        for (int v : res) {
            out.print(v + " ");
        }
    }

    void dfs(int u) {
        System.out.println("u = " + u);
        for (int v : adj[u]) {
            dfs(v);
        }
        if (a[u] > 0) {
            res.add(u);
            ans += a[u];
            if (b[u] != -1) {
                a[b[u]] += a[u];
            }
        } else {
            negatives.add(u);
            ans += a[u];
        }
    }
}
