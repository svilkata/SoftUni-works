import java.util.*;

public class Ranking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        {contest}:{password for contest}
        HashMap<String, String> contestMap = new HashMap<>();
        String input = sc.nextLine();
        while (!"end of contests".equals(input)) {
            String[] tokens = input.split(":");
            String contest = tokens[0];
            String pass = tokens[1];
            if (!contestMap.containsKey(contest)) {
                contestMap.put(contest, pass);
            }

            input = sc.nextLine();
        }

//        {contest}=>{password}=>{username}=>{points}
        TreeMap<String, HashMap<String, Integer>> studentTotal = new TreeMap<>();
        // HashMap<String, Integer> subjectsPerStudent = new HashMap<>();
        input = sc.nextLine();
        while (!"end of submissions".equals(input)) {
            String[] tokens2 = input.split("=>");
            String subject = tokens2[0];
            String pass = tokens2[1];
            String username = tokens2[2];
            int points = Integer.parseInt(tokens2[3]);

            if (contestMap.containsKey(subject) && contestMap.containsValue(pass)) {
                if (!studentTotal.containsKey(username)) {//ако е напълно нов User
                    studentTotal.put(username, new HashMap<>());
                    studentTotal.get(username).put(subject, points); //това е първият предмет
                } else { // ако има вече такъв User, правим проверка за subject
                    if (!studentTotal.get(username).containsKey(subject)) { //ако няма предмет от този изпит до момента
                        studentTotal.get(username).put(subject, points);
                    } else { //ако има вече резултат от този изпит/предмет - правим проверка кои точки са повече за този предмет
                        int currJudgePoints = studentTotal.get(username).get(subject);
                        if (currJudgePoints < points) {
                            studentTotal.get(username).put(subject, points);
                        }
                    }
                }
            }

            input = sc.nextLine();
        }

        HashMap<Integer, String> maxPointsPerStudent = new HashMap<>();

        for (Map.Entry<String, HashMap<String, Integer>> stud : studentTotal.entrySet()) {
            //всички предмети за даден студент с макс точки
            int currSum = stud.getValue()
                    .entrySet()
                    .stream()
                    .mapToInt(x -> x.getValue())
                    .sum();

            maxPointsPerStudent.put(currSum, stud.getKey());
        }


        int maxPoints = maxPointsPerStudent
                .entrySet()
                .stream()
                .mapToInt(x -> x.getKey())
                .max()
                .getAsInt();

        System.out.println(String.format("Best candidate is %s with total %d points.",
                maxPointsPerStudent.get(maxPoints), maxPoints));

        System.out.println("Ranking:");
        studentTotal
                .entrySet()
//                .stream()
                .forEach(student -> {
                    System.out.println(student.getKey());

                    student.getValue()
                            .entrySet()
                            .stream()
                            .sorted((x1, x2) -> x2.getValue().compareTo(x1.getValue()))
                            .forEach(z -> {
                                System.out.println(String.format("#  %s -> %d", z.getKey(), z.getValue()));
                            });

                });

    }
}
