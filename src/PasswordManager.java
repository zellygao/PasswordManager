import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PasswordManager {

    //make 1d array of entry objects
    private ArrayList<Entry> entries ;
    private String filename;

    public PasswordManager(String filename) throws FileNotFoundException {
        this.filename = filename;
        setup(filename);
    }

    public void setup(String filename) throws FileNotFoundException { //all of the data from the text file is copied over into the 1d array
        entries = new ArrayList<>();
        Scanner scanner = new Scanner (new File (filename));
        while(scanner.hasNextLine()){
            String [] array = scanner.nextLine().split(",");
            Entry entry = new Entry (array[0],array[1],array[2],array[3],getTime());
            entries.add(entry);
        }
    }

    public void addEntry(String website, String username, String password, String url, String time) {
        Entry existingEntry = getEntry (website);
       // if(existingEntry.getUsername()!=null&&existingEntry.getUsername() != null){
       //     throw new IllegalArgumentException("Duplicate username entry: " + username + "for "+website);
       // }
        entries.add(new Entry(website, username, password, url,getTime()));
        try {
            save();
        } catch (Exception e) {

        }
    }

    public void editPassword(Entry e, String password){
        e.setPassword(password);
        e.setTime(getTime());

        try {
            save();
        } catch (Exception f) {

        }

    }
    public void editUsername(Entry e, String username){

        e.setUsername(username);
        e.setTime(getTime());

        try {
            save();
        } catch (Exception f) {

        }
    }
    public void editWebsite(Entry e,String website){
        e.setWebsite(website);
        e.setTime(getTime());

        try {
            save();
        } catch (Exception f) {

        }
    }

    public void editUrl(Entry e,String url){
e.setUrl(url);
        e.setTime(getTime());

        try {
            save();
        } catch (Exception f) {

        }
    }
    public Entry getEntry(String website) {
        for (Entry e : entries) {
            if (e.getWebsite().equalsIgnoreCase(website)) { //so that it is not case sensitive
                return e;
            }
        }
        return null;
    }
    public void save() throws FileNotFoundException {
        PrintStream file = new PrintStream ("Data.txt");
        //entries.println("Data.txt");
        for (Entry e : entries) {
            file.println(e.toString());
        }
    }


   public static String getTime() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            return (dtf.format(now));
   }
    public void deleteEntry(String website){
        Entry entry = getEntry(website);
        if(entry!=null){
            entries.remove(entry);
            try {
                save();
            } catch (Exception d) {

            }
        }


    }


}
