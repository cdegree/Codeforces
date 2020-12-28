package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            boolean oddWin = false;
            for (int i = 0; i < n; i += 2) {
                if ((s[i] - '0') % 2 == 1) {
                    oddWin = true;
                }
            }
            boolean evenWin = false;
            for (int i = 1; i < n; i += 2) {
                if ((s[i] - '0') % 2 == 0) {
                    evenWin = true;
                }
            }
            if (n % 2 == 1) {
                if(oddWin){
                    out.println(1);
                }
                else{
                    out.println(2);
                }
            } else {
                if(evenWin){
                    out.println(2);
                }
                else{
                    out.println(1);
                }
            }
        }
    }
}
