package baekjoon;

import java.io.*;

// KMP -> O(N+M)
public class SubString_16916 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();

//        System.out.println(s.contains(p) ? 1 : 0); -> O(NM)
        System.out.println(kmp(s, p) ? 1 : 0);
    }

    // 문자가 일치하지 않을 경우, 다음 일치하는 부분으로 건너뜀(pattern의 접두사와 string의 접미사가 같은 부분)
    private static boolean kmp(String string, String pattern) {
        int j = 0; // 현재 탐색 중인 pattern의 index
        int[] pi = getPiTable(pattern);

        for (int i = 0; i < string.length(); i++) { // 현재 탐색 중인 string의 index
            while (j > 0 && string.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1]; // 현재 탐색 중인 pattern의 index를 앞으로 땡김
            }
            if (string.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    // pi[i] = pattern의 i번째 index까지 포함한 문자열에서 접두사와 접미사가 같은 부분 문자열의 최대 길이
    private static int[] getPiTable(String pattern) {
        int length = pattern.length();
        int[] pi = new int[length];
        int j = 0;

        for (int i = 1; i < length; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
