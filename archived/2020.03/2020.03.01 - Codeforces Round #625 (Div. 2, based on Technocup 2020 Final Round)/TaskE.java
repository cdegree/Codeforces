package current;

import algorithms.BinaryIndexedTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Watchable;
import java.util.*;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        TreeSet<Weapon> weapons = new TreeSet<>(Comparator.comparingInt(w -> w.attack));
        TreeSet<Armor> armors = new TreeSet<>(Comparator.comparingInt(a -> a.defence));
        Iterator<Armor> am = armors.iterator();
        int mnw = 1 << 30;
        int mna = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int a = in.nextInt();
            int ca = in.nextInt();
            Weapon w = new Weapon(a, ca);
            if (weapons.contains(w)) {
                Weapon ww = weapons.floor(w);
                if (w.cost < ww.cost) {
                    weapons.remove(ww);
                    weapons.add(w);
                    //System.out.println("added " + w.cost + " " + ww.cost);
                }
            } else {
                weapons.add(w);
            }
            mnw = Math.min(mnw, ca);
        }
        for (int i = 0; i < m; ++i) {
            int d = in.nextInt();
            int cd = in.nextInt();
            Armor a = new Armor(d, cd);
            if (armors.contains(a)) {
                armors.add(a);
                Armor aa = armors.floor(a);
                if (a.cost < aa.cost) {
                    armors.remove(aa);
                    armors.add(a);
                    //System.out.println("added");
                }
            } else {
                armors.add(a);
            }

            mna = Math.min(mna, cd);
        }
        Vector<Monster> monsters = new Vector<>();
        BinaryIndexedTree bit = new BinaryIndexedTree(1000005);
        for (int i = 0; i < k; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();
            monsters.add(new Monster(x, y, z));
        }
        Collections.sort(monsters, new Comparator<Monster>() {
            @Override
            public int compare(Monster o1, Monster o2) {
                if (o1.defence == o2.defence) {
                    return o1.attack - o2.attack;
                } else {
                    return o1.defence - o2.defence;
                }
            }
        });

        long res = -mna - mnw;

        int sq = 1;
        while (sq * sq < k) ++sq;
        int[] belong = new int[k];
        for (int i = 0; i < k; ++i) {
            belong[i] = i / sq;
        }

        for (int i = 0; i < k; ++i) {
            int left = i;
            int right = i;
            while (right + 1 < k && belong[right + 1] == belong[left]) ++right;
            for (int j = left; j <= right; ++j) {
                int d = monsters.get(j).defence;
                Weapon weapon = weapons.higher(new Weapon(d, 0));
                if (weapon != null) {
                    int a = monsters.get(j).attack;
                    int r = monsters.get(j).reward;
                    bit.update(a, r);
                    Armor ar = armors.higher(new Armor(a, 0));
                    if (ar != null) {
                        long rs = bit.sum(ar.defence) - weapon.cost - ar.cost;
                        //System.out.println(String.format("sum[%d] = %d", a, bit.sum(a)));
                        //System.out.println(String.format("Monster %d - %d,%d,%d can be defeated by %d,%d  %d,%d", j, d, a, r, weapon.attack, weapon.cost, ar.defence, ar.cost));
                        res = Math.max(res, rs);
                    }
                    for (int p = left; p < j; ++p) {
                        ar = armors.higher(new Armor(monsters.get(p).attack, 0));
                        if (ar != null) {
                            long rs = bit.sum(ar.defence) - weapon.cost - ar.cost;
                            //System.out.println(String.format("sum[%d] = %d", a, bit.sum(a)));
                            //System.out.println(String.format("Monster %d - %d,%d,%d can be defeated by %d,%d  %d,%d", p, d, a, r, weapon.attack, weapon.cost, ar.defence, ar.cost));
                            res = Math.max(res, rs);
                        }
                    }
                }
            }
            i = right;
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
