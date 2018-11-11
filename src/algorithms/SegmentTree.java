package algorithms;

import java.util.Arrays;

public class SegmentTree {

    public static void main(String[] args) {

        int n = 10;
        SegmentTree st = new SegmentTree(n);

        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        st.build(1, a);

        int from = 1;
        int to = 3;
        int ans = st.query(1, from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));

        from = 8;
        to = 10;
        ans = st.query(1, from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));

        from = 4;
        to = 7;
        ans = st.query(1, from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));

        st.update(1, 10, 10, 1);
        from = 8;
        to = 10;
        ans = st.query(1, from, to);
        System.out.println(String.format("[%d,%d] = %d", from, to, ans));

    }

    int n;
    int[] val;
    int[] sum;
    int[] lazy_value;
    int[] left;
    int[] right;

    public SegmentTree(int n) {
        int m = 4 * n;
        val = new int[m + 1];
        sum = new int[m + 1];
        lazy_value = new int[m + 1];
        left = new int[m + 1];
        right = new int[m + 1];
        left[1] = 1;
        right[1] = n;
    }

    void pull_up(int cur) {
        sum[cur] = sum[cur * 2] + sum[cur * 2 + 1];
    }

    public void build(int cur, int[] a) {

//        System.out.println(String.format("node[%d] [%d,%d]", cur, left[cur], right[cur]));
        if (left[cur] == right[cur]) {
            val[cur] = a[left[cur]];
            sum[cur] = val[cur];
//            System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        } else {
            int lc = cur * 2;
            int rc = cur * 2 + 1;
            int mid = (left[cur] + right[cur]) >> 1;
            left[lc] = left[cur];
            right[lc] = mid;
            left[rc] = mid + 1;
            right[rc] = right[cur];
            build(lc, a);
            build(rc, a);
            pull_up(cur);
        }
        lazy_value[cur] = -1;
    }

    void push_down(int cur) {
        if (lazy_value[cur] != -1) {
            int lc = cur * 2;
            int rc = cur * 2 + 1;
            lazy_value[lc] = lazy_value[rc] = lazy_value[cur];
            sum[lc] = (right[lc] - left[lc] + 1) * lazy_value[cur];
            sum[rc] = (right[rc] - left[rc] + 1) * lazy_value[cur];
//            pf("left", lc);
//            pf("right", rc);
            lazy_value[cur] = -1;
        }
    }

    void pf(String label, int cur) {
        System.out.println(String.format("%s:node[%d] [%d,%d] sum =%d", label, cur, left[cur], right[cur], sum[cur]));
    }

    public void update(int cur, int from, int to, int value) {
//        System.out.println(String.format("before node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        if (from <= left[cur] && right[cur] <= to) {
            sum[cur] = (right[cur] - left[cur] + 1) * value;
            lazy_value[cur] = value;
        } else {
            push_down(cur);
            int lc = cur * 2;
            int rc = cur * 2 + 1;
            if (to <= right[lc]) {
                update(lc, from, to, value);
            } else if (from >= left[rc]) {
                update(rc, from, to, value);
            } else {
                update(lc, from, to, value);
                update(rc, from, to, value);
            }
            pull_up(cur);
//            System.out.println(String.format("after node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        }
    }

    public int query(int cur, int from, int to) {
//        System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
        if (from <= left[cur] && right[cur] <= to) {
            return sum[cur];
        } else {
            int lc = cur * 2;
            int rc = cur * 2 + 1;
            push_down(cur);
            if (to <= right[lc]) {
                return query(lc, from, to);
            } else if (from >= left[rc]) {
                return query(rc, from, to);
            } else {
                int sum_left = query(lc, from, to);
                int sum_right = query(rc, from, to);
                return sum_left + sum_right;
            }
        }
    }
}
