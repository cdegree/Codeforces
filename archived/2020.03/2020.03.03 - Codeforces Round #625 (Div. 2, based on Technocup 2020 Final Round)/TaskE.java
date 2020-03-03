package current;

import algorithms.ArrayUtils;
import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Vector<Weapon> weapons = new Vector<>();
        Vector<Armor> armors = new Vector<>();
        for (int i = 0; i < n; ++i) {
            int a = in.nextInt();
            int ca = in.nextInt();
            weapons.add(new Weapon(a, ca));
        }
        Collections.sort(weapons, Comparator.comparingInt(w -> w.attack));
        for (int i = 0; i < m; ++i) {
            int d = in.nextInt();
            int cd = in.nextInt();
            armors.add(new Armor(d, cd));
        }
        Collections.sort(armors, Comparator.comparingInt(a -> a.defence));
        int[] armorCost = new int[m + 1];
        for (int i = 1; i <= m; ++i) {
            armorCost[i] = -armors.get(i - 1).cost;
            //System.out.println(String.format("Armor defence = ^%d", armors.get(i - 1).defence));
        }
        Vector<Monster> monsters = new Vector<>();
        for (int i = 0; i < k; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            monsters.add(new Monster(x, y, z));
        }
        Collections.sort(monsters, Comparator.comparingInt(o -> o.defence));
        SegmentTree st = new SegmentTree(m);
        st.build(armorCost);
        long res = -(1L << 60);
        int j = 0;
        for (Weapon weapon : weapons) {
            while (j < k && monsters.get(j).defence < weapon.attack) {
                int monsterAttack = monsters.get(j).attack;
                int monsterReward = monsters.get(j).reward;
                int left = 0;
                int right = m - 1;
                int ans = -1;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    if (armors.get(mid).defence > monsterAttack) {
                        right = mid - 1;
                        ans = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                //System.out.println(String.format("monsterAttack = %d  find %d -> %d += %d", monsterAttack, index, m, monsterReward));
                if (ans != -1) {
                    st.update(ans + 1, m, monsterReward);
                }
                ++j;
            }
            res = Math.max(res, st.query(1, m) - weapon.cost);
            //System.out.println(String.format("Weapon attack %d cost %d   reward %d", weapon.attack, weapon.cost, st.query(1, m)));
        }
        out.println(res);
    }

    class Weapon {
        int attack;
        int cost;

        public Weapon(int attack, int cost) {
            this.attack = attack;
            this.cost = cost;
        }

    }

    class Armor {
        int defence;
        int cost;
        int index;

        public Armor(int defence, int cost) {
            this.defence = defence;
            this.cost = cost;
        }
    }

    class Monster {
        int defence;
        int attack;
        int reward;

        public Monster(int defence, int attack, int reward) {
            this.defence = defence;
            this.attack = attack;
            this.reward = reward;
        }

    }
}
