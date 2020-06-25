package algorithms;

import java.util.Arrays;
import java.util.LinkedList;

public class Trie {
    public int result = 0;
    public int k = 0;
    public boolean[] isGood = new boolean[26];
    Node root = null;

    public Trie() {
        root = new Node();
    }

    public boolean dfs(char[] t, int pos, Node cur, boolean differed) {
        if (pos < t.length) {
            char c = t[pos];
            int id = c - 'a';
            if (cur.next[id] != null) {
                if (dfs(t, pos + 1, cur.next[id], differed)) {
                    return true;
                }
            }
            if (!differed) {
                for (int k = 0; k < 3; ++k) {
                    if (k != id && cur.next[k] != null) {
                        if (dfs(t, pos + 1, cur.next[k], true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else if (pos == t.length) {
            if (cur.marked && differed) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isExist(char[] t) {
        return dfs(t, 0, root, false);
    }

    public void add(char[] ch) {
        add(ch, 0);
    }


    public int getBest(Node cur, int depth) {
        int cnt = 0;
        int id = -1;
        for (int i = 0; i < 2; ++i) {
            if (cur.next[i] != null) {
                id = i;
                ++cnt;
            }
        }
        if (cnt == 0) {
            return 0;
        } else if (cnt == 1) {
            return getBest(cur.next[id], depth - 1);
        } else {
            int left = getBest(cur.next[0], depth - 1);
            int right = getBest(cur.next[1], depth - 1);
            return (1 << depth) | Math.min(left, right);
        }
    }

    public int getMin() {
        return getBest(root, 29);
    }

    public void add(int x) {
        Node cur = root;
        for (int i = 29; i >= 0; --i) {
            int id = BitUtil.testBit(x, i) ? 1 : 0;
            if (cur.next[id] == null) {
                cur.next[id] = new Node();
            }
            cur = cur.next[id];
        }
    }


    public void add(char[] ch, int st) {
        Node cur = root;
        int cnt = 0;
        for (int i = st; i < ch.length; ++i) {
            int id = ch[i] - 'a';
            if (cur.next[id] == null) {
                cur.next[id] = new Node();
            }
            cur = cur.next[id];
        }
        cur.marked = true;
    }

    class Node {
        Node[] next = new Node[3];
        boolean marked = false;
        int best = 0;

        public Node() {
            Arrays.fill(next, null);
        }
    }
}
