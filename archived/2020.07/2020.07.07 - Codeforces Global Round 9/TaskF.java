package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long[] a = in.nextLongArray(3);
        Node[] nodes = new Node[3];
        for (int i = 0; i < 3; ++i) {
            nodes[i] = new Node(a[i], i + 1);
        }
        out.println("First");
        out.flush();
        while (true) {
            Arrays.sort(nodes, Comparator.comparingLong(o -> o.x));
            long q = nodes[2].x * 2 - nodes[1].x - nodes[0].x;
            if (nodes[2].x - nodes[1].x == nodes[1].x - nodes[0].x) {
                q = nodes[2].x - nodes[1].x;
            }
            long ret = query(q, in, out);
            if (ret == 0 || ret == -1) {
                break;
            } else {
                for (Node node : nodes) {
                    if (node.id == ret) {
                        node.x += q;
                    }
                }
            }
        }
    }

    long query(long x, InputReader in, PrintWriter out) {
        out.println(x);
        out.flush();
        return in.nextInt();
    }

    class Node {
        long x;
        int id;

        public Node(long x, int id) {
            this.x = x;
            this.id = id;
        }

    }

}
