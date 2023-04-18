package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import pl.oleksii.ClientSettings.Client;
import pl.oleksii.FunctionalityInterface.Functionality;

import java.io.IOException;

import static pl.oleksii.ATMFunctions.FunctionClassesOfATM.WriterLogger.*;
import static pl.oleksii.Main.printSymbols;

public class GetterMoney implements Functionality<Double, Client> {
    @Override
    public void init(Client client, Double sum) throws InterruptedException, IOException {
        moneyToGet(client, sum);
    }

    private void moneyToGet(Client client, Double sum) throws InterruptedException, IOException {
        if (client.getMoney() >= sum) {
            client.setMoney(client.getMoney() - sum);
            printSymbols();
            Thread.sleep(1000);
            System.out.println("                     ***Success***");
            System.out.println("**Now your balance is: " + client.getMoney() + "zl");
            printSymbols();
            AddTransactions(client.getId(), "PLN " + sum + " has been withdrawn from your balance");
            rewriterToChangeMoney(client.getId(), client.getMoney());
        } else {
            printToInfoAboutNoMoney(client, sum);
        }
    }

    public void printToInfoAboutNoMoney(Client client, double sum) throws IOException {
        printSymbols();
        System.out.println("                      **Failed**");
        System.out.println("There is not enough money in your account to get...");
        System.out.println("Your balance is: " + client.getMoney() + "zl, you need to grow up account balance on: " + Math.abs(client.getMoney() - sum) + "zl");
        AddTransactions(client.getId(), "Withdrawal error (not enough money)");
        printSymbols();
    }
}
