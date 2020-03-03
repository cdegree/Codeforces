package current;

import algorithms.DisjointSet;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        DisjointSet ds = new DisjointSet(n + 1);
        Vector<Road> rebuildRoads = new Vector<>();
        for (int i = 0; i < n - 1; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int xa = ds.find(a);
            int xb = ds.find(b);
            if (xa != xb) {
                ds.union(xb, xa);
            } else {
                rebuildRoads.add(new Road(a, b));
            }
        }
        int k = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                int xa = ds.find(i);
                int xb = ds.find(j);
                if (xa != xb) {
                    rebuildRoads.get(k).u = xa;
                    rebuildRoads.get(k).v = xb;
                    ++k;
                    ds.union(xb, xa);
                }
            }
        }
        out.println(rebuildRoads.size());
        for (int i = 0; i < rebuildRoads.size(); ++i) {
            out.println(rebuildRoads.get(i).i + " " + rebuildRoads.get(i).j + " " + rebuildRoads.get(i).u + " " + rebuildRoads.get(i).v);
        }
    }

    class Road {
        int i, j, u, v;
        boolean used = false;

        public Road(int i, int j) {
            this.i = i;
            this.j = j;
            this.u = u;
            this.v = v;
        }
    }
}
