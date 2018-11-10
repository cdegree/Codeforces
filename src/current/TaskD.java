package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();
        int index[][] = new int[m][n + 1];
        int std[] = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            std[i] = in.nextInt();
        }
        for (int j = 1; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                int id = in.nextInt();
                index[j][id] = i;
            }
        }
        long sum = n;
        long last = 0;
        for (int i = 1; i < n; ++i) {
            boolean OK = true;
            for (int j = 1; j < m; ++j) {
                if (index[j][std[i]] + 1 != index[j][std[i + 1]]) {
                    OK = false;
                    break;
                }
            }
            if (OK) {
                last++;
            } else if (last > 0) {
                sum += last * (last + 1) / 2;
                last = 0;
            }
        }
        if (last > 0) {
            sum += 1L * last * (last + 1) / 2;
        }
        out.println(sum);
    }
}
