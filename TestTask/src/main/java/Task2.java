import java.math.BigInteger;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task2 {
    private HashMap<Integer, BigInteger> cache;
    private Scanner scanner;

    private Task2() {
        cache = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    private boolean checker(int m, int r) {
        if (r <= m || r < 0 || m < 0) {
            System.out.print("неправильно!!! снова ");
            return false;
        }
        if (r > 30_000 || m > 30_000) {
            System.out.println(" (подождите) ");
            if (r > 70_000 || m > 70_000) {
                System.out.println(" (дольше обычного) ");
                if (r > 200_000 || m > 200_000) {
                    System.out.println("Хотя нет, ответа не будет");
                    return false;
                }
            }
        }
        return true;
    }

    private void calculate() {
        System.out.println("введите два числа");

        int m = scanner.nextInt();
        int r = scanner.nextInt();

        if (!checker(m, r)) {
            return;
        }

        System.out.print("ответ: ");
        System.out.println(combinations(r, m));
    }

    private long combinations(int m, int r) {
        BigInteger factorialN = factorial(m);
        BigInteger factorialK = factorial(r);
        BigInteger factorialNMinusK = factorial(m - r);
        return factorialN.divide(factorialK.multiply(factorialNMinusK)).longValue();
    }

    private BigInteger factorial(int n) {
        return n < 2_000 ? factorialRec(n) : factorialFor(n);
    }

    private BigInteger factorialRec(int n) {
        if (n == 0 || n == 1) return BigInteger.ONE;

        BigInteger ret;
        if (Objects.nonNull(ret = cache.get(n))) return ret;
        ret = BigInteger.valueOf(n).multiply(factorial(n - 1));
        cache.put(n, ret);
        return ret;
    }

    private BigInteger factorialFor(int n) {
        if (n == 0 || n == 1) return BigInteger.ONE;

        BigInteger ret;
        if (Objects.nonNull(ret = cache.get(n))) return ret;
        ret = BigInteger.ONE;

        for (int i = 1; i <= n; ++i) {
            ret = Optional.ofNullable(cache.get(i)).orElse(ret.multiply(BigInteger.valueOf(i)));
            if (n < 50_000) {
                cache.put(n, ret);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws InterruptedException {
        Task2 task2 = new Task2();
        IntStream.range(0, Integer.MAX_VALUE).forEach(i -> task2.calculate());
    }
}
