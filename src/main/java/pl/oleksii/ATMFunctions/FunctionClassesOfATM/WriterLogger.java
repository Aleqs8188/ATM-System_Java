package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import java.io.IOException;

import static pl.oleksii.ATMFunctions.FunctionClassesOfATM.JsonRW.*;

public class WriterLogger {
    public static void rewriterToChangeMoney(int clientID, double Money) throws IOException {
        writeJsonFileToChangeMoney(clientID, Money);
    }
    public static void AddTransactions(int clientID, String str) throws IOException {
        writerToAddTransactions(clientID, str);
    }
}
