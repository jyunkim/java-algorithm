package baekjoon;

import java.io.*;
import java.util.*;

public class Delivery_1719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] weight = new int[n + 1][n + 1];
        int[][] answer = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    weight[i][j] = Integer.MAX_VALUE / 2; // 가중치끼리 더해서 int 범위 초과되는 것 방지
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            weight[a][b] = c;
            weight[b][a] = c;

            answer[a][b] = b;
            answer[b][a] = a;
        }

        // 플로이드 와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (weight[i][k] + weight[k][j] < weight[i][j]) {
                        weight[i][j] = weight[i][k] + weight[k][j];
                        answer[i][j] = k;
                    }
                }
            }
        }

        // 여러 정점을 거쳐가는 경우 가장 처음 거쳐야 하는 정점으로 갱신
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int temp = j;
                while (temp != answer[i][temp]) {
                    temp = answer[i][temp];
                }
                answer[i][j] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (answer[i][j] == 0) {
                    sb.append("- ");
                } else {
                    sb.append(answer[i][j]);
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
