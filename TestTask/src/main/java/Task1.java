import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Task1 {
    private IntPredicate divBy(int number) {
        return i -> i % number == 0;
    }

    private String twoSeven(int number) {
        String result = "";
        result += divBy(2).test(number) ? "Two" : "";
        result += divBy(7).test(number) ? "Seven" : "";
        return result.isEmpty() ? String.valueOf(number) : result;
    }

    private void calculate() {
        IntStream.rangeClosed(1, 100).boxed().map(this::twoSeven).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new Task1().calculate();
    }
}
