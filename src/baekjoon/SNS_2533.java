package baekjoon;

import java.io.*;
import java.util.*;

public class SNS_2533 {

    private static int[][] dp;
    private static List<Node>[] graph; // 트리를 만들기 위한 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // dp[i][j] = i번 노드가 root인 서브 트리에서 필요한 최소 얼리어답터 수
        // j = 0: i번 노드가 얼리어답터가 아닐 경우, j = 1: i번 노드가 얼리어답터일 경우
        dp = new int[n + 1][2];
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            // 리프노드에 사용될 초기값
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v));
            graph[v].add(new Node(u));
        }

        Node root = new Node(1); // root 노드는 어떤 것이 되어도 상관 없음
        makeTree(root);
        compute(root);

        int answer = Math.min(dp[root.index][0], dp[root.index][1]); // 최소 얼리어답터 수
        System.out.println(answer);
    }

    // 트리 구성
    // dfs를 이용하여 부모, 자식 노드 설정
    private static void makeTree(Node current) {
        for (Node node : graph[current.index]) {
            // 현재 노드가 루트 노드이거나, 갈 수 있는 노드가 현재 노드의 부모 노드가 아니면 탐색
            if (current.parent == null || node.index != current.parent.index) {
                current.children.add(node);
                node.parent = current;
                makeTree(node);
            }
        }
    }

    // bottom up - 리프노드부터 올라가며 계산
    private static void compute(Node current) {
        int min = 0;
        int sum = 0;
        for (Node node : current.children) {
            compute(node);
            min += Math.min(dp[node.index][0], dp[node.index][1]);
            sum += dp[node.index][1];
        }

        // 리프노드가 아닐 경우
        if (!current.children.isEmpty()) {
            dp[current.index][0] = sum; // 자신이 얼리어답터가 아니면 자식들이 모두 얼리어답터여야 함
            dp[current.index][1] = min + 1; // 자신이 얼리어답터면 자식들은 얼리어답터가 아니어도 됨 -> 최소값으로 계산
        }
    }

    public static class Node {
        int index;
        Node parent;
        List<Node> children = new ArrayList<>();

        public Node(int index) {
            this.index = index;
        }
    }
}
