package programmers.kakao;

import java.util.*;

public class FindWayGame {

    int[][] answer;
    List<Node> nodeList = new ArrayList<>();
    int count = 0; // answer 배열의 index

    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        Collections.sort(nodeList); // y 내림차순, x 오름차순 정렬

        Node root = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            constructTree(root, nodeList.get(i));
        }

        preOrder(root);
        count = 0;
        postOrder(root);

        return answer;
    }

    // 트리 생성
    public void constructTree(Node parent, Node child) {
        // 하위 노드가 상위 노드보다 왼쪽에 있는 경우
        if (child.x < parent.x) {
            // 왼쪽 자식이 없으면 연결, 있으면 왼쪽 자식 기준으로 재검사
            if (parent.left == null) {
                parent.left = child;
            } else {
                constructTree(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                constructTree(parent.right, child);
            }
        }
    }

    // 전위 순회
    public void preOrder(Node node) {
        answer[0][count++] = node.index;
        if (node.left != null) preOrder(node.left);
        if (node.right != null) preOrder(node.right);
    }

    // 후위 순회
    public void postOrder(Node node) {
        if (node.left != null) postOrder(node.left);
        if (node.right != null) postOrder(node.right);
        answer[1][count++] = node.index;
    }

    static class Node implements Comparable<Node> {
        Node left;
        Node right;

        int x;
        int y;
        int index;

        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) {
                return this.x - o.x;
            } else {
                return o.y - this.y;
            }
        }
    }
}
