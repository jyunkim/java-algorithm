package programmers;

public class VowelDictionary {

    int answer = 0;
    boolean done = false;
    String[] vowels = {"A", "E", "I", "O", "U"};

    // 빈 문자열에서 dfs를 이용하여 순서대로 사전 탐색
    public int solution(String word) {
        dfs("", word, 0);
        return answer;
    }

    public void dfs(String current, String target, int depth) {
        // 단어를 찾았으면 종료
        if (current.equals(target)) {
            done = true;
            return;
        }

        // 현재 단어의 길이가 5이면 for문을 통해 끝 글자 변경
        if (depth == 5) return;

        for (String vowel : vowels) {
            if (done) return;
            answer++;
            dfs(current + vowel, target, depth + 1);
        }
    }
}
