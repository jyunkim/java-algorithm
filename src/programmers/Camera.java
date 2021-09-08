package programmers;

import java.util.*;

public class Camera {

    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> a[1] - b[1]); // 빨리 나가는 순으로 정렬
        // LinkedList로 삭제할 때 탐색하는 데 O(n)이 소요되기 때문에 약간 더 느림
        List<int[]> list = new ArrayList<>(Arrays.asList(routes));

        // 가장 먼저 나가는 차량의 진출 시점에 카메라를 설치한 후
        // 해당 카메라와 만날 수 있는 차량들을 제거한다.
        while (!list.isEmpty()) {
            int camera = list.remove(0)[1];
            list.removeIf(route -> route[0] <= camera && route[1] >= camera);
            answer++;
        }
        return answer;
    }
}
