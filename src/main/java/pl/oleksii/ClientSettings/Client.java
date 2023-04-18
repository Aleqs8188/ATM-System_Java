package pl.oleksii.ClientSettings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    private int id;
    private String name;
    private String surname;
    private int numberCard;
    private int pinCode;
    private double money;
    private ArrayList<String> transactions = new ArrayList<>();

    public ArrayList<String> getTransactions() {
        return transactions;
    }


    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String printHelloToClient() {
        int newNumberCard = getNumberCard();
        newNumberCard /= 10000;
        String formattedNumber = newNumberCard + "**";

        int newPinOfCard = getPinCode();
        newPinOfCard /= 100;
        String formattedPin = newPinOfCard + "**";
        return "Hello " + getName() + " " + getSurname() + "!" + " Your number of card are: " + formattedNumber + ". And your pin is: " + formattedPin + "\nYour balance of money on your card are: " + getMoney();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && numberCard == client.numberCard && pinCode == client.pinCode && Double.compare(client.money, money) == 0 && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(transactions, client.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, numberCard, pinCode, money, transactions);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", numberCard=" + numberCard +
                ", pinCode=" + pinCode +
                ", money=" + money +
                ", transactions=" + transactions +
                '}';
    }
}
