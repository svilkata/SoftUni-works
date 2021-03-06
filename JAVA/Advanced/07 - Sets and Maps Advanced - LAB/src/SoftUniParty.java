import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUniParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String guest = sc.nextLine();

        Set<String> regular = new TreeSet<>();
        Set<String> vip = new TreeSet<>();

        while (!guest.equals("PARTY")) {
            char firstChar = guest.charAt(0);

            if (Character.isDigit(firstChar)) {
                //VIP
                vip.add(guest);
            } else {
                //regular
                regular.add(guest);
            }

            guest = sc.nextLine();
        }

        guest = sc.nextLine();
        Set<String> arrivedGuests = new LinkedHashSet<>();

        while (!guest.equals("END")) {
            arrivedGuests.add(guest);
            vip.remove(guest);
            regular.remove(guest);

            guest = sc.nextLine();
        }

//        vip.removeAll(arrivedGuests);
//        regular.removeAll(arrivedGuests);

        System.out.println(vip.size() + regular.size());

        for (String g : vip) {
            System.out.println(g);
        }

        for (String rg : regular) {
            System.out.println(rg);
        }
    }
}
