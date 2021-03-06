import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppliedArithmetics {
    private static List<Integer> numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = sc.nextLine();
//            "add" -> adds 1; "multiply" -> multiplies by 2; "subtract" -> subtracts 1; "print"
        Map<String, Function<List<Integer>, List<Integer>>> functionMap = new HashMap<>();
        functionMap.put("add", e -> e.stream().map(val -> val + 1).collect(Collectors.toList()));
        functionMap.put("multiply", e -> e.stream().map(val -> val * 2).collect(Collectors.toList()));
        functionMap.put("subtract", e -> e.stream().map(val -> val - 1).collect(Collectors.toList()));
        functionMap.put("print", e -> e.stream().peek(z -> System.out.print(z + " ")).collect(Collectors.toList()));

        handleInput(functionMap, sc, input);

//        while (!"end".equals(input)) {
//            numbers = functionMap.get(input).apply(numbers);
//            input = sc.nextLine();
//        }

    }

    private static void handleInput(Map<String, Function<List<Integer>, List<Integer>>> functionMap, Scanner sc, String input) {
        if (input.equals("end")) {
            return;
        }

        numbers = functionMap.get(input).apply(numbers);
        if (input.equals("print")) {
            System.out.println();
        }
        input = sc.nextLine();

        handleInput(functionMap, sc, input);
    }
}
