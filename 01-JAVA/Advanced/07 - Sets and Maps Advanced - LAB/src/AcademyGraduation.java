import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AcademyGraduation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfStudents = Integer.parseInt(sc.nextLine());

        Map<String,Double[]> graduationList = new TreeMap<>();
        for (int i = 0; i < numberOfStudents; i++) {
            String name = sc.nextLine();
            String[] scoresStrings = sc.nextLine().split(" ");
            Double[] scores = new Double[scoresStrings.length];

            for (int j = 0; j < scoresStrings.length; j++) {
                scores[j] = Double.parseDouble(scoresStrings[j]);
            }
            graduationList.put(name, scores);
        }

        graduationList.entrySet().stream()
                .forEach(x-> {
                    double avrg = 0.0;
                    for (int i = 0; i < x.getValue().length; i++) {
                        avrg+= x.getValue()[i];
                    }
                    avrg /= x.getValue().length;
                    System.out.println(String.format("%s is graduated with %s", x.getKey(),
                            new DecimalFormat("0.###############").format(avrg)));
                });
    }
}
