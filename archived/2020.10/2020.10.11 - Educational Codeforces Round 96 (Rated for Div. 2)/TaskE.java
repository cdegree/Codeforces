package current;

import algorithms.ArrayUtils;
import algorithms.BinaryIndexedTree;
import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.nextLine().toCharArray();

        char[] revsersed = Arrays.copyOf(s, s.length);
        ArrayUtils.reverse(revsersed, 0, n);
        long res = 0;
        LinkedList<Integer>[] posOf = new LinkedList[26];
        for (int i = 0; i < 26; ++i) posOf[i] = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            posOf[s[i] - 'a'].add(i);
        }
        BinaryIndexedTree less = new BinaryIndexedTree(n + 1);
        BinaryIndexedTree more = new BinaryIndexedTree(n + 1);
        for (int i = 0; i < n / 2; ++i) {
            int j = n - i - 1;
            int leftNeed = revsersed[i] - 'a';
            int rightNeed = revsersed[j] - 'a';
            int leftNearbyPos = posOf[leftNeed].pollFirst();
            ++leftNearbyPos;
            int rightNearbyPos = posOf[rightNeed].pollLast();
            ++rightNearbyPos;
            long leftOff = leftNearbyPos - less.sum(leftNearbyPos) - 1;
            long rightOff = n - rightNearbyPos - more.sum(n - rightNearbyPos + 1);
            res += leftOff + rightOff;
            if (leftNearbyPos > rightNearbyPos) --res;
            less.update(leftNearbyPos, 1);
            less.update(rightNearbyPos, 1);
            more.update(n - rightNearbyPos + 1, 1);
            more.update(n - leftNearbyPos + 1, 1);
            //Utils.pf("res = %d i = %d  %d %d    %d %d", res, i, leftOff, rightOff, leftNearbyPos, rightNearbyPos);
        }

        out.println(res);
    }

}
