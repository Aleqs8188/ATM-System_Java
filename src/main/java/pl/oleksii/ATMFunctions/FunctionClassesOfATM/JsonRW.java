package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import pl.oleksii.ClientSettings.Client;
import pl.oleksii.ClientSettings.ClientList;
import pl.oleksii.FunctionalityInterface.Functionality;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class JsonRW implements Functionality<String, Boolean> {
    private static ObjectMapper objectMapper;
    public static Gson gson = new Gson();
    public static String json;

    static {
        try {
            json = readJsonFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClientList clients = gson.fromJson(json, ClientList.class);

    public static String readJsonFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/pl/oleksii/Json/info.json"));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }

    public static void writeJsonFileToChangeMoney(int clientID, double newMoney) throws IOException {
        objectMapper = new ObjectMapper();
        Optional<Client> clientToUpdate = clients.getClients().stream().filter(c -> c.getId() == clientID).findFirst();
        clientToUpdate.ifPresent(c -> c.setMoney(newMoney));
        objectMapper.writeValue(new File("src/main/java/pl/oleksii/Json/info.json"), clients);
    }
    public static void writerToAddTransactions(int clientID, String str) throws IOException {
        objectMapper = new ObjectMapper();
        Optional<Client> clientToUpdate = clients.getClients().stream().filter(c -> c.getId() == clientID).findFirst();
        clientToUpdate.ifPresent(c -> c.getTransactions().add(str));
        objectMapper.writeValue(new File("src/main/java/pl/oleksii/Json/info.json"), clients);
    }

    @Override
    public Boolean init(String str) throws IOException {
        readJsonFile();
        return true;
    }
}
