package algorithms;

public class BinaryIndexedTree {
    int n;
    int[] bit;
    public int count;

    public BinaryIndexedTree(int n) {
        this.n = n;
        bit = new int[n + 1];
    }


    /* Updates element at index 'i' of BIT. */
    public void Update(int i, int add) {
        while (i > 0 && i < n) {
            bit[i] += add;
            i = i + (i & (-i));
        }
    }

    /* Returns cumulative sum of all elements of
       fenwick tree/BIT from start upto and
       including element at index 'i'. */
    public int Sum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += bit[i];
            i = i - (i & (-i));
        }

        return ans;
    }

    // Returns lower bound for k in BIT.
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

    // Insert x into BIT. We masically increment
// rank of all elements greater than x.
    public void InsertElement(int x) {
        Update(x, 1);
        ++count;
    }

    // Delete x from BIT. We masically decreases
// rank of all elements greater than x.
    public void DeleteElement(int x) {
        Update(x, -1);
        --count;
    }

    // Returns rank of element. We basically
// return sum of elements from start to
// index x.
    int FindRank(int x) {
        return Sum(x);
    }
    public static void main(String[] args){

        BinaryIndexedTree bit = new BinaryIndexedTree(20000);

        bit.InsertElement(20);
        bit.InsertElement(50);
        bit.InsertElement(30);
        bit.InsertElement(40);

        System.out.println("2nd Smallest element is "+ bit.FindKthSmallest(2));

        System.out.println("Rank of 40 is  "+ bit.FindRank(40));

        bit.DeleteElement(40);
        System.out.println("Rank of 50 is "+ bit.FindRank(50));

    }
}
