package nl.marisabel.journal;

public class Entry {


    private String newDate;
    private String newEntry;
    private int entryID;

    public Entry( String newDate, String newEntry) {
        this.newDate = newDate;
        this.newEntry = newEntry;
    }

    public String getNewDate() {
        return newDate;
    }

    public String getNewEntry() {
        return newEntry;
    }

    public int getEntryID() {
        return entryID;
    }

    public static Entry createEntry(String newDate, String newEntry) {
        return new Entry(newDate, newEntry);
    }
}
