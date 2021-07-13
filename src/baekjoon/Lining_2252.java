package baekjoon;

import java.io.*;
import java.util.*;

// 위상정렬
// DAG(Directed Acyclic Graph)
public class Lining_2252 {

    private static int[] inDegree; // 자신에게 향하는 edge 수
    private static ArrayList<Integer>[] list; // 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        inDegree = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 그래프 생성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int id1 = Integer.parseInt(st.nextToken());
            int id2 = Integer.parseInt(st.nextToken());

            list[id1].add(id2);
            inDegree[id2]++;
        }

        // 위상정렬
        // in degree가 0이면 poll
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                dq.add(i);
            }
        }

        while (!dq.isEmpty()) {
            int id = dq.poll();
            System.out.print(id + " ");

            for (int temp: list[id]) {
                inDegree[temp]--;
                if (inDegree[temp] == 0) {
                    dq.add(temp);
                }
            }
        }
    }
}
