package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import pl.oleksii.ClientSettings.Client;
import pl.oleksii.FunctionalityInterface.Functionality;

import static pl.oleksii.Main.printSymbols;

public class PrintAllTransactions implements Functionality {
    @Override
    public void init(Client client) {
        printAllTransaction(client);
    }

    public void printAllTransaction(Client client) {
        int count = 1;
        for (String string : client.getTransactions()) {
            System.out.println(count + ") " + string);
            count++;
        }
        printSymbols();
    }
}
