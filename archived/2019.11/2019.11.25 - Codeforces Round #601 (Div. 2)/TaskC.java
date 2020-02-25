package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskC {
    public class Node{
        int[] q;
        LinkedList<Node> adj = new LinkedList<>();
        public Node(int[] v) {
            this.q = v;
            Arrays.sort(q);
        }
        int getTheOther(int b,int c){
            for(int i=0;i<q.length;++i){
                if(b!=q[i]&&q[i]!=c) {
                    return q[i];
                }
            }
            return -1;
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out){
        int n = in.nextInt();
        Node[] nodes = new Node[n-2];
        TreeMap<Integer, Vector<Integer>> map = new TreeMap<>();
        int[] cnt = new int[100005];
        for(int i=0;i<n-2;++i){
            int[] q = in.readIntArray(3);
            nodes[i] = new Node(q);
            for(int j=0;j<3;++j){
                if(!map.containsKey(q[j])) {
                    map.put(q[j], new Vector<>());
                }
                map.get(q[j]).add(i);
                cnt[ q[j] ]++;
            }
        }
        int[] res = new int[n];
        int len=0;
        for(int i=1;i<=n;++i){
            if(cnt[i]==1){
                res[len++] = i;
                int idx = map.get(i).get(0);
                Node root = nodes[idx];
                int a = res[0];
                int b=-1;
                int c=-1;
                for(int j=0;j<3;++j){
                    if(cnt[root.q[j]]==2){
                        res[len++] = root.q[j];
                        b = root.q[j];
                    }
                    else if(cnt[root.q[j]]==3){
                        c = root.q[j];;
                    }
                }
                res[len++] = c;
                for(int j=0;j<n-3;++j){
                    int next = getNext(a,b,c,idx,map);
                    int other = nodes[next].getTheOther(b,c);
                    res[len++] = other;
                    a = b;
                    b = c;
                    c = other;
                    idx = next;
                }
                break;
            }
        }
        for(int i=0;i<len;++i){
            out.print(res[i]+" ");
        }
    }
    int getNext(int a,int b,int c, int cur,TreeMap<Integer, Vector<Integer>> map){
        HashSet<Integer> common = new HashSet<>();
        for(int idx:map.get(b)){
            common.add(idx);
        }
        for(int idx:map.get(c)){
            if(common.contains(idx)){
                if(idx!=cur){
                    return idx;
                }
            }
        }
        return -1;
    }
}
