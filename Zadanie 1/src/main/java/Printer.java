import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Printer {
    public void printToCsv (List<Record> listToPrint, String filename) throws IOException
    {
        try (PrintWriter pw  = new PrintWriter(new File(filename)))
        {
            StringBuilder sb = new StringBuilder();
            sb.append("kontakt_id,klient_id,pracownik_id,status,kontakt_ts");
            for(Record r : listToPrint)
            {
                sb.append(r.toString());
            }
            pw.write(sb.toString());
        }
        System.out.println("File saved");
    }
}
