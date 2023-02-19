import java.io.*;
import java.util.*;

/**
 * solution is written by hand
 *
 * @author xwchen
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class SegmentTree {
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
    }

    static class Task {
        int[] a;
        int[] b;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = in.nextIntArray1(n);
            SegmentTree st = new SegmentTree(n);
            st.build(a);
            for (int i = 0; i < m; i++) {
                int tp = in.nextInt();
                if (tp == 1) {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    long sum = st.querySum(l, r);
                    out.println(sum);
                } else if (tp == 2) {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    int x = in.nextInt();
                    st.updateMod(l, r, x);
                } else {
                    int k = in.nextInt();
                    int x = in.nextInt();
                    st.updateSet(k, x);
                }
            }
        }
    }


    static class Complex {
        double real;
        double image;

        public Complex(double real, double image) {
            this.real = real;
            this.image = image;
        }

        public double getReal() {
            return real;
        }

        public double getImage() {
            return image;
        }

        public Complex plus(Complex other) {
            return new Complex(real + other.getReal(), image + other.getImage());
        }

        public Complex subtract(Complex other) {
            return new Complex(real - other.getReal(), image - other.getImage());
        }

        public Complex div(double v) {
            return new Complex(real / v, image / v);
        }

        public Complex multiply(Complex other) {
            double nReal = real * other.getReal() - image * other.getImage();
            double nImage = real * other.getImage() + image * other.getReal();
            return new Complex(nReal, nImage);
        }

        @Override
        public String toString() {
            return "Complex{" +
                    "real=" + real +
                    ", image=" + image +
                    '}';
        }
    }


    static class InputReader {

        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");

        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        public String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public char nextChar() {
            try {
                int c = reader.read();
                return (char) c;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String nextLine() {
            String ret = "";
            try {
                ret = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public int[] nextIntArray1(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++)
                a[i] = nextInt();
            return a;
        }


        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public long[] nextLongArray1(int n) {
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++)
                a[i] = nextLong();
            return a;
        }
    }
}

