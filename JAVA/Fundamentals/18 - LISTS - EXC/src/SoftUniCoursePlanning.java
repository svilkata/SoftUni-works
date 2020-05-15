import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SoftUniCoursePlanning {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> courseSchedule = Arrays.stream(sc.nextLine().split(", ")).collect(Collectors.toList());

        //System.out.println(courseSchedule);

        String input = sc.nextLine();

        while (!"course start".equals(input)) {
            String[] token = input.split(":");

            switch (token[0]) {
                case "Add": {
                    if (!courseSchedule.contains(token[1])) {
                        courseSchedule.add(token[1]);
                    }
                    break;
                }
                case "Insert": {
                    if (!courseSchedule.contains(token[1])) {
                        int index = Integer.parseInt(token[2]);
                        courseSchedule.add(index, token[1]);
                    }
                    break;
                }
                case "Remove": {
                    if (courseSchedule.contains(token[1])) {
                        String titleToRemove = token[1];
                        String excToRemove = titleToRemove + "-Exercise";
                        while (courseSchedule.contains(titleToRemove)) {
                            courseSchedule.remove(titleToRemove);
                            courseSchedule.remove(excToRemove);
                        }
                    }
                    break;
                }
                case "Swap": {
                    String firstSwapEl = token[1];
                    String firstSwapElEXC = firstSwapEl + "-Exercise";
                    String secondSwapEl = token[2];
                    String secondSwapElEXC = secondSwapEl + "-Exercise";

                    if (courseSchedule.contains(firstSwapEl) && courseSchedule.contains(secondSwapEl)) { // ако изобщо ще сменяме
                        if (!courseSchedule.contains(firstSwapElEXC) && !courseSchedule.contains(secondSwapElEXC)) { //ако сменяме само лекции - ok
                            int firstSwapIndex = courseSchedule.indexOf(firstSwapEl);
                            int secondSwapIndex = courseSchedule.indexOf(secondSwapEl);
                            courseSchedule.set(firstSwapIndex, secondSwapEl);
                            courseSchedule.set(secondSwapIndex, firstSwapEl);
                        } else { // ако сменяме частично или напълно и наличните упражниения
                            if (courseSchedule.contains(firstSwapElEXC) && courseSchedule.contains(secondSwapElEXC)) {//пълно - ok
                                int firstSwapIndexLEC = courseSchedule.indexOf(firstSwapEl);
                                int firstSwapIndexEXC = firstSwapIndexLEC + 1;
                                int secondSwapIndexLEC = courseSchedule.indexOf(secondSwapEl);
                                int secondSwapIndexEXC = secondSwapIndexLEC + 1;

                                courseSchedule.set(firstSwapIndexLEC, secondSwapEl);
                                courseSchedule.set(firstSwapIndexEXC, secondSwapElEXC);
                                courseSchedule.set(secondSwapIndexLEC, firstSwapEl);
                                courseSchedule.set(secondSwapIndexEXC, firstSwapElEXC);
                            } else if (courseSchedule.contains(firstSwapElEXC)) {
                                courseSchedule.remove(firstSwapElEXC);

                                int firstSwapIndexLEC = courseSchedule.indexOf(firstSwapEl);
                                int secondSwapIndexLEC = courseSchedule.indexOf(secondSwapEl);

                                courseSchedule.set(firstSwapIndexLEC, secondSwapEl);
                                courseSchedule.set(secondSwapIndexLEC, firstSwapEl);
                                courseSchedule.add(secondSwapIndexLEC+1, firstSwapElEXC);
                            } else if (courseSchedule.contains(secondSwapElEXC)) {
                                courseSchedule.remove(secondSwapElEXC);

                                int firstSwapIndexLEC = courseSchedule.indexOf(firstSwapEl);
                                int secondSwapIndexLEC = courseSchedule.indexOf(secondSwapEl);

                                courseSchedule.set(firstSwapIndexLEC, secondSwapEl);
                                courseSchedule.set(secondSwapIndexLEC, firstSwapEl);
                                courseSchedule.add(firstSwapIndexLEC+1, secondSwapElEXC);
                            }
                        }
                    }
                    break;
                }
                case "Exercise": {
                    String lectionToAdd = token[1];
                    String excersizeToAdd = token[1] + "-" + token[0];
                    if (!courseSchedule.contains(lectionToAdd)) { //ако не съдържа заглавието
                        courseSchedule.add(lectionToAdd);
                        courseSchedule.add(excersizeToAdd);
                    } else { //ако съдържа заглавието, но не и упражнението
                        if (courseSchedule.contains(lectionToAdd) && !courseSchedule.contains(excersizeToAdd)) {
                            int indexToAddExcersize = courseSchedule.indexOf(lectionToAdd) + 1;
                            courseSchedule.add(indexToAddExcersize, excersizeToAdd);
                        }
                    }
                }
                break;
            }
            input = sc.nextLine();
        }


        printSchedule(courseSchedule);

    }

    private static void printSchedule(List<String> courseSchedule) {
        for (int i = 0; i < courseSchedule.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, courseSchedule.get(i)));
        }
    }
}
