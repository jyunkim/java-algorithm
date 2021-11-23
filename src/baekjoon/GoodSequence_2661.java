package baekjoon;

import java.io.*;

public class GoodSequence_2661 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs("1"); // 가장 작은 좋은수열의 시작 숫자는 항상 1
    }

    private static void dfs(String sequence) {
        if (sequence.length() == n) { // 작은 수부터 탐색하기 때문에 처음 길이를 다 채우면 정답
            System.out.println(sequence);
            System.exit(0);
        }

        int lastNumber = Character.getNumericValue(sequence.charAt(sequence.length() - 1));
        for (int i = 1; i < 4; i++) {
            if (lastNumber != i && isGoodSequence(sequence + i)) { // 인접한 숫자는 반드시 달라야 함
                dfs(sequence + i);
            }
        }
    }

    private static boolean isGoodSequence(String sequence) {
        int length = sequence.length();
        int mid = length / 2;

        // 추가된 숫자를 포함한 부분수열만 검사
        for (int i = 1; i <= mid; i++) {
            if (sequence.substring(length - 2 * i, length - i).equals(sequence.substring(length - i))) {
                return false;
            }
        }
        return true;
    }
}
