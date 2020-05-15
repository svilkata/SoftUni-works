package SongsPckg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numS = Integer.parseInt(sc.nextLine());

        List<Songs> songsList = new ArrayList<>();

        for (int i = 0; i < numS; i++) {
            String[] data = sc.nextLine().split("_");

            Songs current = new Songs();
            current.setTypeList(data[0]);
            current.setName(data[1]);
            current.setTime(data[2]);

            songsList.add(current);
        }

        String typeList = sc.nextLine();

        if (typeList.equals("all")) {
            for (Songs songs : songsList) {
                System.out.println(songs.getName());
            }
        } else {
            for (Songs songs : songsList) {
                if (songs.getTypeList().equals(typeList)) {
                    System.out.println(songs.getName());
                }
            }
        }
        //System.out.println(songsList);

    }
}
