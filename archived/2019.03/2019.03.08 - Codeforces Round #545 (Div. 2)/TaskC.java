package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] row = new int[n][m];
        int[][] col = new int[n][m];
        int[] mxrow = new int[n];
        int[] mxcol = new int[m];
        int[][] a = new int[n][m];
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                a[i][j] = in.nextInt();
            }
        }

        for(int i=0;i<n;++i){
            TreeSet<Integer> set = new TreeSet<>();
            for(int j=0;j<m;++j){
                set.add(a[i][j]);
            }
            Map<Integer,Integer> map = new TreeMap<>();
            int cnt=1;
            for(int v:set){
                map.put(v,cnt++);
            }
            for(int j=0;j<m;++j){
                row[i][j] = map.get(a[i][j]);
                mxrow[i] = Math.max(mxrow[i],row[i][j]);
            }
        }
        for(int j=0;j<m;++j){
            TreeSet<Integer> set = new TreeSet<>();
            for(int i=0;i<n;++i){
                set.add(a[i][j]);
            }
            Map<Integer,Integer> map = new TreeMap<>();
            int cnt=1;
            for(int v:set){
                map.put(v,cnt++);
            }
            for(int i=0;i<n;++i){
                col[i][j] = map.get(a[i][j]);
                mxcol[j] = Math.max(mxcol[j],col[i][j]);
            }
        }
        int[][] ans = new int[n][m];
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                int mrow = mxrow[i];
                int mcol = mxcol[j];
                int r = row[i][j];
                int c = col[i][j];
                if(r==c){
                    ans[i][j] = Math.max(mrow,mcol);
                }
                else if(r>c){
                    int diff = r-c;
                    ans[i][j] = Math.max(mrow,mcol+diff);
                }
                else {
                    int diff = c-r;
                    ans[i][j] = Math.max(mrow+diff,mcol);
                }
            }
        }
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                out.print(ans[i][j]+" ");
            }
        }
    }
}
