package baekjoon;

import java.util.*;
import java.io.*;

// MST
public class PowerShortage_6497 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            List<Road> roads = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());

                roads.add(new Road(x, y, distance));
            }

            Collections.sort(roads);

            int totalCost = 0;
            int cost = 0;
            for (Road road : roads) {
                totalCost += road.distance;

                // 자신의 부모가 union되면 자신의 parent는 업데이트가 되지 않기 때문에 parent로는 비교 불가
                if (find(road.x) != find(road.y)) {
                    union(road.x, road.y);
                    cost += road.distance;
                }
            }

            System.out.println(totalCost - cost);
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[y] = x;
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Road implements Comparable<Road> {
        int x;
        int y;
        int distance;

        public Road(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            return distance - o.distance;
        }
    }
}
