import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cinema {
    public static String[] seats;
    public static String[] permutationsWithoutRepetitionsPeopleRemainedNotFixedSeats;
    public static List<String> people;
    public static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        people = Arrays.stream(sc.nextLine().split(", ")).collect(Collectors.toList());
        seats = new String[people.size()];

        String line = sc.nextLine();
        while (!line.equals("generate")) {
            String[] tokens = line.split(" - ");
            String name = tokens[0];
            int position = Integer.parseInt(tokens[1]) - 1;

            seats[position] = name;

            people.remove(name);

            line = sc.nextLine();
        }

        permutationsWithoutRepetitionsPeopleRemainedNotFixedSeats = new String[people.size()];
        used = new boolean[people.size()];
        permute(0);

    }

    private static void permute(int index) {
        if (index == permutationsWithoutRepetitionsPeopleRemainedNotFixedSeats.length) {
            print();
        } else {
            for (int i = 0; i < people.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    permutationsWithoutRepetitionsPeopleRemainedNotFixedSeats[index] = people.get(i);
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print() {
        int index = 0;
        String[] out = new String[seats.length];
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] != null) {
                out[i] = seats[i];
            } else {
                out[i] = permutationsWithoutRepetitionsPeopleRemainedNotFixedSeats[index++];
            }
        }

        System.out.println(String.join(" ", out));
    }
}
