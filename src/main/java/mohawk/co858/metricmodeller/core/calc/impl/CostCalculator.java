package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

public class CostCalculator implements Calculator {

    @Override
    public OutputParam outputParam(){
        return OutputParam.COST;
    }

    @Override
    public double calculate(final Project project){
        final double avgCost = project.expertiseCounts().values().stream()
                .mapToDouble(e -> e.cost().get() * e.count().get() * 8)
                .sum() / project.expertiseCounts().peopleCount() > 0 ? project.expertiseCounts().peopleCount() : 1;
        final double time = Calculators.time().calculate(project);
        return time * avgCost;
    }
}
