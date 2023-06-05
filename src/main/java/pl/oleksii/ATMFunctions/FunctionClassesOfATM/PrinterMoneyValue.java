package pl.oleksii.ATMFunctions.FunctionClassesOfATM;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class PrinterMoneyValue implements Runnable {
    Document page;
    Element tableWth;
    Elements valueToBuy;
    Elements valueOfValue;
    Elements valueOfMoney;
    Elements valueOfCountry;
    Elements valueOfSymbols;
    String separator = "+----------------------+----------------------+----------------------+--------+";

    private org.jsoup.nodes.Document getPage() throws IOException {
        String url = "https://wyborcza.biz/Waluty/0,111138,8932151,,,Kursy_srednie_walut_NBP,A.html?disableRedirects=true";
        return Jsoup.parse(new URL(url), 3000);
    }

    public void getTheExchangeRate(String str) throws IOException {
        page = getPage();
        tableWth = page.select("table[class=biz_table]").first();
        assert tableWth != null;
        valueToBuy = tableWth.select(str);
        valueOfValue = valueToBuy.select("td[class=left currency]");
        valueOfCountry = valueToBuy.select("td[class=left country]");
        valueOfSymbols = valueToBuy.select("td[class=left symbol]");
        valueOfMoney = valueToBuy.select("td[class=right average]");

        String row = String.format("| %-20s | %-20s | %-20s | %-5s |", valueOfCountry.text(), valueOfValue.text(), valueOfSymbols.text(), valueOfMoney.text());
        System.out.println(separator);
        System.out.println(row);
    }

    public void printTableWithValues() throws IOException {
        System.out.println("                          * Table with rates: *");
        System.out.println(separator);
        String header = String.format("| %-20s | %-20s | %-20s | %-6s |", "Country: ", "Value: ", "Symbols: ", "Rate: ");
        System.out.println(header);
        getTheExchangeRate("tr[id=tr_UAH]");
        getTheExchangeRate("tr[id=tr_EUR]");
        getTheExchangeRate("tr[id=tr_USD]");
        getTheExchangeRate("tr[id=tr_CAD]");
        getTheExchangeRate("tr[id=tr_CZK]");
        getTheExchangeRate("tr[id=tr_JPY]");
        System.out.println(separator);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                printTableWithValues();
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
