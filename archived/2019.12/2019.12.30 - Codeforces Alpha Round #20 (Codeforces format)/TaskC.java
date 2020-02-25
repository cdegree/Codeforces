package current;

import algorithms.Graph;
import fastio.InputReader;
import sun.reflect.generics.tree.Tree;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n, m);
        for (int i = 0; i < m; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            graph.addEdge(a, b, w);
            graph.addEdge(b, a, w);
        }
        long[] distanceFromSource = new long[n + 1];
        int[] prePos = new int[n + 1];
        TreeMap<Long, Vector<Integer>> nearestNode = new TreeMap<>();
        Arrays.fill(distanceFromSource, 1L << 60);
        nearestNode.put(0L, new Vector<>());
        nearestNode.get(0L).add(1);
        distanceFromSource[1] = 0;
        while (!nearestNode.isEmpty()) {
            Map.Entry<Long, Vector<Integer>> entry = nearestNode.pollFirstEntry();
            long curDis = entry.getKey();
            Vector<Integer> curPositions = entry.getValue();
            for (int curPos : curPositions) {
                for (Graph.Edge edge : graph.getAdj()[curPos]) {
                    if (distanceFromSource[edge.v] > curDis + edge.value) {
                        distanceFromSource[edge.v] = curDis + edge.value;
                        prePos[edge.v] = curPos;
                        if (!nearestNode.containsKey(distanceFromSource[edge.v])) {
                            nearestNode.put(distanceFromSource[edge.v], new Vector<>());
                        }
                        nearestNode.get(distanceFromSource[edge.v]).add(edge.v);
                    }
                }
            }
        }
        Vector<Integer> path = new Vector<>();
        int pos = n;

        if (distanceFromSource[pos] == 1L << 60) {
            out.println(-1);
            return;
        }
        while (pos != 1) {
            path.add(pos);
            pos = prePos[pos];
        }
        path.add(1);
        Collections.reverse(path);
        for (int i = 0; i < path.size(); ++i) {
            out.print(path.get(i) + " ");
        }
    }
}
