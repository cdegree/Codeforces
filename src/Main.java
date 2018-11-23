import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.Vector;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Collections;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        long res;
        int mod = 1000000007;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(0, n);
            final int N = 1000006;

            int[] cnt = new int[N];
            res = 0;
            cnt[0] = 1;
            for (int i = 0; i < n; ++i) {
                int m = a[i];
                Vector<Integer> r = MathUtil.getDivisors(m);
                Collections.sort(r);
                Collections.reverse(r);
                for (int k : r) {
                    cnt[k] = (cnt[k] + cnt[k - 1]) % mod;
                }
            }
            for (int i = 1; i <= n; ++i) {
                res += cnt[i];
            }
            res %= mod;
            out.println(res);
        }

    }

    static class MathUtil {
        public static Vector<Integer> getDivisors(int n) {
            Vector<Integer> r = new Vector<>();
            for (int i = 1; i * i <= n; ++i) {
                if (n % i == 0) {
                    r.add(i);
                    if (n / i != i) {
                        r.add((n / i));
                    }
                }
            }
            return r;
        }

    }

    static class InputReader {
        final private int BUFFER_SIZE = 1 << 10;
        private DataInputStream in;
        private byte[] buffer;
        private int bufferPointer;
        private int bytesRead;

        public InputReader(InputStream inputStream) {
            this.in = new DataInputStream(inputStream);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int[] readIntArray(int start, int n) {
            int ret[] = new int[start + n];
            for (int i = start; i < n; ++i) {
                ret[i] = this.nextInt();
            }
            return ret;
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
}

