package baekjoon;

import java.io.*;
import java.util.*;

// 그리디
// 자릿수가 가장 큰 알파벳부터 큰 숫자 할당 -> 자릿수가 같을 경우 추가 조건 필요
// 방정식 문제처럼 접근하여 알파벳 별로 가중치 계산
public class WordMath_1339 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            // 알파벳 별로 자릿수 가중치 합을 저장
            for (int j = 0; j < word.length(); j++) {
                char alphabet = word.charAt(j);
                int weight = (int) Math.pow(10, word.length() - 1 - j);

                map.putIfAbsent(alphabet, 0);
                map.replace(alphabet, map.get(alphabet) + weight);
            }
        }

        List<Integer> weights = new ArrayList<>(map.values());
        weights.sort(Comparator.reverseOrder());

        int answer = 0;
        int number = 9;

        // 가중치가 큰 알파벳부터 9, 8, 7.. 순으로 할당 후 합 계산
        for (Integer weight : weights) {
            answer += weight * number;
            number--;
        }
        System.out.println(answer);
    }
}
