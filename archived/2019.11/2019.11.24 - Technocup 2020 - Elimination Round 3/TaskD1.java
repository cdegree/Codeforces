package current;

import algorithms.BinaryIndexedTree;
import algorithms.BinarySearchTree;
import fastio.InputReader;
import org.omg.CORBA.INTERNAL;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskD1 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(1, n);
        Item[] items = new Item[n + 1];
        for (int i = 1; i <= n; ++i) {
            items[i] = new Item(i, a[i]);
        }
        Arrays.sort(items, 1, n + 1, new Comparator<Item>() {
            @Override
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
            @Override
            public int compare(Quest o1, Quest o2) {
                return Integer.compare(o1.k, o2.k);
            }
        });
        BinaryIndexedTree bit = new BinaryIndexedTree(n + 1);
        int idx = 1;
        for (int i = 0; i < m; ++i) {
            while (bit.count < quests[i].k) {
                bit.InsertElement(items[idx++].idx);
            }
            int kSmall = bit.FindKthSmallest(quests[i].p);
            quests[i].result = a[kSmall];
        }
        Arrays.sort(quests, new Comparator<Quest>() {
            @Override
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

        public Item(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
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