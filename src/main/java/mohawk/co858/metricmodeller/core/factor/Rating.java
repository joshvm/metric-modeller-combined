package mohawk.co858.metricmodeller.core.factor;

public enum Rating {

    NO_INFLUENCE(0, "No Influence"),
    INCIDENTAL(1, "Incidental"),
    MODERATE(2, "Moderate"),
    AVERAGE(3, "Average"),
    SIGNIFICANT(4, "Significant"),
    ESSENTIAL(5, "Essential");

    public static final Rating[] VALUES = values();

    private final int value;
    private final String title;

    Rating(final int value, final String title){
        this.value = value;
        this.title = title;
    }

    public int value(){
        return value;
    }

    public String title(){
        return title;
    }

    @Override
    public String toString(){
        return title;
    }
}
