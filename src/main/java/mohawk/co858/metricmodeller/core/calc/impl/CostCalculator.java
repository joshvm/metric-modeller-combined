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
        final double peopleCount = project.expertiseCounts().peopleCount() > 0 ? project.expertiseCounts().peopleCount() : 1;
        final double totalCostPerMonth = project.expertiseCounts().values().stream()
                .mapToDouble(e -> e.cost().get() * e.count().get() * 8D * 22D) // 8 hours per day, 22 working days per month
                .sum();

        final double averageCostPerPerson = totalCostPerMonth / peopleCount;        // average $/month for resources
        final double effort = Calculators.effort().calculate(project);               // effort in person months
        return effort * averageCostPerPerson;                                        // total project cost
    }
}
