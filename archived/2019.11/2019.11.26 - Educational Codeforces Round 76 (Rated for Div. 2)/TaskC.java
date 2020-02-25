package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            Vector<Integer>[] adj = new Vector[n + 1];
            for (int i = 0; i < n; ++i) {
                if (adj[a[i]] == null) {
                    adj[a[i]] = new Vector<>();
                }
                adj[a[i]].add(i);
            }
            int result = -1;
            for (int i = 1; i <= n; ++i) {
                if (adj[i] != null && adj[i].size() >= 2) {
                    for (int j = 0; j < adj[i].size() - 1; ++j) {
                        int diff = adj[i].get(j + 1) - adj[i].get(j) + 1;
                        if (result == -1) {
                            result = diff;
                        } else if (result > diff) {
                            result = diff;
                        }
                    }
                }
            }
            out.println(result);
        }
    }
}
