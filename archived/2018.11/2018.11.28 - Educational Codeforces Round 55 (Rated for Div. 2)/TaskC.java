package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        Vector<Integer>[] subject = new Vector[m];
        Vector<Integer>[] sum = new Vector[m];
        int len = 0;
        for (int i = 0; i < n; ++i) {
            int s = in.nextInt();
            int r = in.nextInt();
            if (!map.containsKey(s)) {
                map.put(s, len);
                subject[len] = new Vector<>();
                subject[len++].add(r);
            } else {
                int k = map.get(s);
                subject[k].add(r);
            }
        }
        m = len;
        if (m == 0) {
            out.println(0);
            return;
        }
        for (int i = 0; i < m; ++i) {
            Collections.sort(subject[i], new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }
        Arrays.sort(subject, 0, m, new Comparator<Vector<Integer>>() {
            @Override
            public int compare(Vector<Integer> o1, Vector<Integer> o2) {
                return o2.size() - o1.size();
            }
        });

//        for(int i=0;i<m;++i){
//            for(int j=0;j<subject[i].size();++j){
//                out.print(subject[i].get(j)+" ");
//            }
//            out.println();
//        }
        int mx = 0;
        long[] S = new long[m];
        for (int i = 0; i < m; ++i) {
            sum[i] = new Vector<>();
            sum[i].add(subject[i].get(0));
//            if(subject[i].get(0)>=0) {
//                S[i] += subject[i].get(0);
//            }
            for (int j = 1; j < subject[i].size(); ++j) {
                sum[i].add(sum[i].get(j - 1) + subject[i].get(j));
//                if(subject[i].get(j)>=0) {
//                    S[j] += subject[i].get(j);
//                }
            }
            mx = Math.max(mx, subject[i].size());

        }

        long res = 0;
        for (int j = 0; j < mx; ++j) {
            res = Math.max(res, getSum(j, m, subject, sum));
        }
        out.println(res);

    }

    long getSum(int mid, int m, Vector<Integer>[] subject, Vector<Integer>[] sum) {
        long sumTemp = 0;
        for (int i = 0; i < m; ++i) {
            if (mid < subject[i].size() && sum[i].get(mid) >= 0) {
                sumTemp += sum[i].get(mid);
            }
            else if(mid>=subject[i].size()){
                break;
            }
        }
        return sumTemp;
    }
}
