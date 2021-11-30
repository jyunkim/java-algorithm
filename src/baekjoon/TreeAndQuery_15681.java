package baekjoon;

import java.io.*;
import java.util.*;

// 매번 서브트리의 정점 개수를 새로 세면 쿼리가 많을수록 비효율적
// -> 모든 정점을 루트로 하는 서브트리의 정점 개수를 미리 구해둠
public class TreeAndQuery_15681 {

    static List<Node>[] graph;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            answer[i] = 1;
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v));
            graph[v].add(new Node(u));
        }

        Node root = new Node(r);
        makeTree(root);
        countSubtreeNodes(root);

        for (int i = 0; i < q; i++) {
            int query = Integer.parseInt(br.readLine());
            System.out.println(answer[query]);
        }
    }

    // 루트 노드에서부터 내려가면서 트리 구성(dfs)
    public static void makeTree(Node current) {
        for (Node node : graph[current.value]) {
            // 현재 노드가 루트 노드이거나, 갈 수 있는 노드가 현재 노드의 부모 노드가 아닌 경우
            if (current.parent == null || node.value != current.parent.value) {
                current.children.add(node);
                node.parent = current;
                makeTree(node);
            }
        }
    }

    // 리프 노드에서부터 값을 계산해서 올라옴(초기값 = 1)
    public static void countSubtreeNodes(Node current) {
        for (Node node : current.children) {
            countSubtreeNodes(node);
            answer[current.value] += answer[node.value];
        }
    }

    static class Node {
        int value;
        Node parent;
        List<Node> children = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }
    }
}
