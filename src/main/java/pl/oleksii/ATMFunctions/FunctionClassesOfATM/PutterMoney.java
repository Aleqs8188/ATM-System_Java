package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import pl.oleksii.ClientSettings.Client;
import pl.oleksii.FunctionalityInterface.Functionality;

import java.io.IOException;

import static pl.oleksii.ATMFunctions.FunctionClassesOfATM.WriterLogger.*;
import static pl.oleksii.Main.printBalanceOfMoney;
import static pl.oleksii.Main.printSymbols;

public class PutterMoney implements Functionality<Double, Client> {
    @Override
    public void init(Client client, Double sum) throws InterruptedException, IOException {
        moneyToPut(client, sum);
    }

    private void moneyToPut(Client client, double sum) throws InterruptedException, IOException {
        client.setMoney(client.getMoney() + sum);
        printSymbols();
        Thread.sleep(1000);
        System.out.println("                     ***Success***");
        printBalanceOfMoney(client, "Now");
        AddTransactions(client.getId(), "PLN " + sum + " has been add on your balance");
        rewriterToChangeMoney(client.getId(), client.getMoney());
        printSymbols();
    }
}
