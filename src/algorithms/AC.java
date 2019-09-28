package algorithms;

import java.util.Arrays;
import java.util.LinkedList;

public class AC {
    public AC() {
        init();
    }

    class Node {
        public Node[] next = new Node[26];
        public boolean end = false;
        public Node fail = null;

        public Node() {
            Arrays.fill(next, null);
        }

        public int val;
    }

    Node root;
    int cnt = 0;

    public void init() {
        root = new Node();
        root.fail = root;
    }

    public void add(char[] s) {
        Node now = root;
        for (int i = 0; i < s.length; ++i) {
            int k = s[i] - 'a';
            if (now.next[k] == null) {
                now.next[k] = new Node();
                ++cnt;
                now.next[k].val = cnt;
            }
            now = now.next[k];
        }
        now.end = true;
        System.out.println(String.format("C %d -> %s", now.val, now.end));
    }

    public void construct() {
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            Node now = q.removeFirst();
            now.end = now.end || now.fail.end;
            for (int i = 0; i < 26; ++i) {
                Node next = now.next[i];
                if (next != null) {
                    q.addLast(next);
                    if (now == root) {
                        next.fail = root;
                    } else {
                        Node previousFail = now.fail;
                        while (previousFail.next[i] == null && previousFail != root) {
                            previousFail = previousFail.fail;
                        }
                        next.fail = previousFail.next[i] == null ? root : previousFail.next[i];
                    }
                    System.out.println(String.format("%d fail to %d", next.val, next.fail.val));
                }
            }
        }
    }
}
