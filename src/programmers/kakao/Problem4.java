package programmers.kakao;

import java.util.*;

public class Problem4 {

    static int score = 0;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) {
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(solution(5, info)));
    }

    public static int[] solution(int n, int[] info) {
        dfs(n, info, 0, new int[info.length], 0);

        return list.get(0);
    }

    public static void dfs(int n, int[] info, int temp, int[] scoreList, int index) {
        if (n == 0) {
            if (score <= temp) {
                score = temp;
                list.add(scoreList);
            }
            return;
        } else if (n < 0) {
            int[] arr = {-1};
            list.add(arr);
            return;
        }

        for (int i = index; i < info.length; i++) {
            int shoot = info[i] + 1;
            scoreList[i] = shoot;
            dfs(n - shoot, info, temp + 10 - i, scoreList, i + 1);
            scoreList[i] = 0;
        }
    }
}
