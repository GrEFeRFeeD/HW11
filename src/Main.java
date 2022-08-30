import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {


        List<String> names = List.of("Ivan", "Peter", "Vladislav", "Olga", "Mikita", "Zina", "Alexandr");
        System.out.println("Names list: " + names);

        // Task 1
        Stream<String> formattedNamesStream = names.stream()
                .filter(s -> names.indexOf(s) % 2 == 0)
                .map(s -> (names.indexOf(s) + 1) + ". " + s);
        String formattedNames = formattedNamesStream.collect(Collectors.joining(", "));
        System.out.println("Task 1: " + formattedNames);

        // Task 2
        Stream<String> sortedCapsStream = names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder());
        List<String> sortedCapsNames = sortedCapsStream.collect(Collectors.toList());
        System.out.println("Task 2: " + sortedCapsNames);


        // Task 3
        String[] givenArray = new String[]{"1, 2, 0", "4, 5"};
        Stream<Integer> sortedNumbersStream = Arrays.stream(givenArray)
                .flatMap(s -> Stream.of(s.split(", ")))
                .map(Integer::parseInt)
                .sorted();
        List<Integer> sortedNumbers = sortedNumbersStream.collect(Collectors.toList());
        System.out.println("Given array: " + Arrays.toString(givenArray));
        System.out.println("Task 3: " + sortedNumbers);

        // Task 4
        long a = 25214903917L, c = 11, m = 2 ^ 48;
        Stream<Long> infiniteStream = getInfiniteStream(5, a, c, m, 10);
        System.out.println("Task 4: infinite stream of 10 elements with seed 5: " + infiniteStream.collect(Collectors.toList()));
        infiniteStream = getInfiniteStream(10, a, c, m, 10);
        System.out.println("Task 4: infinite stream of 10 elements with seed 10: " + infiniteStream.collect(Collectors.toList()));

        // Task 5
        List<Integer> listA = List.of(1, 3, 5, 7, 9);
        List<Integer> listB = List.of(0, 2, 4, 6, 8);
        System.out.println("List a: " + listA);
        System.out.println("List b: " + listB);
        Stream<Integer> shuffledMergedStream = zip(listA.stream(), listB.stream());
        List<Integer> shuffledMergedList = shuffledMergedStream.collect(Collectors.toList());
        System.out.println("Task 5: " + shuffledMergedList);

    }

    public static Stream<Long> getInfiniteStream(long seed, long a, long c, long m, long size) {
        return Stream.iterate(seed, x -> (a * x + c) % m).limit(size);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> mergedStreamsList = Stream.concat(first, second).collect(Collectors.toList());
        Collections.shuffle(mergedStreamsList);
        return mergedStreamsList.stream();
    }
}
