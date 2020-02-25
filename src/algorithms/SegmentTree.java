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
 
    public static void main(String[] args) {

//        int n = 10;
//        SegmentTree st = new SegmentTree(n);
//
//        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//
//        st.build(1, a);
//
//        int from = 1;
//        int to = 3;
//        int ans = st.query(1, from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));
//
//        from = 8;
//        to = 10;
//        ans = st.query(1, from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));
//
//        from = 4;
//        to = 7;
//        ans = st.query(1, from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));
//
//        st.update(1, 10, 10, 1);
//        from = 8;
//        to = 10;
//        ans = st.query(1, from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));

    }

    void pull_up(Node cur, Node left, Node right) {
        int pair = Math.min(left.rightBracket, right.leftBracket);
        cur.val = left.val + right.val + pair * 2;
        cur.leftBracket = left.leftBracket + right.leftBracket - pair;
        cur.rightBracket = right.rightBracket + left.rightBracket - pair;
    }

    public void build(char[] a) {
        build(root, a);
    }

    public void build(Node cur, char[] a) {

//        System.out.println(String.format("node[%d] [%d,%d]", cur, left[cur], right[cur]));
        if (cur.left == cur.right) {
            //System.out.println(" L:"+cur.left);
            if (a[cur.left - 1] == ')') {
                cur.leftBracket = 1;
            } else {
                cur.rightBracket = 1;
            }
            cur.val = 0;
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
        cur.lazy_value = -1;
    }

    void push_down(int cur) {
//        if (lazy_value[cur] != -1) {
//            int lc = cur * 2;
//            int rc = cur * 2 + 1;
//            lazy_value[lc] = lazy_value[rc] = lazy_value[cur];
//            sum[lc] = (right[lc] - left[lc] + 1) * lazy_value[cur];
//            sum[rc] = (right[rc] - left[rc] + 1) * lazy_value[cur];
////            pf("left", lc);
////            pf("right", rc);
//            lazy_value[cur] = -1;
//        }
    }

    void pf(String label, Node cur) {
        System.out.println(String.format("%s:node[%d] [%d,%d] sum ", label, cur, cur.left, cur.right));
    }

//    public void update(int cur, int from, int to, int value) {
////        System.out.println(String.format("before node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
//        if (from <= left[cur] && right[cur] <= to) {
//            sum[cur] = (right[cur] - left[cur] + 1) * value;
//            lazy_value[cur] = value;
//        } else {
//            push_down(cur);
//            int lc = cur * 2;
//            int rc = cur * 2 + 1;
//            if (to <= right[lc]) {
//                update(lc, from, to, value);
//            } else if (from >= left[rc]) {
//                update(rc, from, to, value);
//            } else {
//                update(lc, from, to, value);
//                update(rc, from, to, value);
//            }
//            pull_up(cur);
////            System.out.println(String.format("after node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
//        }
//    }

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
            //push_down(cur);
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
