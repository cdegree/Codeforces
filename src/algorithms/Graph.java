package algorithms;

import java.util.LinkedList;

public class Graph {

    public class Edge{
        public int u;
        public int v;
        public int value;

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

    Edge[] edges;
    int m;
    int n;
    LinkedList<Edge>[] adj;
    public int[] depth;
    public int[] order;
    public int[][] fa;



    public Graph(int n, int m) {
        this.n=n;
        this.m=m;
        adj = new LinkedList[n+1];
        for(int i=0;i<=n;++i){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u,int v,int value){
        adj[u].add(new Edge(v,value));
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
        if(depth[u]<depth[v]){
            u^=v^=u^=v;
        }
        for(int i=18;i>=0;--i){
            if(depth[ fa[u][i] ] >= depth[v]){
                u = fa[u][i];
            }
        }
        if(u==v){
            return u;
        }
        for(int i=18;i>=0;--i){
            if( fa[u][i] !=fa[v][i] ){
                u=fa[u][i];
                v=fa[v][i];
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
}
