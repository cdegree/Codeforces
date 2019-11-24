import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.Arrays;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Comparator;
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
        TaskD1 solver = new TaskD1();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD1 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = in.readIntArray(1, n);
            Item[] items = new Item[n + 1];
            for (int i = 1; i <= n; ++i) {
                Item item = new Item();
                item.idx = i;
                item.value = a[i];
                items[i] = item;
            }
            Arrays.sort(items, 1, n + 1, new Comparator<Item>() {

                public int compare(Item o1, Item o2) {
                    if (o1.value != o2.value) {
                        return Integer.compare(o2.value, o1.value);
                    } else {
                        return Integer.compare(o1.idx, o2.idx);
                    }
                }
            });
            int m = in.nextInt();
            Quest[] quests = new Quest[m];

            for (int i = 0; i < m; ++i) {
                int k = in.nextInt();
                int p = in.nextInt();
                quests[i] = new Quest(k, p, i);
            }
            Arrays.sort(quests, new Comparator<Quest>() {

                public int compare(Quest o1, Quest o2) {
                    return Integer.compare(o1.k, o2.k);
                }
            });
            BinaryIndexedTree bst = new BinaryIndexedTree(n + 1);
            int idx = 1;
            for (int i = 0; i < m; ++i) {
                while (bst.count < quests[i].k) {
                    bst.InsertElement(items[idx++].idx);
                }
                int kSmall = bst.FindKthSmallest(quests[i].p);
                quests[i].result = a[kSmall];
            }
            Arrays.sort(quests, new Comparator<Quest>() {

                public int compare(Quest o1, Quest o2) {
                    return Integer.compare(o1.idx, o2.idx);
                }
            });
            for (int i = 0; i < m; ++i) {
                out.println(quests[i].result);
            }
        }

        public class Item {
            int idx;
            int value;

        }

        public class Quest {
            int k;
            int p;
            int idx;
            int result;

            public Quest(int k, int p, int idx) {
                this.k = k;
                this.p = p;
                this.idx = idx;
            }

        }

    }

    static class BinaryIndexedTree {
        int n;
        int[] bit;
        public int count;

        public BinaryIndexedTree(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        public void Update(int i, int add) {
            while (i > 0 && i < n) {
                bit[i] += add;
                i = i + (i & (-i));
            }
        }

        public int Sum(int i) {
            int ans = 0;
            while (i > 0) {
                ans += bit[i];
                i = i - (i & (-i));
            }

            return ans;
        }

        public int FindKthSmallest(int k) {
            // Do binary search in BIT[] for given
            // value k.
            int l = 0;
            int h = n;
            while (l < h) {
                int mid = (l + h) / 2;
                if (k <= Sum(mid)) {
                    h = mid;
                } else {
                    l = mid + 1;
                }
            }

            return l;
        }

        public void InsertElement(int x) {
            Update(x, 1);
            ++count;
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
            for (int i = start; i < start + n; ++i) {
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

