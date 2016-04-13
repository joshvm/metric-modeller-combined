package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

public class SourceLinesOfCodeCalculator implements Calculator {

    @Override
    public OutputParam outputParam(){
        return OutputParam.SOURCE_LINES_OF_CODE;
    }

    @Override
    public double calculate(final Project project){
        return langs(project) * Calculators.adjustedFunctionPointCalculator().calculate(project);
    }

    public double langs(final Project project){
        return project.languageUsages().values().isEmpty() ? 1 :
                project.languageUsages().values().stream()
                        .mapToDouble(e -> e.language().get().locPerFp() * e.usage().get().weight())
                        .sum()
                        /
                        project.languageUsages().values().stream()
                                .mapToDouble(e -> e.usage().get().weight())
                                .sum();
    }
}
