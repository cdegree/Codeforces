package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        SegmentTree st = new SegmentTree(n);
        st.build(a);
        int m = in.nextInt();
        while (m-- > 0) {
            String[] tokens = in.nextLine().split(" ");
            if (tokens.length == 2) {
                int lf = Integer.parseInt(tokens[0]);
                int rg = Integer.parseInt(tokens[1]);
                long ans;
                if (lf <= rg) {
                    ans = st.query(lf + 1, rg + 1);
                } else {
                    ans = st.query(lf + 1, n);
                    ans = Math.min(ans, st.query(1, rg + 1));
                }
                out.println(ans);
            } else if (tokens.length == 3) {
                int lf = Integer.parseInt(tokens[0]);
                int rg = Integer.parseInt(tokens[1]);
                int v = Integer.parseInt(tokens[2]);
                if (lf <= rg) {
                    st.update(lf + 1, rg + 1, v);
                } else {
                    st.update(lf + 1, n, v);
                    st.update(1, rg + 1, v);
                }
            }
        }
    }

    class SegmentTree {

        int n;
        Node[] nodes = null;
        int[] rev = null;

        public SegmentTree(int n) {
            int m = n * 4;
            this.n = n;
            nodes = new Node[m];
            rev = new int[m];
            for (int i = 0; i < m; ++i) {
                nodes[i] = new Node();
            }
        }


        public void build(int[] a) {
            build(1, 1, n, a);
        }

        public void build(int root, int nodeLeft, int nodeRight, int[] a) {
            nodes[root].left = nodeLeft;
            nodes[root].right = nodeRight;
//      System.out.println(String.format("node[%d] [%d,%d]", cur, left[cur], right[cur]));
            if (nodeLeft == nodeRight) {
                //System.out.println(" L:"+cur.left);
                nodes[root].minValue = a[nodeLeft - 1];
//          System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
            } else {
                int leftChild = root * 2;
                int rightChild = root * 2 + 1;
                int mid = (nodeLeft + nodeRight) / 2;
                build(leftChild, nodeLeft, mid, a);
                build(rightChild, mid + 1, nodeRight, a);
                pull_up(nodes[root], nodes[leftChild], nodes[rightChild]);
                //System.out.println(String.format("node[%d] [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));
            }
        }

        String subString(char[] a, int st, int ed) {
            StringBuilder sb = new StringBuilder();
            for (int i = st; i < ed; ++i) {
                sb.append(a[i - 1]);
            }
            return sb.toString();
        }


        void pull_up(Node cur, Node left, Node right) {
            cur.minValue = Math.min(left.minValue, right.minValue);
            //int pair = Math.min(left.rightBracket, right.leftBracket);
            //cur.val = Math.max(left.val, right.val);
            //cur.leftBracket = left.leftBracket + right.leftBracket - pair;
            //cur.rightBracket = right.rightBracket + left.rightBracket - pair;
        }

        void push_down(int root) {
            if (nodes[root].lazy_value != 0) {
                op(root * 2, nodes[root].lazy_value);
                op(root * 2 + 1, nodes[root].lazy_value);
                nodes[root].lazy_value = 0;
            }
        }

        void op(int root, long value) {
            nodes[root].minValue += value;
            nodes[root].lazy_value += value;
        }

        void pf(String label, Node cur) {
            System.out.println(String.format("%s:node[%d] [%d,%d] sum ", label, cur, cur.left, cur.right));
        }

        public void update(int queryLeft, int queryRight, int value) {
            //System.out.println(String.format("%d->%d += %d", from, to, value));
            update(1, 1, n, queryLeft, queryRight, value);
        }

        public void update(int root, int nodeLeft, int nodeRight, int queryLeft, int queryRight, int value) {
            //System.out.println(String.format("enter node[%d] range[%d,%d] query[%d,%d]", root, nodeLeft, nodeRight, queryLeft, queryRight));
            //System.out.println(String.format("enter node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));

            if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
                op(root, value);
                //flip(root);
                //System.out.println(String.format("exit node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));
            } else {
                push_down(root);
                int leftChild = root * 2;
                int rightChild = root * 2 + 1;
                int mid = (nodeLeft + nodeRight) / 2;
                if (queryLeft <= mid) {
                    update(leftChild, nodeLeft, mid, queryLeft, queryRight, value);
                }
                if (queryRight >= mid + 1) {
                    update(rightChild, mid + 1, nodeRight, queryLeft, queryRight, value);
                }
                pull_up(nodes[root], nodes[leftChild], nodes[rightChild]);
                //System.out.println(String.format("exit node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));

                //System.out.println(String.format("after node [%d,%d] max =%d  add %d", cur.left, cur.right, cur.val, value));
            }
        }

        public long query(int queryLeft, int queryRight) {
            Node qNode = query(1, 1, n, queryLeft, queryRight);
            return qNode.minValue;
        }

        public Node query(int root, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
//        System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));

            if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
                return nodes[root];
            } else {
                push_down(root);
                int leftChild = root * 2;
                int rightChild = root * 2 + 1;
                int mid = (nodeLeft + nodeRight) / 2;
                if (queryRight <= mid) {
                    return query(leftChild, nodeLeft, mid, queryLeft, queryRight);
                }
                if (queryLeft >= mid + 1) {
                    return query(rightChild, mid + 1, nodeRight, queryLeft, queryRight);
                }
                Node ql = query(leftChild, nodeLeft, mid, queryLeft, queryRight);
                Node qr = query(rightChild, mid + 1, nodeRight, queryLeft, queryRight);
                Node qNode = new Node();
                pull_up(qNode, ql, qr);
                return qNode;
            }
        }

        class Node {
            int left;
            int right;
            Node lcld;
            Node rcld;
            long minValue;
            long lazy_value;
        }
    }
}
