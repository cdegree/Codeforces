package algorithms;

public class SegmentTree {

    int n;
    Node[] nodes = null;
    Node[] rNodes = null;
    int[] a = null;
    int[] rev = null;

    public SegmentTree(int n) {
        int m = n * 4;
        this.n = n;
        nodes = new Node[m];
        rNodes = new Node[m];
        rev = new int[m];
        for (int i = 0; i < m; ++i) {
            nodes[i] = new Node();
            rNodes[i] = new Node();
        }
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

//        st.build(a);
//
//        int from = 1;
//        int to = 3;
//        int ans = st.query(from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));// -4,-2,-11
//
//        st.update(2, 3, 4);
//        from = 1;
//        to = 3;
//        ans = st.query(from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));//-4,2,-9
//
//        st.update(1, 3, 6);
//        from = 1;
//        to = 3;
//        ans = st.query(from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));//2,8,-3
//
//        st.update(3, 3, 6);
//        from = 1;
//        to = 3;
//        ans = st.query(from, to);
//        System.out.println(String.format("[%d,%d] = %d", from, to, ans));//2,8,3
    }


    public void build(int[] a) {
        this.a = a;
        build(1, 1, n, a);
    }

    public void build(int root, int nodeLeft, int nodeRight, int[] a) {
        nodes[root].left = nodeLeft;
        nodes[root].right = nodeRight;
        rNodes[root].left = nodeLeft;
        rNodes[root].right = nodeRight;
        nodes[root].lazy_value = rNodes[root].lazy_value = 0;
//      System.out.println(String.format("node[%d] [%d,%d]", cur, left[cur], right[cur]));
        if (nodeLeft == nodeRight) {
            nodes[root].val = a[nodeLeft];
            //System.out.println(" L:"+cur.left);
//            if (a[nodeLeft - 1] == '<') {
//                rNodes[root].leftRight = nodes[root].leftLeft = 1;
//                rNodes[root].leftLeft = nodes[root].leftRight = 0;
//                rNodes[root].rightRight = nodes[root].rightLeft = 1;
//                rNodes[root].rightLeft = nodes[root].rightRight = 0;
//                rNodes[root].maxValue = nodes[root].maxValue = 1;
//            } else {
//                rNodes[root].leftRight = nodes[root].leftLeft = 0;
//                rNodes[root].leftLeft = nodes[root].leftRight = 1;
//                rNodes[root].rightRight = nodes[root].rightLeft = 0;
//                rNodes[root].rightLeft = nodes[root].rightRight = 1;
//                rNodes[root].maxValue = nodes[root].maxValue = 1;
//            }
//          System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        } else {
            int leftChild = root * 2;
            int rightChild = root * 2 + 1;
            int mid = (nodeLeft + nodeRight) / 2;
            build(leftChild, nodeLeft, mid, a);
            build(rightChild, mid + 1, nodeRight, a);
            pull_up(nodes[root], nodes[leftChild], nodes[rightChild]);
            pull_up(rNodes[root], rNodes[leftChild], rNodes[rightChild]);
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
        //int pair = Math.min(left.rightBracket, right.leftBracket);
        //cur.val = Math.max(left.val, right.val);
        cur.maxValue = Math.max(left.maxValue, right.maxValue);
        cur.leftRight = left.leftRight;
        if (left.leftLeft > 0) {
            cur.leftLeft = left.leftLeft;
            if (left.leftLeft + left.leftRight == left.right - left.left + 1) {
                if (right.leftRight == 0) {
                    cur.leftLeft += right.leftLeft;
                }
            }
        } else {
            cur.leftRight += right.leftRight;
            cur.leftLeft = right.leftLeft;
        }
        cur.maxValue = Math.max(cur.maxValue, cur.leftRight + cur.leftLeft);

        cur.rightLeft = right.rightLeft;
        if (right.rightRight > 0) {
            cur.rightRight = right.rightRight;
            if (right.rightLeft + right.rightRight == right.right - right.left + 1) {
                if (left.rightLeft == 0) {
                    cur.rightRight += left.rightRight;
                }
            }
        } else {
            cur.rightLeft += left.rightLeft;
            cur.rightRight = left.rightRight;
        }
        cur.maxValue = Math.max(cur.maxValue, cur.rightRight + cur.rightLeft);
        if (left.rightLeft == 0) {
            cur.maxValue = Math.max(cur.maxValue, left.rightRight + right.leftRight + right.leftLeft);
        }
        if (right.leftRight == 0) {
            cur.maxValue = Math.max(cur.maxValue, right.leftLeft + left.rightLeft + left.rightRight);
        }
        //cur.leftBracket = left.leftBracket + right.leftBracket - pair;
        //cur.rightBracket = right.rightBracket + left.rightBracket - pair;
    }

    void push_down(int root) {
        if (nodes[root].lazy_value > 0) {
            op(root * 2, nodes[root].lazy_value);
            op(root * 2 + 1, nodes[root].lazy_value);
            nodes[root].lazy_value = 0;
        }
    }

    void flip(int root) {
        rev[root] ^= 1;
        Node tmp = nodes[root];
        nodes[root] = rNodes[root];
        rNodes[root] = tmp;
    }

    void op(int root, int value) {
        nodes[root].val += value;
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
            //flip(root);
            op(root, value);
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
            pull_up(rNodes[root], rNodes[leftChild], rNodes[rightChild]);
            //System.out.println(String.format("exit node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));

            //System.out.println(String.format("after node [%d,%d] max =%d  add %d", cur.left, cur.right, cur.val, value));
        }
    }

    public int query(int queryLeft, int queryRight) {
        Node qNode = query(1, 1, n, queryLeft, queryRight);
        return qNode.val;
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
        int val;
        int highestRow;
        int leftBracket;
        int rightBracket;
        int leftLeft;
        int leftRight;
        int rightLeft;
        int rightRight;
        int maxValue;
        int lazy_value;
    }
}
