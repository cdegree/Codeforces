package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskC {
    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getCost(Node a) {
            return (x - a.x) * (x - a.x) + (y - a.y) * (y - a.y);
        }

        @Override
        public boolean equals(Object obj) {
            return ((Node)obj).x==x&&((Node)obj).y==y;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int r1 = in.nextInt();
        int c1 = in.nextInt();
        int r2 = in.nextInt();
        int c2 = in.nextInt();
        r1--;
        c1--;
        r2--;
        c2--;
        char[][] g = new char[n][n];
        for (int i = 0; i < n; ++i) {
            g[i] = in.next().toCharArray();
        }

        LinkedList<Node> s = getExtendNodes(r1, c1, g);
        LinkedList<Node> e = getExtendNodes(r2, c2, g);
        for(Node t:s){
            if(t.equals(e.getFirst())){
                out.println(0);
                return;
            }
        }
        int res = 50 * 50 * 50;
        for (Node t1 : s) {
            for (Node t2 : e) {
                res = Math.min(res, t1.getCost(t2));
            }
        }
        out.println(res);
    }

    LinkedList<Node> getExtendNodes(int r, int c, char[][] g) {
        LinkedList<Node> res = new LinkedList<>();

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int n = g.length;
        boolean[][] vis = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(vis[i], false);
        }
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(new Node(r, c));
        res.addLast(new Node(r, c));
        vis[r][c] = true;
        while (!q.isEmpty()) {
            Node cur = q.pollFirst();
            for (int k = 0; k < 4; ++k) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !vis[nx][ny] && g[nx][ny] == '0') {
                    q.addLast(new Node(nx, ny));
                    res.addLast(new Node(nx, ny));
                    vis[nx][ny] = true;
                }
            }
        }
        return res;
    }
}
