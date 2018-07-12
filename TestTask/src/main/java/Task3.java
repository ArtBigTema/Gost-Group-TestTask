import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Task3 {

    private void calculate() throws URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(
                getClass().getClassLoader().getResource("text.txt")).toURI());

        Comparator<Map.Entry<String, Long>> comparator = Collections.reverseOrder(Map.Entry.comparingByValue());

        Pattern pattern = Pattern.compile("[^a-z]");

        try (Stream<String> stream = Files.lines(path)) {

            Map<String, Long> collect = stream
                    .map(x -> Stream.of(x.toLowerCase().split(" ")))
                    .flatMap(Function.identity())
                    .filter(x -> !x.isEmpty())
                    .map(x -> pattern.matcher(x).replaceAll(""))
                    .collect(groupingBy(Function.identity(), counting()));

            collect.entrySet().stream().sorted(comparator).forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        new Task3().calculate();
    }
}
