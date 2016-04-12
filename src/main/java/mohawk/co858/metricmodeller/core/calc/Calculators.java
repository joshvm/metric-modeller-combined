package mohawk.co858.metricmodeller.core.calc;

import java.util.HashMap;
import java.util.Map;

import mohawk.co858.metricmodeller.core.calc.impl.*;

public final class Calculators {

    private static final Map<OutputParam, Calculator> MAP = new HashMap<>();

    static{
        add(new AdjustedFunctionPointCalculator());
        add(new AdjustedEffortCalculator());
        add(new CostCalculator());
        add(new DbComplexityFactorCalculator());
        add(new EffortCalculator());
        add(new ExpertiseFactorCalculator());
        add(new FunctionPointCalculator());
        add(new SourceLinesOfCodeCalculator());
        add(new TeamFactorCalculator());
        add(new ProjectDurationCalculator());
    }

    private Calculators(){
    }

    public static void add(final Calculator calc){
        MAP.put(calc.outputParam(), calc);
    }

    public static <C extends Calculator> C forOutput(final OutputParam output){
        return (C) MAP.get(output);
    }

    public static AdjustedFunctionPointCalculator adjustedFunctionPointCalculator() {return forOutput(OutputParam.ADJUSTED_FUNCTION_POINTS);}
    public static AdjustedEffortCalculator adjustedEffortCalculator() { return forOutput(OutputParam.ADJUSTED_EFFORT);}
    public static CostCalculator cost(){
        return forOutput(OutputParam.COST);
    }
    public static DbComplexityFactorCalculator dbcf() {return forOutput(OutputParam.DB_FACTOR);}
    public static EffortCalculator effort(){ return forOutput(OutputParam.EFFORT); }
    public static ExpertiseFactorCalculator expertiseFactorCalculator() {return forOutput(OutputParam.EXPERTISE_FACTOR);}
    public static FunctionPointCalculator functionPoints(){
        return forOutput(OutputParam.FUNCTION_POINTS);
    }
    public static SourceLinesOfCodeCalculator sourceLinesOfCode(){return forOutput(OutputParam.SOURCE_LINES_OF_CODE);}
    public static TeamFactorCalculator teamFactorCalculator() {return forOutput(OutputParam.TEAM_FACTOR);}
    public static ProjectDurationCalculator time(){
        return forOutput(OutputParam.PROJECT_DURATION);
    }



}
