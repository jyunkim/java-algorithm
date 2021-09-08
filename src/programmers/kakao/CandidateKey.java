package programmers.kakao;

import java.util.*;

public class CandidateKey {

    List<List<Integer>> candidates;

    public int solution(String[][] relation) {
        candidates = new ArrayList<>();
        int column_length = relation[0].length;

        // 뽑는 숫자를 증가시키며 전체 조합 구하기
        for (int i = 1; i <= column_length; i++) {
            check(relation, new ArrayList<>(), 0, column_length, i);
        }
        return candidates.size();
    }

    public void check(String[][] relation, List<Integer> attributes, int startIndex, int n, int r) {
        if (r == 0) {
            // 최소성 검사
            for (List<Integer> candidate : candidates) {
                if (attributes.containsAll(candidate)) return;
            }
            // 유일성 검사
            if (isUnique(relation, attributes)) {
                candidates.add(attributes);
            }
        }
        // 조합 구하기
        for (int i = startIndex; i < n; i++) {
            List<Integer> list = new ArrayList<>(attributes);
            list.add(i);
            check(relation, list, i + 1, n, r - 1);
        }
    }

    // Set을 이용하여 문자열 중복 검사
    public boolean isUnique(String[][] relation, List<Integer> attributes) {
        Set<String> set = new HashSet<>();

        for (String[] tuple : relation) {
            String temp = "";
            for (Integer index : attributes) {
                temp += tuple[index] + " "; // ["10", "110"], ["101", "10"] 구분하기 위해 칼럼 간 공백 삽입
            }
            set.add(temp);
        }
        return set.size() == relation.length;
    }
}
