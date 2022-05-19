package programmers.kakao;

import java.util.*;

public class GetReportResult {

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> reportCount = new HashMap<>();
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        Map<String, Set<String>> reportMap = new HashMap<>();

        for (String id : id_list) {
            reportCount.put(id, 0);
            reportMap.put(id, new HashSet<>());
        }

        for (String reportId : reportSet) {
            String[] split = reportId.split(" ");
            String from = split[0];
            String to = split[1];
            reportCount.replace(to, reportCount.get(to) + 1);
            reportMap.get(from).add(to);
        }

        for (Map.Entry<String, Integer> countEntry : reportCount.entrySet()) {
            if (countEntry.getValue() >= k) {
                for (int i = 0; i < id_list.length; i++) {
                    if (reportMap.get(id_list[i]).contains(countEntry.getKey())) {
                        answer[i] += 1;
                    }
                }
            }
        }
        return answer;
    }
}
