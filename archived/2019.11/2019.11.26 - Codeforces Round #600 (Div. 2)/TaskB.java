package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int status = 0;
        Vector<Integer> result = new Vector<>();
        Vector<Integer> piece = new Vector<>();
        int[] office = new int[1000005];
        boolean OK = true;
        int count = 0;
        for (int i = 0; i < n; ++i) {
            int b = a[i];
            status += b;
            if (b < 0) {
                b = -b;
                if (office[b] == 1) {
                    office[b] = 2;
                } else{
                    OK = false;
                    break;
                }
            } else {
                if (office[b] == 0) {
                    piece.add(b);
                    office[b] = 1;
                } else {
                    OK = false;
                    break;
                }
            }
            ++count;
            if (status == 0) {
                result.add(count);
                count = 0;
                for (int c : piece) {
                    office[c] = 0;
                }
                piece.clear();
            } else {

            }
        }
        if(status>0){
            OK=false;
        }
        if (OK) {
            out.println(result.size());
            for (int b : result) {
                out.print(b + " ");
            }
        } else {
            out.println(-1);
        }
    }
}
