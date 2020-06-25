package current;

import algorithms.Graph;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int x = in.nextInt();
            graph.addEdge(u, v, x, 1);
        }
        for (int i = 0; i < k; ++i) {
            int s = in.nextInt();
            int y = in.nextInt();
            graph.addEdge(1, s, y, 2);
        }
        graph.getMinDistanceTo(1);
        int notUsed = 0;
        int used = 0;
        for (Graph.Edge edge : graph.getEdges()) {
            if (edge.type == 2) {
                if (edge.used) used++;
                else notUsed++;
            }
        }
        //System.out.println(notUsed+"  "+used+"   "+cnt);
        int res = (notUsed - used)/2;
        out.println(res);
    }
}
