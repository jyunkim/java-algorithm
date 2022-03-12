package baekjoon;

import java.io.*;
import java.util.*;

// 최장 증가 부분 수열(LIS)
public class TwistedWire_1365 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] poles = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            poles[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]: poles[i]를 마지막 값으로 가지는 최장 증가 부분 수열의 길이 -> O(n^2)
        // dp값 중 최댓값이 최장 증가 부분 수열의 길이
//        int[] dp = new int[n + 1];
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (poles[i] > poles[j]) { // 앞의 숫자들 중 자신보다 작은 값들의 dp의 최댓값 + 1
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//        System.out.println(Arrays.stream(dp).max().getAsInt());

        // 자신보다 작은 값 탐색 시 이분탐색 이용 -> O(nlogn)
        // -> 배열이 정렬되어 있어야 함
        // -> 리스트에 길이가 아닌 각 숫자를 넣음(poles[i]) - 리스트가 부분 수열이 되도록
        // 리스트의 증가 폭이 최소가 되도록 갱신 -> 뒤에 올 숫자를 최대한 수열에 포함시킬 수 있도록
        // 리스트의 길이가 최장 증가 부분 수열의 길이(리스트가 최장 증가 부분 수열은 아님)
        List<Integer> sequence = new ArrayList<>();
        sequence.add(poles[1]);

        for (int i = 2; i <= n; i++) {
            if (poles[i] > sequence.get(sequence.size() - 1)) {
                sequence.add(poles[i]);
            } else {
//                int index = -1 * Collections.binarySearch(sequence, poles[i]) - 1; // key가 없을 경우 -(key보다 큰 첫번째 값의 index)를 반환
                int index = lowerBound(sequence, poles[i]);
                sequence.set(index, poles[i]);
            }
        }
        System.out.println(n - sequence.size());
    }

    private static int lowerBound(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;
        int lowerBound = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (key <= list.get(mid)) {
                lowerBound = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return lowerBound;
    }
}
