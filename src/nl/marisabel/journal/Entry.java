package nl.marisabel.journal;

public class Entry {


    private String newTitle;
    private String newDate;
    private String newEntry;
    private int entryID;

    public Entry(String newTitle, String newDate, String newEntry) {
        this.newTitle = newTitle;
        this.newDate = newDate;
        this.newEntry = newEntry;
    }

    public String getNewTitle() {
        return newTitle;
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

    public static Entry createEntry(String newTitle, String newDate, String newEntry) {
        return new Entry(newTitle, newDate, newEntry);
    }
}
