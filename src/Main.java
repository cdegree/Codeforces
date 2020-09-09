import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
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
            int q = in.nextInt();
            Hashtable<Integer, Integer> hashtable = new Hashtable<>();
            int[] a = in.nextIntArray1(n);
            int[] b = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                b[i] = i - a[i];
            }
            int[] prefix_Zero = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                prefix_Zero[i] = prefix_Zero[i - 1] + ((b[i] == 0) ? 1 : 0);
            }
            int[] needToHave = new int[n + 1];
            int[] coversIfHaveZeros = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                coversIfHaveZeros[i] = i;
            }
            SegmentTree st = new SegmentTree(n);
            st.build(coversIfHaveZeros);
            for (int i = 1; i <= n; ++i) {
                if (b[i] > 0) {
                    int rawZeros = prefix_Zero[i];
                    if (rawZeros < 0) {
                        needToHave[i] = 1 << 30;
                    } else {
                        int left = 1;
                        int right = rawZeros;
                        int min = -1;
                        while (left <= right) {
                            int mid = (left + right) / 2;
                            if (st.query(mid, mid) >= b[i]) {
                                min = mid;
                                right = mid - 1;
                            } else {
                                left = mid + 1;
                            }
                        }
                        if (min > -1) {
                            needToHave[i] = min;
                            st.update(min, rawZeros, 1);
                        } else {
                            needToHave[i] = 1 << 30;
                        }
                    }

                } else if (b[i] == 0) {
                    needToHave[i] = 0;
                } else {
                    needToHave[i] = 1 << 30;
                }
            }
            int[] cnt = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                if (needToHave[i] > 0 && needToHave[i] <= n) {
                    cnt[needToHave[i]]++;
                }
            }

            while (q-- > 0) {
                int x = in.nextInt();
                int y = in.nextInt();

                int zeros = prefix_Zero[x];

            }
        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");

        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(
                    new InputStreamReader(inputStream));
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public int[] nextIntArray1(int n) {
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++)
                a[i] = nextInt();
            return a;
        }

    }

    static class SegmentTree {
        int n;
        Node[] nodes = null;
        Node[] rNodes = null;
        int[] a = null;
        int[] rev = null;

        public SegmentTree(int n) {
            int m = n * 4;
            this.n = n;
            nodes = new Node[m];
            rNodes = new Node[m];
            rev = new int[m];
            for (int i = 0; i < m; ++i) {
                nodes[i] = new Node();
                rNodes[i] = new Node();
            }
        }

        public void build(int[] a) {
            this.a = a;
            build(1, 1, n, a);
        }

        public void build(int root, int nodeLeft, int nodeRight, int[] a) {
            nodes[root].left = nodeLeft;
            nodes[root].right = nodeRight;
            rNodes[root].left = nodeLeft;
            rNodes[root].right = nodeRight;
            nodes[root].lazy_value = rNodes[root].lazy_value = 0;
//      System.out.println(String.format("node[%d] [%d,%d]", cur, left[cur], right[cur]));
            if (nodeLeft == nodeRight) {
                nodes[root].val = a[nodeLeft];
                //System.out.println(" L:"+cur.left);
//            if (a[nodeLeft - 1] == '<') {
//                rNodes[root].leftRight = nodes[root].leftLeft = 1;
//                rNodes[root].leftLeft = nodes[root].leftRight = 0;
//                rNodes[root].rightRight = nodes[root].rightLeft = 1;
//                rNodes[root].rightLeft = nodes[root].rightRight = 0;
//                rNodes[root].maxValue = nodes[root].maxValue = 1;
//            } else {
//                rNodes[root].leftRight = nodes[root].leftLeft = 0;
//                rNodes[root].leftLeft = nodes[root].leftRight = 1;
//                rNodes[root].rightRight = nodes[root].rightLeft = 0;
//                rNodes[root].rightLeft = nodes[root].rightRight = 1;
//                rNodes[root].maxValue = nodes[root].maxValue = 1;
//            }
//          System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));
            } else {
                int leftChild = root * 2;
                int rightChild = root * 2 + 1;
                int mid = (nodeLeft + nodeRight) / 2;
                build(leftChild, nodeLeft, mid, a);
                build(rightChild, mid + 1, nodeRight, a);
                pull_up(nodes[root], nodes[leftChild], nodes[rightChild]);
                pull_up(rNodes[root], rNodes[leftChild], rNodes[rightChild]);
                //System.out.println(String.format("node[%d] [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));
            }
        }

        void pull_up(Node cur, Node left, Node right) {
            //int pair = Math.min(left.rightBracket, right.leftBracket);
            //cur.val = Math.max(left.val, right.val);
            cur.maxValue = Math.max(left.maxValue, right.maxValue);
            cur.leftRight = left.leftRight;
            if (left.leftLeft > 0) {
                cur.leftLeft = left.leftLeft;
                if (left.leftLeft + left.leftRight == left.right - left.left + 1) {
                    if (right.leftRight == 0) {
                        cur.leftLeft += right.leftLeft;
                    }
                }
            } else {
                cur.leftRight += right.leftRight;
                cur.leftLeft = right.leftLeft;
            }
            cur.maxValue = Math.max(cur.maxValue, cur.leftRight + cur.leftLeft);

            cur.rightLeft = right.rightLeft;
            if (right.rightRight > 0) {
                cur.rightRight = right.rightRight;
                if (right.rightLeft + right.rightRight == right.right - right.left + 1) {
                    if (left.rightLeft == 0) {
                        cur.rightRight += left.rightRight;
                    }
                }
            } else {
                cur.rightLeft += left.rightLeft;
                cur.rightRight = left.rightRight;
            }
            cur.maxValue = Math.max(cur.maxValue, cur.rightRight + cur.rightLeft);
            if (left.rightLeft == 0) {
                cur.maxValue = Math.max(cur.maxValue, left.rightRight + right.leftRight + right.leftLeft);
            }
            if (right.leftRight == 0) {
                cur.maxValue = Math.max(cur.maxValue, right.leftLeft + left.rightLeft + left.rightRight);
            }
            //cur.leftBracket = left.leftBracket + right.leftBracket - pair;
            //cur.rightBracket = right.rightBracket + left.rightBracket - pair;
        }

        void push_down(int root) {
            if (nodes[root].lazy_value > 0) {
                op(root * 2, nodes[root].lazy_value);
                op(root * 2 + 1, nodes[root].lazy_value);
                nodes[root].lazy_value = 0;
            }
        }

        void op(int root, int value) {
            nodes[root].val += value;
            nodes[root].lazy_value += value;
        }

        public void update(int queryLeft, int queryRight, int value) {
            //System.out.println(String.format("%d->%d += %d", from, to, value));
            update(1, 1, n, queryLeft, queryRight, value);
        }

        public void update(int root, int nodeLeft, int nodeRight, int queryLeft, int queryRight, int value) {
            //System.out.println(String.format("enter node[%d] range[%d,%d] query[%d,%d]", root, nodeLeft, nodeRight, queryLeft, queryRight));
            //System.out.println(String.format("enter node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));

            if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
                //flip(root);
                op(root, value);
                //System.out.println(String.format("exit node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));
            } else {
                push_down(root);
                int leftChild = root * 2;
                int rightChild = root * 2 + 1;
                int mid = (nodeLeft + nodeRight) / 2;
                if (queryLeft <= mid) {
                    update(leftChild, nodeLeft, mid, queryLeft, queryRight, value);
                }
                if (queryRight >= mid + 1) {
                    update(rightChild, mid + 1, nodeRight, queryLeft, queryRight, value);
                }
                pull_up(nodes[root], nodes[leftChild], nodes[rightChild]);
                pull_up(rNodes[root], rNodes[leftChild], rNodes[rightChild]);
                //System.out.println(String.format("exit node[%d] lazy = %d [%d,%d] leftLeft = %d leftRight = %d rightLeft = %d rightRight = %d %s", root, nodes[root].lazy_value, nodeLeft, nodeRight, nodes[root].leftLeft, nodes[root].leftRight, nodes[root].rightLeft, nodes[root].rightRight, subString(a, nodeLeft, nodeRight + 1)));

                //System.out.println(String.format("after node [%d,%d] max =%d  add %d", cur.left, cur.right, cur.val, value));
            }
        }

        public int query(int queryLeft, int queryRight) {
            Node qNode = query(1, 1, n, queryLeft, queryRight);
            return qNode.val;
        }

        public Node query(int root, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
//        System.out.println(String.format("node[%d] [%d,%d] sum =%d", cur, left[cur], right[cur], sum[cur]));

            if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
                return nodes[root];
            } else {
                push_down(root);
                int leftChild = root * 2;
                int rightChild = root * 2 + 1;
                int mid = (nodeLeft + nodeRight) / 2;
                if (queryRight <= mid) {
                    return query(leftChild, nodeLeft, mid, queryLeft, queryRight);
                }
                if (queryLeft >= mid + 1) {
                    return query(rightChild, mid + 1, nodeRight, queryLeft, queryRight);
                }
                Node ql = query(leftChild, nodeLeft, mid, queryLeft, queryRight);
                Node qr = query(rightChild, mid + 1, nodeRight, queryLeft, queryRight);
                Node qNode = new Node();
                pull_up(qNode, ql, qr);
                return qNode;
            }
        }

        class Node {
            int left;
            int right;
            int val;
            int leftLeft;
            int leftRight;
            int rightLeft;
            int rightRight;
            int maxValue;
            int lazy_value;

        }

    }
}

