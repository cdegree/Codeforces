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

    public void add(char[] ch, int st) {
        Node cur = root;
        int cnt = 0;
        for (int i = st; i < ch.length; ++i) {
            int id = ch[i] - 'a';
            if (cur.next[id] == null) {
                cur.next[id] = new Node();
            }
            cur = cur.next[id];
            if (!isGood[id]) {
                ++cnt;
            }
            if (cnt <= k) {
                if (!cur.marked) {
                    ++result;
                }
                cur.marked = true;
            } else {
                return;
            }
        }
    }

    class Node {
        Node[] next = new Node[26];
        boolean marked = false;
        public Node() {
            Arrays.fill(next, null);
        }
    }
}
