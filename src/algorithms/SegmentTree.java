package algorithms;

public class SegmentTree {
    long[] max;
    long[] sum;
    int n;

    public SegmentTree(int n) {
        sum = new long[4 * n];
        max = new long[4 * n];
        this.n = n;
    }

    public void build(int[] val) {
        build(1, 1, n, val);
    }

    private void build(int rt, int nodeLeft, int nodeRight, int[] val) {
        if (nodeLeft == nodeRight) {
            sum[rt] = max[rt] = val[nodeLeft];
            return;
        }
        int mid = (nodeLeft + nodeRight) >> 1;
        build(rt * 2, nodeLeft, mid, val);
        build(rt * 2 + 1, mid + 1, nodeRight, val);
        pull(rt);
    }

    public void updateSet(int pos, int val) {
        updateSet(1, 1, n, pos, val);
    }

    private void updateSet(int rt, int nodeLeft, int nodeRight, int pos, int val) {
        if (nodeLeft == pos && nodeRight == pos) {
            sum[rt] = max[rt] = val;
            return;
        }
        int mid = (nodeLeft + nodeRight) >> 1;
        if (pos <= mid) {
            updateSet(rt * 2, nodeLeft, mid, pos, val);
        } else {
            updateSet(rt * 2 + 1, mid + 1, nodeRight, pos, val);
        }
        pull(rt);
    }

    public void updateMod(int left, int right, int val) {
        updateMod(1, 1, n, left, right, val);
    }

    private void updateMod(int rt, int nodeLeft, int nodeRight, int left, int right, int val) {
        if (max[rt] < val) {
            return;
        }
        if (nodeLeft == nodeRight) {
            max[rt] = sum[rt] = max[rt] % val;
            return;
        }
        int mid = (nodeLeft + nodeRight) >> 1;
        if (left <= mid) {
            updateMod(rt * 2, nodeLeft, mid, left, right, val);
        }
        if (mid < right) {
            updateMod(rt * 2 + 1, mid + 1, nodeRight, left, right, val);
        }
        pull(rt);
    }

    public long querySum(int left, int right) {
        return querySum(1, 1, n, left, right);
    }

    private long querySum(int rt, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
        if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
            return sum[rt];
        }
        int mid = (nodeLeft + nodeRight) >> 1;
        long ans = 0;
        if (queryLeft <= mid) {
            ans += querySum(rt * 2, nodeLeft, mid, queryLeft, queryRight);
        }
        if (mid < queryRight) {
            ans += querySum(rt * 2 + 1, mid + 1, nodeRight, queryLeft, queryRight);
        }
        return ans;
    }

    void pull(int rt) {
        max[rt] = Math.max(max[rt * 2], max[rt * 2 + 1]);
        sum[rt] = sum[rt * 2] + sum[rt * 2 + 1];
    }

    public static void main(String[] args) {

        int n = 10;

        SegmentTree st = new SegmentTree(n);
        int[] a = {1,1,1,1,1,1,1,1,1,1,1};
        st.build(a);

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
}


