package baekjoon;

import java.io.*;
import java.util.*;

public class CountCross_1615 {

    private static long[] tree;
    private static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        num = 1;
        int temp = 0;
        while (num < n) {
            num *= 2;
            temp += 1;
        }
        tree = new long[num * 2];

        long answer = 0;
        for (int[] edge: edges) {
            answer += query(edge[1] + 1, n);
            update(edge[1]);
        }
        System.out.println(answer);
    }

    public static void update(int i) {
        int index = i + num - 1;
        tree[index]++;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    public static long query(int l, int r) {
        long count = 0;
        l += (num - 1);
        r += (num - 1);
        while (l <= r) {
            if (l % 2 == 1) {
                count += tree[l];
                l++;
            }
            if (r % 2 == 0) {
                count += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return count;
    }
}
