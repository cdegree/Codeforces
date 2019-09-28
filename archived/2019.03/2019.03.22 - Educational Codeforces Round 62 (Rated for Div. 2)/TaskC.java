package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskC {
    public class Song{
        int t;
        int b;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        int k = in.nextInt();
        ArrayList<Song> songs = new ArrayList<>(n);
        for(int i=0;i<n;++i){
            Song s = new Song();
            s.t = in.nextInt();
            s.b = in.nextInt();
            songs.add(s);
        }
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o2.b-o1.b;
            }
        });
        TreeMap<Integer,Integer> set = new TreeMap<>();
        long sum = 0;
        long res = 0;
        int sz = 0;
        for(int i=0;i<n;++i){
            int len = songs.get(i).t;
            if(sz<k){
                if(!set.containsKey(len)){
                    set.put(len,1);
                }
                else {
                    set.put(len,set.get(len)+1);
                }
                sum += len;
                ++sz;
            }
            else {
                if(set.firstKey()<len){
                    int cnt = set.get(set.firstKey());
                    int mnLen = set.firstKey();
                    set.put(mnLen,cnt-1);
                    if(set.get(mnLen)==0){
                        set.remove(mnLen);
                    }
                    sum -= mnLen;
                    sum += len;
                    if(!set.containsKey(len)){
                        set.put(len,1);
                    }
                    else {
                        set.put(len,set.get(len)+1);
                    }
                }
            }
            int bt = songs.get(i).b;
            res = Math.max(res, 1L*bt*sum);
//            out.println(len+"  "+bt);
        }
        out.println(res);
    }
}
