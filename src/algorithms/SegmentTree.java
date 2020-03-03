package algorithms;

import java.util.Arrays;

public class SegmentTree {

    int n;
    Node root = null;

    public SegmentTree(int n) {
        root = new Node();
        root.left = 1;
        root.right = n;
    }

    public static void change(int[][] a) {

        int[] b = {1, 2, 3, 4};
        a[0] = b;

        int[][] c = {{2, 2, 2, 2}, {2, 2, 2, 2}};
        a = c;
    }

    public static void main(String[] args) {

        int[][] c = {{3, 3, 3, 3}, {3, 3, 3, 3}};
        change(c);
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c[i].length; ++j) {
                System.out.println(c[i][j]);
            }
        }


        int[] a = {0, -4, -2, -11};
        int n = a.length - 1;

        SegmentTree st = new SegmentTree(n);

        st.build(a);

        int from = 1;
        int to = 3;
        int ans = st.query(from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));// -4,-2,-11

        st.update(2, 3, 4);
        from = 1;
        to = 3;
        ans = st.query(from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));//-4,2,-9

        st.update(1, 3, 6);
        from = 1;
        to = 3;
        ans = st.query(from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));//2,8,-3

        st.update(3, 3, 6);
        from = 1;
        to = 3;
        ans = st.query(from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));//2,8,3
    }


    public void build(int[] a) {
        build(root, a);
    }

    public void build(Node cur, int[] a) {

//        System.out.println(String.format("node[%d] [%d,%d]", cur, left[cur], right[cur]));
        if (cur.left == cur.right) {
            //System.out.println(" L:"+cur.left);
            cur.val = a[cur.left];
//            System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        } else {
            Node lc = new Node();
            Node rc = new Node();
            int mid = (cur.left + cur.right) >> 1;
            lc.left = cur.left;
            lc.right = mid;
            rc.left = mid + 1;
            rc.right = cur.right;
            cur.lcld = lc;
            cur.rcld = rc;
            build(lc, a);
            build(rc, a);
            pull_up(cur, lc, rc);
        }
        cur.lazy_value = 0;
    }

    void pull_up(Node cur, Node left, Node right) {
        //int pair = Math.min(left.rightBracket, right.leftBracket);
        cur.val = Math.max(left.val, right.val);
        //cur.leftBracket = left.leftBracket + right.leftBracket - pair;
        //cur.rightBracket = right.rightBracket + left.rightBracket - pair;
    }

    void push_down(Node cur) {
        if (cur.lazy_value > 0) {
            cur.lcld.lazy_value += cur.lazy_value;
            cur.lcld.val += cur.lazy_value;
            cur.rcld.lazy_value += cur.lazy_value;
            cur.rcld.val += cur.lazy_value;
            cur.lazy_value = 0;
        }
    }

    void pf(String label, Node cur) {
        System.out.println(String.format("%s:node[%d] [%d,%d] sum ", label, cur, cur.left, cur.right));
    }

    public void update(int from, int to, int value) {
        //System.out.println(String.format("%d->%d += %d", from, to, value));
        update(root, from, to, value);
    }

    public void update(Node cur, int from, int to, int value) {
        //System.out.println(String.format("before node [%d,%d] max =%d  add %d", cur.left, cur.right, cur.val, value));
        if (from <= cur.left && cur.right <= to) {
            cur.lazy_value += value;
            cur.val += value;
        } else {
            push_down(cur);
            if (from <= cur.lcld.right) {
                update(cur.lcld, from, to, value);
            }
            if (cur.rcld.left <= to) {
                update(cur.rcld, from, to, value);
            }
            pull_up(cur, cur.lcld, cur.rcld);
            //System.out.println(String.format("after node [%d,%d] max =%d  add %d", cur.left, cur.right, cur.val, value));
        }
    }

    public int query(int from, int to) {
        Node qNode = query(root, from, to);
        return qNode.val;
    }

    public Node query(Node cur, int from, int to) {
//        System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        if (from <= cur.left && cur.right <= to) {
            return cur;
        } else {
            Node lc = cur.lcld;
            Node rc = cur.rcld;
            push_down(cur);
            if (to <= lc.right) {
                return query(lc, from, to);
            } else if (from >= rc.left) {
                return query(rc, from, to);
            } else {
                Node ql = query(lc, from, to);
                Node qr = query(rc, from, to);
                Node qNode = new Node();
                pull_up(qNode, ql, qr);
                return qNode;
            }
        }
    }

    class Node {
        int left;
        int right;
        Node lcld;
        Node rcld;
        int val;
        int leftBracket;
        int rightBracket;
        int lazy_value;
    }
}
