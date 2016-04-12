package mohawk.co858.metricmodeller.core.calc;

import java.text.NumberFormat;

public enum OutputParam {

    FUNCTION_POINTS("Function Points", NumberFormat.getIntegerInstance(), "%s"),
    EFFORT("Effort", NumberFormat.getInstance(), "%s Person Months"),
    COST("Cost", NumberFormat.getCurrencyInstance(), "%s"),
    SOURCE_LINES_OF_CODE("Source Lines Of Code", NumberFormat.getIntegerInstance(), "%s Lines"),
    TIME("Duration", NumberFormat.getInstance(), "%s Months");

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
