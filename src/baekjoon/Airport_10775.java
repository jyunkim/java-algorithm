package baekjoon;

import java.io.*;
import java.util.*;

// 그리디 + 이분탐색
public class Airport_10775 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        int count = 0;
//        int[] gate = new int[g + 1];
        List<Integer> gate = new ArrayList<>();
        for (int i = 1; i <= g; i++) {
            gate.add(i);
        }

        for (int i = 0; i < p; i++) {
            // 도킹할 수 있는 가장 큰 번호의 게이트를 찾음
            int airplane = Integer.parseInt(br.readLine());
            int index = -1;
            int low = 0;
            int high = gate.size() - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (gate.get(mid) <= airplane) {
                    index = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // 도킹할 수 있는 게이트가 없으면 공항 폐쇄
            if (index == -1) {
                break;
            }

            // 도킹할 수 있으면 게이트 제거
            gate.remove(index);
            count++;

            // 도킹할 수 있는 가장 큰 번호의 게이트를 찾음(그리디 + 이분탐색) -> lower bound를 올릴순 있지만 upper bound를 줄일순 없음
//            int index = 0;
//            int low = 1;
//            int high = Integer.parseInt(br.readLine());

//            while (low <= high) {
//                int mid = (low + high) / 2;
//                if (gate[mid] == 0) {
//                    index = mid;
//                    low = mid + 1;
//                } else {
//                    high = mid - 1;
//                }
//            }
//
//            // 도킹할 수 있는 게이트가 없으면 공항 폐쇄
//            if (index == 0) {
//                break;
//            }
//
//            // 도킹
//            gate[index] = 1;
//            count++;

            // 큰 index부터 검사(그리디 + 완전탐색) -> 최악의 경우 O(n)
//            int airplane = Integer.parseInt(br.readLine());
//            while (airplane > 0) {
//                if (gate[airplane] == 0) {
//                    gate[airplane] = 1;
//                    count++;
//                    break;
//                }
//                airplane--;
//            }
//            if (airplane == 0) {
//                break;
//            }
        }

        System.out.println(count);
    }
}
