import java.util.HashMap;
import java.util.Map;

public class MiniQuiz3 {

    public static void hitungKemunculan(int[] array) {
        Map<Integer, Integer> memo = new HashMap<>();

        for (int e: array) {
            memo.put(e, memo.getOrDefault(e, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> tuple: memo.entrySet()) {
            System.out.printf(
                "Bilangan %d muncul sebanyak %d kali\n",
                tuple.getKey(),
                tuple.getValue());
        }
    }
    public static void main(String[] args) {
    }
}
