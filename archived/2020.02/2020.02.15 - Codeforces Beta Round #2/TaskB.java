package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        int[][] five = new int[n][n];
        int[][] two = new int[n][n];
        boolean containsZero = false;
        int zeroI = 0, zeroJ = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = in.nextInt();
                if (a[i][j] == 0) {
                    containsZero = true;
                    zeroI = i;
                    zeroJ = j;
                    a[i][j] = 10;
                }
                int[] cnt = get25Dividers(a[i][j]);
                five[i][j] = cnt[0];
                two[i][j] = cnt[1];
            }
        }
        int[][] cntFive = new int[n][n];
        int[][] cntTwo = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                cntFive[i][j] += five[i][j];
                if (j == 0 && i > 0) {
                    cntFive[i][j] += cntFive[i - 1][j];
                } else if (i == 0 && j > 0) {
                    cntFive[i][j] += cntFive[i][j - 1];
                } else if (i > 0 && j > 0) {
                    cntFive[i][j] += Math.min(cntFive[i - 1][j], cntFive[i][j - 1]);
                }
                cntTwo[i][j] += two[i][j];
                if (j == 0 && i > 0) {
                    cntTwo[i][j] += cntTwo[i - 1][j];
                } else if (i == 0 && j > 0) {
                    cntTwo[i][j] += cntTwo[i][j - 1];
                } else if (i > 0 && j > 0) {
                    cntTwo[i][j] += Math.min(cntTwo[i - 1][j], cntTwo[i][j - 1]);
                }
            }
        }
        if (containsZero && Math.min(cntFive[n - 1][n - 1], cntTwo[n - 1][n - 1]) > 0) {
            out.println(1);
            for (int i = 0; i < zeroI; ++i) {
                out.print("D");
            }
            for (int i = 0; i < n-1; ++i) {
                out.print("R");
            }
            for (int i = zeroI; i < n-1; ++i) {
                out.print("D");
            }
        } else {
            if (cntFive[n - 1][n - 1] < cntTwo[n - 1][n - 1]) {
                String ans = getAns(cntFive, n);
                out.println(ans);
            } else {
                String ans = getAns(cntTwo, n);
                out.println(ans);
            }
        }
    }

    String getAns(int[][] cnt, int n) {
        int i = n - 1;
        int j = n - 1;
        StringBuilder sb = new StringBuilder();
        do {
            if (i == 0 && j > 0) {
                sb.append('R');
                --j;
            } else if (j == 0 && i > 0) {
                sb.append('D');
                --i;
            } else if (i > 0 && j > 0) {
                if (cnt[i][j - 1] < cnt[i - 1][j]) {
                    --j;
                    sb.append('R');
                } else {
                    --i;
                    sb.append('D');
                }
            }
        } while (i != 0 || j != 0);
        sb = sb.reverse();
        return cnt[n - 1][n - 1] + "\n" + sb.toString();
    }

    int[] get25Dividers(int x) {
        int[] ret = {0, 0};
        while (x % 5 == 0) {
            x /= 5;
            ret[0]++;
        }
        while (x % 2 == 0) {
            x /= 2;
            ret[1]++;
        }
        return ret;
    }
}
