package baekjoon;

import java.util.*;
import java.io.*;

// 비트마스킹
public class WordMemorization_18119 {

//    static List<String> dictionary = new ArrayList<>(); // 단어가 적힌 사전
//    static boolean[] memorize = new boolean[26]; // 알파벳 기억 여부
    static List<Integer> dictionary = new ArrayList<>(); // 단어가 이진수 형태로 적힌 사전
    static int memorize = (1 << 26) - 1; // z..a 순으로 각 위치에 해당하는 알파벳을 기억하면 1, 잊었으면 0 (처음엔 모든 알파벳을 기억)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 처음에는 모든 알파벳을 기억
//        Arrays.fill(memorize, true);

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            int binaryWord = 0;
            // 단어를 26자리 이진수 형태로 나타냄
            // z..a 순으로 각 위치에 해당 알파벳이 있으면 1, 없으면 0
            for (int j = 0; j < word.length(); j++) {
                binaryWord |= (1 << (word.charAt(j) - 'a'));
            }
            dictionary.add(binaryWord);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String o = st.nextToken();
            char x = st.nextToken().charAt(0);

            if (o.equals("1")) { // 알파벳을 잊음
                memorize &= ~(1 << (x - 'a'));
            } else { // 알파벳을 기억해냄
                memorize |= (1 << (x - 'a'));
            }
            printKnownWordNum(); // 알고 있는 단어의 개수 출력
        }
    }

    public static void printKnownWordNum() {
        int count = 0;

//        for (String word : dictionary) {
//            boolean isKnown = true;
//            for (int i = 0; i < word.length(); i++) {
//                // 단어에 모르는 알파벳이 있는 경우
//                if (!memorize[word.charAt(i) - 'a']) {
//                    isKnown = false;
//                    break;
//                }
//            }
//            if (isKnown) count++;
//        }
        for (Integer word : dictionary) {
            // 단어 안에 모든 알파벳을 알고 있으면(word가 memorize의 부분집합) 개수 증가
            if ((word & memorize) == word) count++;
        }
        System.out.println(count);
    }
}
