import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Record> records;
        Loader loader = new Loader();
        loader.readFile("statuses.json");
        String data = "01-07-2017";
        records=loader.filterBefore(data);
        records=loader.sortList(records);
        Printer printer = new Printer();
        printer.printToCsv(records, "filtered.csv");
    }
}
