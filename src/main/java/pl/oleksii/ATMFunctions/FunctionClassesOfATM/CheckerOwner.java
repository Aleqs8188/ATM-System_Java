package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import pl.oleksii.ClientSettings.Client;
import pl.oleksii.FunctionalityInterface.Functionality;

import static pl.oleksii.ATMFunctions.FunctionClassesOfATM.JsonRW.clients;

public class CheckerOwner implements Functionality<Integer, Client> {

    public Client checker(Integer integer) {
        for (Client client : clients.getClients()) {
            if (client.getNumberCard() == integer) {
                return client;
            } else if (client.getPinCode() == integer) {
                return client;
            }
        }
        Client empty = new Client();
        empty.setId(-1);
        return empty;
    }

    @Override
    public Client init(Integer integer) {
        return checker(integer);
    }
}
