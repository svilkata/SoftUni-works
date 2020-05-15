import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Judge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        {username} -> {contest} -> {points}
        LinkedHashMap<String, LinkedHashMap<String, Integer>> contestsAll = new LinkedHashMap<>();
        //TreeMap<String, Integer> everyUserTotalPoints = new TreeMap<>();
        String input = sc.nextLine();

        while (!"no more time".equals(input)) {
            String[] tokens = input.split(" -> ");
            String username = tokens[0];
            String contest = tokens[1];
            int points = Integer.parseInt(tokens[2]);

            if (!contestsAll.containsKey(contest)) { //когато няма все още такъв contest
                contestsAll.put(contest, new LinkedHashMap<>()); //щом няма такъв contest, то няма и такъв username за него
                contestsAll.get(contest).put(username, points);
            } else { // ако има такъв contest
                if (!contestsAll.get(contest).containsKey(username)) {//когато няма все още такъв username
                    contestsAll.get(contest).put(username, points);
                } else { // имаме такъв username за този contest - правим score check
                    int oldPointsUserAllContests = contestsAll.get(contest).get(username);
                    if (oldPointsUserAllContests < points) {
                        contestsAll.get(contest).put(username, points);
                    }
                }
            }

            input = sc.nextLine();
        }

//        {constestName}: {participants.Count} participants
//        {position}. {username} <::> {points}
        final AtomicInteger br = new AtomicInteger(1);
        contestsAll
                .entrySet()
                .forEach(contnt -> {
                    System.out.println(String.format("%s: %d participants", contnt.getKey(), contnt.getValue().size()));

                    br.getAndSet(1);
                    contnt.getValue()
                            .entrySet()
                            .stream()
                            .sorted((user1, user2) -> {
                                int result = Integer.compare(user2.getValue(), user1.getValue());
                                if (result == 0) {
                                    result = user1.getKey().compareTo(user2.getKey());
                                    if (result == 0) {
                                        return  0;
                                    }
                                }
                                return result;
                            }).forEach(s -> {
                        System.out.printf("%d. %s <::> %d%n", br.getAndIncrement(), s.getKey(), s.getValue());
                    });
                });

        LinkedHashMap<String, Integer> standings = new LinkedHashMap<>();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> contestName : contestsAll.entrySet()) {
            for (Map.Entry<String, Integer> userName : contestName.getValue().entrySet()) {
                standings.putIfAbsent(userName.getKey(), 0);
                standings.put(userName.getKey(), userName.getValue() + standings.get(userName.getKey()));
            }
        }

        System.out.println("Individual standings:");
        br.set(1);

        standings
                .entrySet()
                .stream()
                .sorted((user1, user2) -> {
                    int result = Integer.compare(user2.getValue(), user1.getValue());
                    if (result == 0) {
                        result = user1.getKey().compareTo(user2.getKey());
                    }
                    return result;
                })
                .forEach(s -> {
                    System.out.printf("%d. %s -> %d%n", br.getAndIncrement(), s.getKey(), s.getValue());
                });


    }
}
