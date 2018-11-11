import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author xwchen
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int q = in.nextInt();
            String s = in.next();
            int m = 26;
            int[][] cnt = new int[m][n + 1];
            for (int i = 0; i < s.length(); ++i) {
                cnt[s.charAt(i) - 'a'][i + 1] = 1;
            }
            SegmentTree[] st = new SegmentTree[m];
            for (int i = 0; i < m; ++i) {
                st[i] = new SegmentTree(n);
                st[i].build(1, cnt[i]);
            }
            int[] tcnt = new int[m];
            while (q-- > 0) {
                int from = in.nextInt();
                int to = in.nextInt();
                int k = in.nextInt();
                for (int i = 0; i < m; ++i) {
                    tcnt[i] = st[i].query(1, from, to);
//                if (tcnt[i] > 0) out.println(String.format("[%d,%d]cnt[%d] = %d", from, to, i, tcnt[i]));
                }
                if (k == 1) {
                    int pos = from;
                    for (int i = 0; i < m; ++i) {
                        if (tcnt[i] > 0) {

                            st[i].update(1, from, to, 0);
                            st[i].update(1, pos, pos + tcnt[i] - 1, 1);
                            pos = pos + tcnt[i];
                        }
                    }
                } else {
                    int pos = to;
                    for (int i = 0; i < m; ++i) {
                        if (tcnt[i] > 0) {
                            st[i].update(1, from, to, 0);
                            st[i].update(1, pos - tcnt[i] + 1, pos, 1);
                            pos = pos - tcnt[i];
                        }
                    }
                }
            }
            for (int i = 1; i <= n; ++i) {
                int id = -1;
                for (int j = 0; j < m; ++j) {
                    if (st[j].query(1, i, i) > 0) {
                        id = j;
                        break;
                    }
                }
                if (id != -1) {
                    char c = (char) (id + 'a');
                    out.print(c);
                } else {
                    out.print("What??????");
                }
            }
        }

    }

    static class InputReader {
        final private int BUFFER_SIZE = 1 << 10;
        final private int LINE_SIZE = 1 << 20;
        private DataInputStream in;
        private byte[] buffer;
        private int bufferPointer;
        private int bytesRead;

        public InputReader(InputStream inputStream) {
            this.in = new DataInputStream(inputStream);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String next() {
            byte[] buf = new byte[LINE_SIZE]; // line length
            int cnt = 0, c;
            c = read();
            while (c == ' ' || c == '\n' || c == '\r')
                c = read();
            do {
                if (c == ' ' || c == '\n' || c == '\r')
                    break;
                buf[cnt++] = (byte) c;
            } while ((c = read()) != -1);
            return new String(buf, 0, cnt);
        }

        public int nextInt() {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() {
            try {
                bytesRead = in.read(buffer, bufferPointer = 0, BUFFER_SIZE);
                if (bytesRead == -1)
                    buffer[0] = -1;
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }

        private byte read() {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }

    static class SegmentTree {
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
}

