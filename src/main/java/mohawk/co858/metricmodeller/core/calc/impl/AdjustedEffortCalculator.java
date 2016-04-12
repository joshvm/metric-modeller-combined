package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

/**
 * Created by Daniel on 4/12/2016.
 */
public class AdjustedEffortCalculator implements Calculator {

    @Override
    public OutputParam outputParam() {
        return OutputParam.ADJUSTED_EFFORT;
    }

    @Override
    public double calculate(final Project project) {
        return Calculators.effort().calculate(project) *
                Calculators.teamFactorCalculator().calculate(project) *
                Calculators.expertiseFactorCalculator().calculate(project);
    }
}

