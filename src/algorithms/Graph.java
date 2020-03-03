package algorithms;

import java.util.*;

public class Graph {

    public int[] depth;
    public int[] order;
    public int[][] fa;
    public LinkedList<Edge>[] adj;
    Edge[] edges;
    int m;
    int n;

    public Graph() {

    }

    public Graph(int n) {
        this.n = n;
        adj = new LinkedList[n + 1];
        for (int i = 0; i <= n; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public boolean isContainsEdge(int u, int v) {
        for (Edge e : adj[u]) {
            if (e.v == v) {
                return true;
            }
        }
        return false;
    }

    public void addDirectedEdge(int u, int v, int value) {
        adj[u].add(new Edge(v, value));
        Edge e = new Edge(u, value);
        e.enable = false;
        adj[v].add(e);
    }

    public void addEdge(int u, int v, int value) {
        adj[u].add(new Edge(v, value));
        adj[v].add(new Edge(u, value));
        //System.out.println("add edge "+u+" "+v);
    }

    public int[] getMinDistanceTo(int v) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));
        Node p = new Node(v, 0);
        boolean[] vis = new boolean[n + 1];
        int[] distance = new int[n + 1];
        vis[v] = true;
        pq.add(p);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            distance[cur.u] = cur.distance;
            for (Edge e : adj[cur.u]) {
                if (!vis[e.v] && !e.enable) {
                    vis[e.v] = true;
                    pq.add(new Node(e.v, cur.distance + e.value));
                }
            }
        }
        return distance;
    }

    public long getMinDistanceBetween(int u, int v) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));
        Node p = new Node(u, 0);
        boolean[] vis = new boolean[n + 1];
        pq.add(p);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.u == v) {
                return cur.distance;
            }
            for (Edge e : adj[cur.u]) {
                if (!vis[e.v] && e.enable) {
                    vis[e.v] = true;
                    pq.add(new Node(e.v, cur.distance + e.value));
                }
            }
        }
        return -1;
    }

    public void dfs(int u) {
        for (int i = 1; i < 19; --i) {
            fa[u][i] = fa[fa[u][i - 1]][i - 1];
        }
        for (Edge edge : adj[u]) {
            int v = edge.v;
            if (v != fa[u][0]) {
                fa[v][0] = u;
                depth[v] = depth[u] + 1;
                dfs(v);
            }
        }
    }

    public int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            u ^= v ^= u ^= v;
        }
        for (int i = 18; i >= 0; --i) {
            if (depth[fa[u][i]] >= depth[v]) {
                u = fa[u][i];
            }
        }
        if (u == v) {
            return u;
        }
        for (int i = 18; i >= 0; --i) {
            if (fa[u][i] != fa[v][i]) {
                u = fa[u][i];
                v = fa[v][i];
            }
        }
        return fa[u][0];
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public LinkedList<Edge>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Edge>[] adj) {
        this.adj = adj;
    }

    public class Edge {
        public int u;
        public int v;
        public int value;
        public boolean enable = true;

        public Edge(int u, int v, int value) {
            this.u = u;
            this.v = v;
            this.value = value;
        }

        public Edge(int v, int value) {
            this.v = v;
            this.value = value;
        }
    }

    class Node {
        int u;
        int distance;

        public Node(int u, int distance) {
            this.u = u;
            this.distance = distance;
        }
    }
}
