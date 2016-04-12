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
        final double expertiseFactor = project.expertiseCounts().peopleCount() > 0 ?
                project.expertiseCounts().values().stream()
                        .mapToDouble(e -> e.count().get() * e.level().get().factor())
                        .sum() / project.expertiseCounts().peopleCount()
                : 1;

        final int people = project.expertiseCounts().values().stream()
                .mapToInt(e -> e.count().get())
                .sum();

        // Team factor adjusts effort if my section is enabled.
        double teamFactor = 1;
        if(project.team().enabled().getValue()){
            final double linesOfCommunication = people * (people - 1) / 2;
            final double functionPoints = Calculators.functionPoints().calculate(project);
            teamFactor += ((linesOfCommunication * functionPoints * (.0005 / project.team().coordination().get().value())) / project.team().leadership().get().value());
        }

        final double effort = avg * Calculators.sourceLinesOfCode().calculate(project);
        return (effort * expertiseFactor * teamFactor) / 22D; // Effort in person months
    }
}
