import java.util.Scanner;

public class ProjectsCreation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String nameArchitect = sc.nextLine();
        int numberOfProjects = Integer.parseInt(sc.nextLine());

        System.out.printf("The architect %s will need %d hours to complete %d project/s.",
                nameArchitect, 3 * numberOfProjects, numberOfProjects);
    }
}
