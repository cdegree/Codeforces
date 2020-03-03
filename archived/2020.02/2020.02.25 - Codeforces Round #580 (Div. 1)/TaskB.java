package current;

import algorithms.BitUtil;
import algorithms.Graph;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        int ans = -1;
        for (int pos = 0; pos < 61; ++pos) {
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (BitUtil.testBit(a[i], pos)) {
                    ++cnt;
                }
            }
            if (cnt >= 3) {
                ans = 3;
            }
        }
        if (ans == 3) {
            out.println(3);
        } else {
            Graph g = new Graph(n);
            for (int i = 0; i < 61; ++i) {
                Vector<Integer> nodes = new Vector<>();
                for (int j = 0; j < n; ++j) {
                    if (BitUtil.testBit(a[j], i)) {
                        nodes.add(j);
                    }
                }
                for (int j = 0; j < nodes.size(); ++j) {
                    for (int k = j + 1; k < nodes.size(); ++k) {
                        if (!g.isContainsEdge(nodes.get(j), nodes.get(k))) {
                            g.addEdge(nodes.get(j), nodes.get(k), 1);
                        }
                    }
                }
            }
            long minDistance = 1000000000;
            for (int i = 0; i < n; ++i) {
                if (!g.adj[i].isEmpty()) {
                    for (Graph.Edge e : g.adj[i]) {
                        int j = e.v;
                        if (j < i) continue;
                        e.enable = false;
                        Graph.Edge eV = null;
                        for (Graph.Edge e2 : g.adj[j]) {
                            if (e2.v == i) {
                                eV = e2;
                                e2.enable = false;
                                break;
                            }
                        }
                        long dis = g.getMinDistanceBetween(i, j);
                        e.enable = true;
                        eV.enable = true;
                        if (dis != -1) {
                            minDistance = Math.min(minDistance, dis);
                        }
                    }
                }
            }
            if (minDistance != 1000000000) {
                out.println(minDistance + 1);
            } else {
                out.println(-1);
            }
        }
    }
}
