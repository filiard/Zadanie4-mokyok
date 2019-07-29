import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Loader {
    FileReader fr;
    String fileContent = "";
    Status status = null;

    private String loadSourceFile (String sourceFile)
    {
        try
        {
            fr = new FileReader(sourceFile);
        } catch (FileNotFoundException e)
        {
            System.out.println("File '"+sourceFile+"' not found!");
            System.exit(1);
        }
        BufferedReader br = new BufferedReader(fr);
        String lastLine;
        try
        {
            StringBuilder sb = new StringBuilder();
            while((lastLine = br.readLine())!=null)
            {
                fileContent = sb.append(lastLine).toString();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("File loaded");
        return fileContent;
    }

    public void readFile(String filename)
    {

        ObjectMapper mapper = new ObjectMapper();
        Status status=null;
        try
        {
            fileContent = loadSourceFile(filename);
            status = mapper.readValue(fileContent, Status.class);
        }
        catch (IOException e)
            {
            e.printStackTrace();
            }
        this.status =status;
        System.out.println("File read");
    }

    public List<Record> filterBefore(String dateBefore)
    {
        Date date=null;
        SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = format.parse(dateBefore);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Record> filteredRecords = new ArrayList<>();

        List<Record> baseList = status.getRecords();
        for (Record r : baseList)
        {
            if(r.getKontakt_ts().compareTo(date)>=0)
            {
                filteredRecords.add(r);
            }
        }
        System.out.println("File filtered");
        return filteredRecords;
    }

    public List<Record> sortList (List<Record> listToSort)
    {
        Collections.sort(listToSort, new RecordComparator());
        System.out.println("File sorted");
        return listToSort;
    }
}
