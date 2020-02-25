package fastio;

import java.io.*;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class InputReader {

    private BufferedReader reader;
    private StringTokenizer tokenizer = new StringTokenizer("");

    public InputReader(InputStream inputStream) {
        this.reader = new BufferedReader(
                new InputStreamReader(inputStream));
    }

    public String next()  {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }
    public String nextLine(){
        String ret="";
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

    public double nextDouble()  {
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

//    final private int BUFFER_SIZE = 1 << 10;
//    final private int LINE_SIZE = 1 << 20;
//    private DataInputStream in;
//    private byte[] buffer;
//    private int bufferPointer, bytesRead;
//
//    public InputReader(InputStream inputStream) {
//        this.in = new DataInputStream(inputStream);
//        buffer = new byte[BUFFER_SIZE];
//        bufferPointer = bytesRead = 0;
//    }
//
//    public int[] readIntArray(int n) {
//        int ret[] = new int[n];
//        for (int i = 0; i < n; ++i) {
//            ret[i] = this.nextInt();
//        }
//        return ret;
//    }
//
//    public int[] readIntArray(int start, int n) {
//        int ret[] = new int[start + n];
//        for (int i = start; i < start + n; ++i) {
//            ret[i] = this.nextInt();
//        }
//        return ret;
//    }
//
//    public int[] readLongArray(int n) {
//        int ret[] = new int[n];
//        for (int i = 0; i < n; ++i) {
//            ret[i] = this.nextInt();
//        }
//        return ret;
//    }
//
//    public String next() {
//        byte[] buf = new byte[LINE_SIZE]; // line length
//        int cnt = 0, c;
//        c = read();
//        while (c == ' ' || c == '\n' || c == '\r')
//            c = read();
//        do {
//            if (c == ' ' || c == '\n' || c == '\r')
//                break;
//            buf[cnt++] = (byte) c;
//        } while ((c = read()) != -1);
//        return new String(buf, 0, cnt);
//    }
//
//    public String nextLine() {
//        byte[] buf = new byte[LINE_SIZE]; // line length
//        int cnt = 0, c;
//        c = read();
//        while (c == '\n' || c == '\r')
//            c = read();
//        do {
//            if (c == '\n' || c == '\r')
//                break;
//            buf[cnt++] = (byte) c;
//        } while ((c = read()) != -1);
//        return new String(buf, 0, cnt);
//    }
//
//    public int nextInt() {
//        int ret = 0;
//        byte c = read();
//        while (c <= ' ')
//            c = read();
//        boolean neg = (c == '-');
//        if (neg)
//            c = read();
//        do {
//            ret = ret * 10 + c - '0';
//        } while ((c = read()) >= '0' && c <= '9');
//
//        if (neg)
//            return -ret;
//        return ret;
//    }
//
//    public long nextLong() {
//        long ret = 0;
//        byte c = read();
//        while (c <= ' ')
//            c = read();
//        boolean neg = (c == '-');
//        if (neg)
//            c = read();
//        do {
//            ret = ret * 10 + c - '0';
//        }
//        while ((c = read()) >= '0' && c <= '9');
//        if (neg)
//            return -ret;
//        return ret;
//    }
//
//    public double nextDouble() {
//        double ret = 0, div = 1;
//        byte c = read();
//        while (c <= ' ')
//            c = read();
//        boolean neg = (c == '-');
//        if (neg)
//            c = read();
//        do {
//            ret = ret * 10 + c - '0';
//        }
//        while ((c = read()) >= '0' && c <= '9');
//
//        if (c == '.') {
//            while ((c = read()) >= '0' && c <= '9') {
//                ret += (c - '0') / (div *= 10);
//            }
//        }
//        if (neg)
//            return -ret;
//        return ret;
//    }
//
//    private void fillBuffer() {
//        try {
//            bytesRead = in.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        } catch (IOException e) {
//            throw new InputMismatchException();
//        }
//    }
//
//    private byte read() {
//        if (bufferPointer == bytesRead)
//            fillBuffer();
//        return buffer[bufferPointer++];
//    }


}
