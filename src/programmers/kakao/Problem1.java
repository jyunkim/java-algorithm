package programmers.kakao;

import java.util.*;

public class Problem1 {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 중복 신고 제거
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));

        Map<String, Integer> countReport = new HashMap<>();
        Map<String, List<String>> userReport = new HashMap<>();

        for (String rep : reportSet) {
            String reporter = rep.split(" ")[0];
            String reported = rep.split(" ")[1];

            // 신고 받은 횟수 세기
            countReport.putIfAbsent(reported, 0);
            countReport.replace(reported, countReport.get(reported) + 1);

            // 유저별 신고한 id 저장
            userReport.putIfAbsent(reporter, new ArrayList<>());
            userReport.get(reporter).add(reported);
        }

        // 정지된 id
        List<String> banned = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : countReport.entrySet()) {
            if (entry.getValue() >= k) {
                banned.add(entry.getKey());
            }
        }

        // 메일 수신 횟수 세기
        for (int i = 0; i < id_list.length; i++) {
            if (userReport.containsKey(id_list[i])) {
                for (String id : userReport.get(id_list[i])) {
                    if (banned.contains(id)) {
                        answer[i]++;
                    }
                }
            }
        }

        return answer;
    }
}
