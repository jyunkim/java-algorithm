package baekjoon;

import java.io.*;
import java.util.*;

public class FriendNetwork_4195 {

    private static Map<String, String> parent;
    private static Map<String, Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int f = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            count = new HashMap<>();

            for (int j = 0; j < f; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                parent.putIfAbsent(friend1, friend1);
                parent.putIfAbsent(friend2, friend2);
                count.putIfAbsent(friend1, 1);
                count.putIfAbsent(friend2, 1);

                // 부모가 다를 경우만 union
                // parent가 업데이트 되지 않았을 수 있으므로 find로 찾아줌
                if (!find(friend1).equals(find(friend2))) {
                    union(friend1, friend2);
                }
                System.out.println(count.get(find(friend1)));
            }
        }
    }

    private static void union(String friend1, String friend2) {
        friend1 = find(friend1);
        friend2 = find(friend2);
        parent.replace(friend2, friend1);
        count.replace(friend1, (count.get(friend1) + count.get(friend2))); // 두 네트워크의 친구 수 합을 부모에 저장
    }

    private static String find(String friend) {
        if (friend.equals(parent.get(friend))) {
            return friend;
        }
        String topParent = find(parent.get(friend));
        parent.replace(friend, topParent);
        return topParent;
    }
}
