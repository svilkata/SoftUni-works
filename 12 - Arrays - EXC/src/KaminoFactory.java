import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KaminoFactory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nSizeofArr = Integer.parseInt(sc.nextLine());

        String[] currentArr = new String[nSizeofArr];

        int bestStartIndexCurrentLineCurrentConsequtive = -1;
        int bestLengthOnes = 0;
        int sumCurrentLine = 0;
        int countInputs = 0;

        List<String> currentLineBests = new ArrayList<String>();


        String currentInput = sc.nextLine().replaceAll("!+", "");
        while (!currentInput.equals("Clone them")) {
            countInputs++;
            currentArr = currentInput.split("");

            for (int i = 0; i < currentArr.length - 1; i += bestLengthOnes) {
                if (currentArr[i].equals("0")) {
                    bestLengthOnes = 1;
                    continue;
                }

                // когато i-ият елемент е 1
                bestStartIndexCurrentLineCurrentConsequtive = i;
                bestLengthOnes = 1;
                for (int j = i + 1; j < currentArr.length; j++) {
                    if (currentArr[j].equals("0")) {
                        break;
                    } else {
                        bestLengthOnes++;
                    }
                }

                //sumCurrentLine+= bestLengthOnes;

                currentLineBests.add(countInputs + "" + bestStartIndexCurrentLineCurrentConsequtive + "" + bestLengthOnes);
            } //end of current line operations


            currentInput = sc.nextLine().replaceAll("!+", "");
            bestStartIndexCurrentLineCurrentConsequtive = -1;
            bestLengthOnes = 0;
//            sumCurrentLine = 0;
        }


        String[] check = new String[currentLineBests.size()];
        int maxLengthCheck = 0;

        for (int i = 1; i <= countInputs; i++) {


            for (int i1 = 0; i1 < currentLineBests.size(); i1++) {
                String el = currentLineBests.get(i1);
                if (Integer.parseInt((el.charAt(0) + "")) == i) {

                }
            }


        }


    }


        //System.out.println("End of program");

}


