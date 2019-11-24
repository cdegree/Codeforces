package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskD {
    public class Station {
        int id;
        int x;
        int y;
        long c;
        long k;
        boolean build;
    }

    public class Edge {
        int x;
        int y;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Station[] stations = new Station[n];
        for (int i = 0; i < n; ++i) {
            stations[i] = new Station();
            stations[i].build = false;
            stations[i].x = in.nextInt();
            stations[i].y = in.nextInt();
            stations[i].id = i + 1;
        }
        for (int i = 0; i < n; ++i) {
            stations[i].c = in.nextInt();
        }
        for (int i = 0; i < n; ++i) {
            stations[i].k = in.nextInt();
        }
        Arrays.sort(stations, new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return Long.compare(o1.c, o2.c);
            }
        });
        stations[0].build = true;
        long cost = stations[0].c;
        long mx = 9000000000000000000L;
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Integer> builds = new ArrayList<>();
        builds.add(stations[0].id);
        for(int k=1;k<n;++k){
            for(int i=0;i<n;++i){

            }
        }

        for (int i = 1; i < n; ++i) {
            long buildCost = stations[i].c;
            long minConnectCost = buildCost;
            int cand = i;
            for (int j = 0; j < i; ++j) {
                long connectCost = (Math.abs(stations[i].x - stations[j].x) + Math.abs(stations[i].y - stations[j].y)) * (stations[i].k + stations[j].k);
                if (connectCost < minConnectCost) {
                    minConnectCost = connectCost;
                    cand = j;
                }
            }
            if (cand != i) {
                cost += minConnectCost;
                Edge edge = new Edge();
                edge.x = stations[i].id;
                edge.y = stations[cand].id;
                if(stations[i].x!=stations[cand].x||stations[i].y!=stations[cand].y) {
                    edges.add(edge);
                }
            } else {
                cost += buildCost;
                builds.add(stations[i].id);
            }
        }
        out.println(cost);
        out.println(builds.size());
        for (int i = 0; i < builds.size(); ++i) {
            out.print(builds.get(i) + " ");
        }
        out.println();
        out.println(edges.size());
        for (int i = 0; i < edges.size(); ++i) {
            out.println(edges.get(i).x + " " + edges.get(i).y);
        }
        out.println();
    }
}
