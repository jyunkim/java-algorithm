package baekjoon;

import java.io.*;
import java.util.*;

// Union-find
public class SetExpression_1717 {

    // 부모 노드 저장
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 자기 자신을 가리키도록 초기화
        // 노드 - 1번 ~ n번 이므로 n+1
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 0) {
                union(b, c);
            } else if (a == 1) {
                // 루트 부모가 같을 경우
                if (find(b) == find(c)) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            }
        }
    }

    // 연결
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        // 부모 설정
        // 같은 수가 들어오면 처리 x
        if (x != y) {
            parent[y] = x;
        }
    }

    // 루트 부모 반환
    public static int find(int x) {
        // 종료 조건
        if (parent[x] == x) {
            return x;
        }
        // 시간 단축을 위해 바로 루트 부모를 가리키도록 설정
        return parent[x] = find(parent[x]);
    }
}
