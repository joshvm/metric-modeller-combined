package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

public class ProjectDurationCalculator implements Calculator {

    @Override
    public OutputParam outputParam(){
        return OutputParam.PROJECT_DURATION;
    }

    @Override
    public double calculate(final Project project){
        final double effort = Calculators.adjustedEffortCalculator().calculate(project);
        final double people = project.expertiseCounts().peopleCount() > 0 ?
                project.expertiseCounts().peopleCount() : 1;
        return effort / people; // total project length in months
    }
}
