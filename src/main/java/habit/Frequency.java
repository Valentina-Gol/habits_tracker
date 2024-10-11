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
}
