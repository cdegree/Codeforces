package current;

import algorithms.DisjointSet;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskF {
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
        if(dq!=null) {
            Node root = dq.head;
            while (root != null) {
                out.print(root.value + " ");
                root = root.next;
            }
        }
        out.println();
    }

}
