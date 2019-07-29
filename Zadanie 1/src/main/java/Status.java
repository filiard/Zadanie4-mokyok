import java.util.List;

public class Status {
    private int next_offset;
    private List<Record> records = null;

    public int getNext_offset() {
        return next_offset;
    }

    public void setNext_offset(int next_offset) {
        this.next_offset = next_offset;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
