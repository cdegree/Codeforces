package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] s = new char[n][];
        for (int i = 0; i < n; ++i) {
            s[i] = in.nextLine().toCharArray();
        }
        int[][] sameBricksToLeft = buildLeft(n, m, s);
        int[][] sameBricksToRight = buildRight(n, m, s);
        int[][] sameBricksToUp = buildUp(n, m, s);
        int[][] sameBricksToDown = buildDown(n, m, s);
        //pf(sameBricksToLeft);
        int[][] left = new int[n][m];
        int[][] right = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ++left[i][j];
                if (j > 0 && s[i][j] == s[i][j - 1]) {
                    left[i][j] = Math.min(left[i][j - 1] + 1, Math.min(sameBricksToLeft[i][j], Math.min(sameBricksToUp[i][j], sameBricksToDown[i][j])));
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = m - 1; j >= 0; --j) {
                ++right[i][j];
                if (j + 1 < m && s[i][j] == s[i][j + 1]) {
                    right[i][j] = Math.min(right[i][j + 1] + 1, Math.min(sameBricksToRight[i][j], Math.min(sameBricksToUp[i][j], sameBricksToDown[i][j])));
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                res += Math.min(left[i][j], right[i][j]);
            }
        }
        out.println(res);
    }
    void pf(int[][] a){
        for(int i=0;i<a.length;++i){
            for(int j=0;j<a[i].length;++j){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    int[][] buildLeft(int n, int m, char[][] s) {
        int[][] sameBricks = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ++sameBricks[i][j];
                if (j > 0 && s[i][j] == s[i][j - 1]) {
                    sameBricks[i][j] += sameBricks[i][j - 1];
                }
            }
        }
        return sameBricks;
    }

    int[][] buildRight(int n, int m, char[][] s) {
        int[][] sameBricks = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = m - 1; j >= 0; --j) {
                ++sameBricks[i][j];
                if (j + 1 < m && s[i][j] == s[i][j + 1]) {
                    sameBricks[i][j] += sameBricks[i][j + 1];
                }
            }
        }
        return sameBricks;
    }

    int[][] buildUp(int n, int m, char[][] s) {
        int[][] sameBricks = new int[n][m];
        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                ++sameBricks[i][j];
                if (i > 0 && s[i][j] == s[i - 1][j]) {
                    sameBricks[i][j] += sameBricks[i - 1][j];
                }
            }
        }
        return sameBricks;
    }

    int[][] buildDown(int n, int m, char[][] s) {
        int[][] sameBricks = new int[n][m];
        for (int j = 0; j < m; ++j) {
            for (int i = n - 1; i >= 0; --i) {
                ++sameBricks[i][j];
                if (i + 1 < n && s[i][j] == s[i + 1][j]) {
                    sameBricks[i][j] += sameBricks[i + 1][j];
                }
            }
        }
        return sameBricks;
    }
}
