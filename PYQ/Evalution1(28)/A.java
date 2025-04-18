/*Sylhet is one of the most beautiful cities in Bangladesh during the monsoon season and
Sharara has decided to explore Sylhet in the monsoon season. But it is difficult for her to
see as she navigates Sylhet in her car on a cloudy day. She has 𝐾 units of petrol left in her
car, and she can go one unit farther with each unit of fuel.
Given Sylhet’s layout, which is made up of several interconnected cities, and her current
location, your goal is to calculate and print the maximum number of cities she can visit until
she runs out of fuel.
Input
● The input starts with four integers:
○ C: The number of cities in Sylhet.
○ R: The number of roads connecting different cities in Sylhet
○ 𝐾: The remaining fuel units in Sharara's car.
○ L : Sharara's current city
● The following R lines describe the road connections between cities as pairs of
integers 𝑢,𝑣, indicating a bidirectional road between cities 𝑢 and 𝑣
Output
Output a single integer representing the total possible number of cities Sharara can reach if
she uses up all 𝐾 units of her fuel.*/

import java.util.*;

public class A {

    static class Graph {
        int v;
        List<List<Integer>> adj;
        boolean[] visit;
        int city=0;
        Graph(int v) {
            this.v = v;
            adj = new ArrayList<>();
            for (int i = 0; i <= v; i++)
                adj.add(new ArrayList<>());
            this.visit = new boolean[v + 1];

        }
        public void addedge(int u,int v)
        {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        public int cityvisit(int fuel,int curr)
        {
            if(fuel<0||visit[curr]) return 0;
            visit[curr]=true;
            city++;
            for(int neigh: adj.get(curr))
            {
                if(!visit[neigh])
                {
                    cityvisit(fuel-1,neigh);
                }
            }
            return city;

        }
    }

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int c=sc.nextInt();
        int r=sc.nextInt();
        int re=sc.nextInt();
        int cur=sc.nextInt();
        Graph g=new Graph(c);
        for(int i=0;i<r;i++)
        {
            int u=sc.nextInt();
            int w=sc.nextInt();
            g.addedge(u,w);
        }
        System.out.println(g.cityvisit(re,cur));


    }

}
