package TrafficLights;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TrafficLight[] trafficLightsArrayToChange = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(e -> TrafficLight.valueOf(e)) //mapping to Enum
                .toArray(TrafficLight[]::new);

        int n = sc.nextInt();

        TrafficLight[] lightsEnum = TrafficLight.values();

        while (n-- > 0) {
            for (int i = 0; i < trafficLightsArrayToChange.length; i++) {
                int index = (trafficLightsArrayToChange[i].ordinal() + 1) % lightsEnum.length; //гарантираме, че не излизаме от масива
//                if (index >= lights.length) {
//                    index = 0;
//                }
                trafficLightsArrayToChange[i] = lightsEnum[index];

                System.out.print(trafficLightsArrayToChange[i].toString() + " ");
            }
            System.out.println();
        }

    }
}
