package pl.oleksii;

import pl.oleksii.ATMFunctions.FunctionClassesOfATM.*;
import pl.oleksii.ClientSettings.Client;

import java.io.IOException;
import java.util.Scanner;

import static pl.oleksii.ATMFunctions.ATMFunctions.functionalityList;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        functionalityList.add(new CheckerOwner());
        functionalityList.add(new GetterMoney());
        functionalityList.add(new PutterMoney());
        functionalityList.add(new PrintAllTransactions());

        while (true) {
            Scanner scan = new Scanner(System.in);
            Client client = null;
            int numberOfCard;
            int pinOfCard;
            int choice;
            printSymbols();
            System.out.println("                 Hey, you are in ATM!");
            System.out.println("If you want to start, you need to enter your number of card and your pin.");
            System.out.print("Enter your number of card: ");
            numberOfCard = scan.nextInt();
            Client clientWithNumberOfCard = (Client) functionalityList.get(0).init(numberOfCard);
            System.out.print("Enter your pin of this card: ");
            pinOfCard = scan.nextInt();
            if (clientWithNumberOfCard.getNumberCard() == numberOfCard && clientWithNumberOfCard.getPinCode() == pinOfCard) {
                client = clientWithNumberOfCard;
                printSymbols();
                System.out.println("                     ***Success***");
                System.out.println(client.printHelloToClient());
            } else if (clientWithNumberOfCard.getId() == -1 || numberOfCard == pinOfCard) {
                printSymbols();
                System.out.println("This card is not variable!");
                System.out.println("Try again...");
                return;
            }
            while (true) {
                System.out.println("What do you want to do? : ");
                System.out.println("1) Get Money    ||    2) Put Money    ||    3) Check transactions    ||    4) Exchange Rates    ||    5) Exit...");
                System.out.print("Enter your choice: ");
                choice = scan.nextInt();
                printSymbols();
                assert client != null;
                switch (choice) {
                    case 1 -> {
                        int choiceToGetMoney;
                        printBalanceOfMoney(client, "Now");
                        System.out.println("Please enter amount of money: ");
                        System.out.println("1) 50zl    ||    2) 100zl    ||    3) 200zl    ||    4) 400zl    ||    5) Or your value...    ||    6) Back to menu...");
                        choiceToGetMoney = scan.nextInt();
                        switch (choiceToGetMoney) {
                            case 1 -> functionalityList.get(1).init(client, 50.0);
                            case 2 -> functionalityList.get(1).init(client, 100.0);
                            case 3 -> functionalityList.get(1).init(client, 200.0);
                            case 4 -> functionalityList.get(1).init(client, 400.0);
                            case 5 -> {
                                System.out.print("Please enter amount of money do you want to get: ");
                                double moneyToGet = scan.nextDouble();
                                functionalityList.get(1).init(client, moneyToGet);
                            }
                        }
                    }
                    case 2 -> {
                        printBalanceOfMoney(client, "Now");
                        System.out.print("Please enter amount of money: ");
                        double amountOfMoney = scan.nextDouble();
                        functionalityList.get(2).init(client, amountOfMoney);
                    }
                    case 3 -> functionalityList.get(3).init(client);
                    case 4 -> {
                        Thread threadToPrintTableWithRates = new Thread(new PrinterMoneyValue());
                        threadToPrintTableWithRates.start();
                        Thread.sleep(3000);
                        threadToPrintTableWithRates.interrupt();
                    }
                    case 5 -> {
                        System.out.println("        ************* Exit Success **************");
                        return;
                    }
                }
            }
        }
    }

    public static void printSymbols() {
        System.out.println("*******************************************************************************");
    }

    public static void printBalanceOfMoney(Client client, String str) {
        System.out.println("**" + str + " your balance is: " + client.getMoney() + "zl");
    }
}