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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int index[][] = new int[m][n + 1];
            int std[] = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                std[i] = in.nextInt();
            }
            for (int j = 1; j < m; ++j) {
                for (int i = 0; i < n; ++i) {
                    int id = in.nextInt();
                    index[j][id] = i;
                }
            }
            long sum = n;
            long last = 0;
            for (int i = 1; i < n; ++i) {
                boolean OK = true;
                for (int j = 1; j < m; ++j) {
                    if (index[j][std[i]] + 1 != index[j][std[i + 1]]) {
                        OK = false;
                        break;
                    }
                }
                if (OK) {
                    last++;
                } else if (last > 0) {
                    sum += last * (last + 1) / 2;
                    last = 0;
                }
            }
            if (last > 0) {
                sum += 1L * last * (last + 1) / 2;
            }

            out.println(sum);
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

