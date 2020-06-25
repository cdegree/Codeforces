package current;

import fastio.InputReader;
import org.omg.CORBA.INTERNAL;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            ++n;
            TreeSet<Integer>[] par = new TreeSet[n];
            for (int i = 1; i < n; ++i) {
                par[i] = new TreeSet<>();
            }
            boolean[] closed = new boolean[n];

            for (int i = 0; i < m; ++i) {
                int u = in.nextInt();
                int v = in.nextInt();
                par[v].add(u);
            }

            Vector<Integer> res = new Vector<>();
            for (int i = 1; i < n; ++i) {
                if (!closed[i]) {
                    for (int v : par[i]) {
                        if (!closed[v]) {
                            for (int w : par[v]) {
                                if (!closed[w]) {
                                    closed[i] = true;
                                    res.add(i);
                                    break;
                                }
                            }
                            if (closed[i]) {
                                break;
                            }
                        }
                    }
                }
            }
            if (res.size() * 7 > 4 * n) while (true) ;
            out.println(res.size());
            for (int x : res) {
                out.print(x + " ");
            }
            out.println();
        }
    }
}
