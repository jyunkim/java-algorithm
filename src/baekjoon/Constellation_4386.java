package baekjoon;

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

// MST
// Kruskal Algorithm
public class Constellation_4386 {

    static int n;
    static double answer = 0;
    static int[] parent; // index(자식) -> element(부모)
    static double[][] stars; // 별의 좌표를 담는 배열
    static List<Edge> edges = new ArrayList<>(); // 연결할 수 있는 모든 edges

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        stars = new double[n + 1][2];

        for (int i = 1; i <= n; i++) {
            // 자기 자신을 부모로 가리키도록 초기화
            parent[i] = i;
            // i번째 별의 좌표를 stars[i]에 담는다
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        // edge 저장
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double cost = getDistance(stars[i], stars[j]); // i, j번째 별들의 거리 계산
                edges.add(new Edge(i, j, cost));
            }
        }
        // cost가 작은 순으로 edge 정렬
        Collections.sort(edges);

        for (Edge edge : edges) {
            // 두 별이 직/간접적으로 연결되어 있지 않은 경우 연결
            if (find(edge.star1) != find(edge.star2)) {
                union(edge.star1, edge.star2);
                answer += edge.cost;
            }
        }
        System.out.println(round(answer * 100) / 100.0);
    }

    // union-find
    public static void union(int star1, int star2) {
        star1 = find(star1);
        star2 = find(star2);
        parent[star2] = star1;
    }

    // 최상위 부모 반환
    public static int find(int star) {
        if (parent[star] == star) {
            return star;
        }
        return parent[star] = find(parent[star]); // 최상위 부모를 바로 가리키도록 최적화
    }

    public static double getDistance(double[] star1, double[] star2) {
        return sqrt(pow((star1[0] - star2[0]), 2) + pow(star1[1] - star2[1], 2));
    }

    static class Edge implements Comparable<Edge> {
        int star1;
        int star2;
        double cost;

        public Edge(int star1, int star2, double cost) {
            this.star1 = star1;
            this.star2 = star2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}
