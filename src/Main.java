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
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        public void solve(int testNumber, InputReader in, PrintWriter out) {

            int n = in.nextInt();
            DisjointSet ds = new DisjointSet(n);

            Dq[] cells = new Dq[n + 1];
            for (int i = 1; i <= n; ++i) {
                cells[i] = new Dq(new Node(i));
            }
            int last = 1;
            for (int i = 1; i < n; ++i) {
                int a = in.nextInt();
                int b = in.nextInt();
                int xa = ds.find(a);
                int xb = ds.find(b);
                if (xa != xb) {
                    ds.union(xa, xb);
                    cells[xa].add(cells[xb]);
                    last = xa;
                }
            }
            output(cells[last], out);
        }

        void output(Dq dq, PrintWriter out) {
            if (dq != null) {
                Node root = dq.head;
                while (root != null) {
                    out.print(root.value + " ");
                    root = root.next;
                }
            }
            out.println();
        }

        public class Node {
            int value;
            Node next;
            Node pre;

            Node(int value) {
                this.value = value;
                next = null;
                pre = null;
            }

        }

        public class Dq {
            Node head;
            Node tail;

            public Dq(Node node) {
                head = node;
                tail = node;
            }

            public void add(Dq dq) {
                if (dq != null) {
                    tail.next = dq.head;
                    dq.head.pre = tail;
                    tail = dq.tail;
                }
            }

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

    static class DisjointSet {
        int[] f;

        public DisjointSet(int n) {
            f = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                f[i] = i;
            }
        }

        public int find(int x) {
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public void union(int x, int y) {
            f[find(y)] = find(x);
        }

    }
}

