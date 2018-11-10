package fastio;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class InputReader {
    final private int BUFFER_SIZE = 1 << 10;
    final private int LINE_SIZE = 1 << 20;
    private DataInputStream in;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

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

    public String next() {
        byte[] buf = new byte[LINE_SIZE]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == ' ')
                break;
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public String nextLine() {
        byte[] buf = new byte[LINE_SIZE]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n')
                break;
            buf[cnt++] = (byte) c;
        }
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

    public long nextLong() {
        long ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }

    public double nextDouble() {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }
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
