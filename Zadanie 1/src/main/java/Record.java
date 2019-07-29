import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Record {
    private int kontakt_id;
    private int klient_id;
    private int pracownik_id;
    private String status;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date kontakt_ts;

    public int getKontakt_id() {
        return kontakt_id;
    }

    public void setKontakt_id(int kontakt_id) {
        this.kontakt_id = kontakt_id;
    }

    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
    }

    public int getPracownik_id() {
        return pracownik_id;
    }

    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id = pracownik_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getKontakt_ts() {
        return kontakt_ts;
    }

    public void setKontakt_ts(Date kontakt_ts) {
        this.kontakt_ts = kontakt_ts;
    }


    private String formatDate(Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate = dateFormat.format(kontakt_ts);
        return formattedDate;
    }

    @Override
    public String toString() {
        return "\n"+kontakt_id + ","+ klient_id + "," + pracownik_id + ","+status + "," +formatDate(kontakt_ts);
    }
}
