import java.util.Scanner;

public class HTMLtags {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String title = sc.nextLine();
        title = String.format("<h1>%n    %s%n</h1>%n", title);

        String content = sc.nextLine();
        content = String.format("<article>%n    %s%n</article>%n", content);

        String input = sc.nextLine();
        StringBuilder comments = new StringBuilder();
        while (!"end of comments".equals(input)){
            comments.append(String.format("<div>%n    %s%n</div>%n", input));

            input = sc.nextLine();
        }

        System.out.print(title);
        System.out.print(content);
        System.out.println(comments.toString());

    }
}
