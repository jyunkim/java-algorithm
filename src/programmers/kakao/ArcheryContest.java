package programmers.kakao;

import java.util.*;

public class ArcheryContest {

    private int maxDifference = 0;
    private final List<int[]> answers = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        dfs(0, n, new int[info.length], info);

        if (maxDifference == 0) {
            return new int[]{-1};
        }
        return answers.get(answers.size() - 1);
    }

    private void dfs(int start, int leftArrow, int[] ryanInfo, int[] apeachInfo) {
        if (leftArrow == 0) {
            if (ryanInfo[1] == 2 && ryanInfo[2] == 2 && ryanInfo[4] == 1) {
                System.out.println();
            }
            int difference = calculateDifference(ryanInfo, apeachInfo);
            if (difference > maxDifference) {
                maxDifference = difference;
                answers.clear();
                answers.add(ryanInfo.clone());
            } else if (difference == maxDifference) {
                answers.add(ryanInfo.clone());
            }
            return;
        }

        for (int i = start; i < ryanInfo.length; i++) {
            ryanInfo[i]++;
            dfs(i, leftArrow - 1, ryanInfo, apeachInfo);
            ryanInfo[i]--;
        }
    }

    private int calculateDifference(int[] ryanInfo, int[] apeachInfo) {
        int ryanScore = 0;
        int apeachScore = 0;

        for (int i = 0; i < ryanInfo.length; i++) {
            if (ryanInfo[i] == 0 && apeachInfo[i] == 0) {
                continue;
            }
            if (ryanInfo[i] > apeachInfo[i]) {
                ryanScore += (10 - i);
            } else {
                apeachScore += (10 - i);
            }
        }
        return ryanScore - apeachScore;
    }
}
