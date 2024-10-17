package habit;

public enum Frequency {
    DAILY("DAILY"),
    WEEKLY("WEEKLY");

    private String title;

    Frequency(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public int getDaysInterval(){
        if (title.equals("DAILY")) return 1;
        if (title.equals("WEEKLY")) return 7;
        return 0;
    }
}
