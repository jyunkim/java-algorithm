package baekjoon;

import java.util.*;
import java.io.*;

public class PickNumber_2668 {

    static int[] numbers;
    static boolean[] visited;
    static int start;
    static Set<Integer> set;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        numbers = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                start = i;
                set = new HashSet<>();
                dfs(i);
            }
        }

        Collections.sort(answer);

        System.out.println(answer.size());
        for (Integer number : answer) {
            System.out.println(number);
        }
    }

    public static void dfs(int current) {
        // 같은 숫자가 두번 나올 경우 정답이 될 수 없음
        if (set.contains(numbers[current])) {
            return;
        }
        set.add(numbers[current]);

        // 시작점으로 다시 돌아올 경우에만 정답에 삽입
        if (numbers[current] == start) {
            for (Integer number : set) {
                answer.add(number);
                visited[number] = true;
            }
            return;
        }

        if (!visited[numbers[current]]) {
            dfs(numbers[current]);
        }
    }
}
