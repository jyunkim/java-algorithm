import java.util.ArrayList;
import java.util.List;

public class Combination {

    private static final List<List<Integer>> combinations = new ArrayList<>();

    public static void main(String[] args) {
        int n = 4;

        // 뽑는 개수: 1 ~ n
        for (int i = 1; i <= n; i++) {
            combination(new ArrayList<>(), 0, n, i);
        }

        System.out.println(combinations);
    }

    private static void combination(List<Integer> list, int startIndex, int n, int r) {
        if (r == 0) {
            combinations.add(new ArrayList<>(list)); // 그대로 저장하면 참조값이 남아있어 데이터가 지워짐
            return;
        }

        // 시작 index: 0 ~ n-1
        // 시작 index가 1씩 증가될 때마다 뽑는 개수를 1씩 감소시켜 다시 뽑음
        for (int i = startIndex; i < n; i++) {
            list.add(i);
            combination(list, i + 1, n, r - 1);
            list.remove(list.size() - 1);
        }
    }
}
