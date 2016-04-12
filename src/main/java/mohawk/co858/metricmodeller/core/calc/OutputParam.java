package mohawk.co858.metricmodeller.core.calc;

import java.text.NumberFormat;

public enum OutputParam {


    FUNCTION_POINTS("Function Points", NumberFormat.getIntegerInstance(), "%s"),
    DB_FACTOR("Database Complexity Factor", NumberFormat.getInstance(), "x %s"),
    ADJUSTED_FUNCTION_POINTS("Adjusted Function Points", NumberFormat.getIntegerInstance(), "%s"),

    SOURCE_LINES_OF_CODE("Source Lines Of Code", NumberFormat.getIntegerInstance(), "%s Lines"),

    EFFORT("Effort", NumberFormat.getInstance(), "%s Person Months"),
    EXPERTISE_FACTOR("Expertise Factor", NumberFormat.getInstance(), "x %s"),
    TEAM_FACTOR("Team Factor", NumberFormat.getInstance(), "x %s"),
    ADJUSTED_EFFORT("Adjusted Effort", NumberFormat.getInstance(), "%s Person Months"),

    PROJECT_DURATION("Project Duration", NumberFormat.getInstance(), "%s Months"),
    COST("Cost", NumberFormat.getCurrencyInstance(), "%s");




    public static final OutputParam[] VALUES = values();

    private final String title;
    private final NumberFormat numberFormat;
    private final String format;

    OutputParam(final String title, final NumberFormat numberFormat, final String format){
        this.title = title;
        this.numberFormat = numberFormat;
        this.format = format;
    }

    public String title(){
        return title;
    }

    public NumberFormat numberFormat(){
        return numberFormat;
    }

    public String format(){
        return format;
    }
}
