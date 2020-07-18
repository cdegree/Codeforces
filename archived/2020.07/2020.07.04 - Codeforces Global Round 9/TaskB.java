package current;

import fastio.InputReader;
import sun.awt.image.ImageWatched;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskB {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] a = new int[n][m];
            boolean OK = true;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    a[i][j] = in.nextInt();
                    int available = 0;
                    for (int k = 0; k < 4; ++k) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < n & ny >= 0 && ny < m) {
                            ++available;
                        }
                    }
                    if (a[i][j] > available) {
                        OK = false;
                    }
                    else{
                        a[i][j] = available;
                    }
                }
            }
            if(OK){
                out.println("YES");
                for (int i = 0; i < n; ++i) {
                    for (int j = 0; j < m; ++j) {
                        out.print(a[i][j] + " ");
                    }
                    out.println();
                }
            }
            else{
                out.println("NO");
            }
        }
    }
}
