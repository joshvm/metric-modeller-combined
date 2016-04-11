package mohawk.co858.metricmodeller.core.calc;

import java.util.HashMap;
import java.util.Map;
import mohawk.co858.metricmodeller.core.calc.impl.CostCalculator;
import mohawk.co858.metricmodeller.core.calc.impl.EffortCalculator;
import mohawk.co858.metricmodeller.core.calc.impl.FunctionPointCalculator;
import mohawk.co858.metricmodeller.core.calc.impl.SourceLinesOfCodeCalculator;
import mohawk.co858.metricmodeller.core.calc.impl.TimeCalculator;

public final class Calculators {

    private static final Map<OutputParam, Calculator> MAP = new HashMap<>();

    static {
        add(new FunctionPointCalculator());
        add(new CostCalculator());
        add(new EffortCalculator());
        add(new SourceLinesOfCodeCalculator());
        add(new TimeCalculator());
    }

    private Calculators(){}

    public static void add(final Calculator calc){
        MAP.put(calc.outputParam(), calc);
    }

    public static <C extends Calculator> C forOutput(final OutputParam output){
        return (C) MAP.get(output);
    }

    public static FunctionPointCalculator functionPoints(){
        return forOutput(OutputParam.FUNCTION_POINTS);
    }

    public static CostCalculator cost(){
        return forOutput(OutputParam.COST);
    }

    public static EffortCalculator effort(){
        return forOutput(OutputParam.EFFORT);
    }

    public static SourceLinesOfCodeCalculator sourceLinesOfCode(){
        return forOutput(OutputParam.SOURCE_LINES_OF_CODE);
    }

    public static TimeCalculator time(){
        return forOutput(OutputParam.TIME);
    }
}
