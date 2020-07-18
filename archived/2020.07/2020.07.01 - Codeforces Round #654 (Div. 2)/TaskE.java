package current;

import fastio.InputReader;

import java.io.PrintWriter;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] equalLess = new int[n + 1 + n];
        int bas = 0;
        for (int x : a) {
            bas = Math.max(x, bas);
        }
        for (int x : a) {
            equalLess[Math.max(0, x - bas + n)]++;
        }
        for (int i = 0; i < n + n; ++i) {
            equalLess[i + 1] += equalLess[i];
        }
        int st = 1;
        for (int i = 0; i < n; ++i) {
            while (equalLess[st + i] - i <= 0) {
                ++st;
            }
        }
        int ed = -1;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = (left + right) / 2;
            int found = 0;
            for (int i = 0; i < n; ++i) {
                if (equalLess[mid + i] - i <= 0) {
                    left = mid + 1;
                    found = 1;
                    break;
                } else if (equalLess[mid + i] - i >= p) {
                    right = mid - 1;
                    found = 2;
                    break;
                }
            }
            if (found == 0) {
                left = mid + 1;
                ed = mid;
            }
        }
        if (ed < st) {
            out.println(0);
        } else {
            out.println(ed - st + 1);
            for (int i = st; i <= ed; ++i) {
                out.print((i + bas - n) + " ");
            }
            out.println();
        }
    }
}
