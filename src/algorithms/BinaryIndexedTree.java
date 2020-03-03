package algorithms;

import java.util.Arrays;

public class BinaryIndexedTree {
    public int count;
    int n;
    long[] bit;

    public BinaryIndexedTree(int n) {
        this.n = n;
        bit = new long[n + 1];
    }

    public static void main(String[] args) {

        BinaryIndexedTree bit = new BinaryIndexedTree(20000);

        bit.insertElement(20);
        bit.insertElement(50);
        bit.insertElement(30);
        bit.insertElement(40);

        System.out.println("2nd Smallest element is " + bit.findKthSmallest(2));

        System.out.println("Rank of 40 is " + bit.findRank(40));

        bit.deleteElement(40);
        System.out.println("Rank of 50 is " + bit.findRank(50));

    }

    public void reset() {
        Arrays.fill(bit, 0);
    }

    /* Updates element at index 'i' of BIT. */
    public void update(int i, int add) {
        while (i > 0 && i <= n) {
            bit[i] += add;
            i = i + (i & (-i));
        }
    }

    /* Returns cumulative sum of all elements of
       fenwick tree/BIT from start upto and
       including element at index 'i'. */
    public long sum(int i) {
        long ans = 0;
        while (i > 0) {
            ans += bit[i];
            i = i - (i & (-i));
        }
        return ans;
    }

    public long queryRange(int i, int j) {
        return sum(j) - sum(i - 1);
    }

    // Returns lower bound for k in BIT.
    public int findKthSmallest(int k) {
        // Do binary search in BIT[] for given
        // value k.
        int l = 0;
        int h = n;
        while (l < h) {
            int mid = (l + h) / 2;
            if (k <= sum(mid)) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    // Insert x into BIT. We masically increment
    // rank of all elements greater than x.
    public void insertElement(int x) {
        update(x, 1);
        ++count;
    }

    // Delete x from BIT. We masically decreases
    // rank of all elements greater than x.
    public void deleteElement(int x) {
        update(x, -1);
        --count;
    }

    // Returns rank of element. We basically
    // return sum of elements from start to
    // index x.
    public long findRank(int x) {
        return sum(x);
    }
}
