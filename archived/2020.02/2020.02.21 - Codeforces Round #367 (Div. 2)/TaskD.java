package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int q = in.nextInt();
        Trie trie = new Trie();
        trie.add(0);
        while (q-- > 0) {
            char ch = in.next().toCharArray()[0];
            int x = in.nextInt();
            if (ch == '+') {
                trie.add(x);
            } else if (ch == '-') {
                trie.remove(x);
            } else {
                int result = trie.query(x);
                out.println(result);
            }
        }
    }

    class Trie {
        Node root = null;
        public Trie() {
            root = new Node();
        }

        public void add(int x) {
            Node cur = root;
            for (int i = 30; i >= 0; --i) {
                int id = (x >> i) & 1;
                if (cur.next[id] == null) {
                    cur.next[id] = new Node();
                }
                cur = cur.next[id];
                cur.cnt++;
            }
        }

        public void remove(int x) {
            Node cur = root;
            for (int i = 30; i >= 0; --i) {
                int id = (x >> i) & 1;
                if (cur.next[id] == null) {
                    cur.next[id] = new Node();
                }
                cur = cur.next[id];
                cur.cnt--;
            }
        }

        public int query(int x) {
            Node cur = root;
            int result = 0;
            for (int i = 30; i >= 0; --i) {
                int id = (x >> i) & 1;
                int rid = id ^ 1;
                if (cur.next[rid] != null && cur.next[rid].cnt > 0) {
                    cur = cur.next[rid];
                    result |= 1 << i;
                } else {
                    cur = cur.next[id];
                }
            }
            return result;
        }

        class Node {
            Node[] next = new Node[2];
            int cnt = 0;

            public Node() {
                Arrays.fill(next, null);
            }
        }
    }
}
