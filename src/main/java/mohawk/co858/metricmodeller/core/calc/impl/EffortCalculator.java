package mohawk.co858.metricmodeller.core.calc.impl;

import java.util.Collection;
import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.db.Database;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.project.history.ProjectHistory;

public class EffortCalculator implements Calculator {

    @Override
    public OutputParam outputParam(){
        return OutputParam.EFFORT;
    }

    @Override
    public double calculate(final Project project){
        final Collection<ProjectHistory> history = Database.projectHistory().all();
        final double avg = history.stream()
                .mapToDouble(h -> h.actualEffort() / h.sourceLinesOfCode())
                .average()
                .orElse(1);

        final double effort = avg * Calculators.sourceLinesOfCode().calculate(project);
        return effort / 22D; // Effort in person months
    }
}
