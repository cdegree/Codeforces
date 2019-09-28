import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.AbstractCollection;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            char[] s = in.next().toCharArray();
            n = s.length;
            if (s.length == 1) {
                out.println(":(");
                return;
            }
            if (s[0] == ')' || s[n - 1] == '(' || n % 2 == 1) {
                out.println(":(");
                return;
            }
            s[0] = '(';
            s[n - 1] = ')';

            LinkedList<Integer> stack = new LinkedList<>();
            LinkedList<Integer> left = new LinkedList<>();
            int q = 0;
            for (int i = 1; i < n - 1; ++i) {
                if (s[i] == '(') {
                    left.addLast(i);
                } else if (s[i] == ')') {
                    if (left.isEmpty()) {
                        if (stack.isEmpty()) {
                            out.println(":(");
                            return;
                        } else {
                            s[stack.getLast()] = '(';
                            stack.removeLast();
                        }
                    } else {
                        left.removeLast();
//                    if (stack.isEmpty()) {
//                        left.removeLast();
//                    } else {
//                        if (stack.getLast() > left.getLast()) {
//                            s[stack.getLast()] = '(';
//                            stack.removeLast();
//                        } else {
//                            left.removeLast();
//                        }
//                    }
                    }
                } else {
                    if (left.isEmpty()) {
                        left.addLast(i);
                        s[i] = '(';
                    } else {
                        stack.addLast(i);
                    }
                }
            }
//        out.println(s);
            if (left.isEmpty() && stack.isEmpty()) {
                out.println(s);
            } else {
                while (!stack.isEmpty()) {
                    if (!left.isEmpty()) {
                        int id = stack.getFirst();
                        stack.removeFirst();
                        int left_id = left.getFirst();
                        left.removeFirst();
                        if (left_id < id) {
                            s[id] = ')';
                        } else {
                            out.println(":(");
                            return;
                        }
                    } else {
                        break;
                    }
                }
                if (!left.isEmpty()) {
                    out.println(":(");
                    return;
                }
//            out.println(stack.size());
                if (stack.size() % 2 == 1) {
                    out.println(":(");
                    return;
                }
                while (!stack.isEmpty()) {
                    int id = stack.getFirst();
//                out.println(id);
                    stack.removeFirst();
                    s[id] = '(';
                    id = stack.getFirst();
                    stack.removeFirst();
                    s[id] = ')';
                }
                out.println(s);
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
}

