import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Test4 {

    public static void main(String[] args) {
        Function<Integer, Boolean> test3 = i -> i % 3 == 0;
        Function<Integer, Boolean> test5 = i -> i % 5 == 0;
        BiFunction<Boolean, String, String> testMsg = (bool, s) -> bool ? s : "";


        IntStream.rangeClosed(0, 100).forEach(x -> {
            StringBuilder msg = new StringBuilder();

            msg.append(testMsg.apply(test3.apply(x), "fizz"));
            msg.append(testMsg.apply(test5.apply(x), "buzz"));
            msg.append(testMsg.apply(msg.toString().isEmpty(), String.valueOf(x)));

            System.out.println(msg);
        });
    }
}
