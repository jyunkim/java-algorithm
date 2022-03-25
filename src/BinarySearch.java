import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 5, 6));
        System.out.println(lowerBound(list, 3)); // 2
        System.out.println(upperBound(list, 3)); // 4
    }

    // key 이상의 값 중 가장 작은 값의 index
    private static int lowerBound(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;

        if (key < list.get(low)) {
            return -1;
        }
        if (key > list.get(high)) {
            return list.size();
        }

        while (low < high) {
            int mid = (low + high) / 2;

            if (key <= list.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    // key 초과의 값 중 가장 작은 값의 index
    private static int upperBound(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;

        if (key < list.get(low)) {
            return -1;
        }
        if (key > list.get(high)) {
            return list.size();
        }

        while (low < high) {
            int mid = (low + high) / 2;

            if (key < list.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
