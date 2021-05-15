import java.util.*;

public class WordSynonyms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.nextLine());

        Map<String, List<String>> map  = new LinkedHashMap<>();


        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();
            String syn = sc.nextLine();
//created map with Cute and ArrayList(0)
            map.putIfAbsent(word, new ArrayList<>()); //този ред се прескача реално
            map.get(word).add(syn);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.printf("%s - %s%n", entry.getKey(),
                    String.join(", ",entry.getValue()) );
        }

    }
}
