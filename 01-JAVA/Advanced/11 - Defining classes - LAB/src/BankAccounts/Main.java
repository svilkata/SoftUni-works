package BankAccounts;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static class BankAccount {
        private static int idCounter = 1;
        private int id;
        private double balance;
        private static double interestRate; //споделен лихвен процент за всички банкови сметки

        public BankAccount() {
            this.id = BankAccount.idCounter;
            BankAccount.idCounter++;
            this.interestRate = 0.02;
            System.out.println("Account ID" + this.id + " created");
        }

        public static void setInterestRate(double interest) {
            BankAccount.interestRate = interest;
        }

        public double getInterest(int years) {
            return (this.balance * this.interestRate) * years;
        }

        public void deposit(double amount) {
            this.balance += amount;
            System.out.printf("Deposited %s to ID%d%n", new DecimalFormat("0.######").format(amount), this.id);
        }

        public int getID() {
            return this.id;
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        Map<Integer, BankAccount> bankAccounts = new HashMap<>();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            if (command.equals("Create")) {
                BankAccount bankAccount = new BankAccount();
                bankAccounts.put(bankAccount.getID(), bankAccount);

            } else if (command.equalsIgnoreCase("Deposit")) {
                int id = Integer.parseInt(tokens[1]);
                if (bankAccounts.containsKey(id)) {
                    BankAccount bankAccount = bankAccounts.get(id);
                    bankAccount.deposit(Double.parseDouble(tokens[2]));
                } else {
                    System.out.println("Account does not exist");
                }
            } else if (command.equals("SetInterest")) {
                double newInterest = Double.parseDouble(tokens[1]);
                BankAccount.setInterestRate(newInterest);

            } else { //GetInterest
                int id = Integer.parseInt(tokens[1]);
                if (bankAccounts.containsKey(id)) {
                    BankAccount bankAccount = bankAccounts.get(id);
                    System.out.printf("%.2f%n",
                            bankAccount.getInterest(Integer.parseInt(tokens[2])));
                } else {
                    System.out.println("Account does not exist");
                }
            }

            input = sc.nextLine();
        }

    }
}
