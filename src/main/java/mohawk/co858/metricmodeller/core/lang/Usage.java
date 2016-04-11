package mohawk.co858.metricmodeller.core.lang;

public enum Usage {

    LOW("Low", .5),
    MEDIUM("Medium", 1),
    HIGH("High", 1.5);

    public static final Usage[] VALUES = values();

    private final String title;
    private final double weight;

    Usage(final String title, final double weight){
        this.title = title;
        this.weight = weight;
    }

    public String title(){
        return title;
    }

    public double weight(){
        return weight;
    }

    @Override
    public String toString(){
        return title;
    }
}
