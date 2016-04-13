package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

/**
 * Created by Daniel on 4/12/2016.
 */
public class AdjustedFunctionPointCalculator implements Calculator {
    @Override
    public OutputParam outputParam(){
        return OutputParam.ADJUSTED_FUNCTION_POINTS;
    }

    @Override
    public double calculate(Project project) {
        return  Calculators.functionPoints().calculate(project) *
                Calculators.dbcf().calculate(project) + project.metricMeasurements().rawFunctionPoints().getValue();
    }
}
