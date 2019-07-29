import java.util.Comparator;

public class RecordComparator implements Comparator<Record> {
    @Override
    public int compare(Record o1, Record o2) {
        int compareResult = o1.getKlient_id()-o2.getKlient_id();
        if (compareResult==0)
        {
            compareResult = o1.getKontakt_ts().compareTo(o2.getKontakt_ts());
        }
        return compareResult;
    }
}
