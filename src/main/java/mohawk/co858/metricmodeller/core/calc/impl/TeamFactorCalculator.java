package mohawk.co858.metricmodeller.core.calc.impl;
import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

/**
 * Created by Daniel on 4/12/2016.
 */
public class TeamFactorCalculator implements Calculator {

    @Override
    public OutputParam outputParam(){
        return OutputParam.TEAM_FACTOR;
    }

    @Override
    public double calculate(final Project project){
        // Team factor adjusts effort if my section is enabled.
        double teamFactor = 1;
        if(project.team().enabled().getValue()){
            final int people = project.expertiseCounts().values().stream()
                    .mapToInt(e -> e.count().get())
                    .sum();
            final double linesOfCommunication = people * (people - 1) / 2;
            teamFactor += linesOfCommunication * (project.team().FIDDLE_FACTOR / (project.team().coordination().get().value() * project.team().leadership().get().value()));
        }

        return teamFactor;
    }
}

