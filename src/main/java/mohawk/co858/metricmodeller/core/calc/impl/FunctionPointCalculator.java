package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

public class FunctionPointCalculator implements Calculator {

    @Override
    public OutputParam outputParam(){
        return OutputParam.FUNCTION_POINTS;
    }

    @Override
    public double calculate(final Project project){
        final double ufp = ufp(project);
        System.out.println("UFP: " + ufp);
        final double tdi = tdi(project);
        System.out.println("TDI: " + tdi);
        final double vaf = vaf(project,tdi);
        System.out.println("VAF: " + vaf);
        final double efp = ufp * vaf;
        System.out.println("EFP: " + efp);
        final double fp = efp;
        System.out.println("FP: " + fp);
        return fp;
    }

    public double ufp(final Project project){
        return project.metricMeasurements().values().stream()
                .mapToDouble(mm -> mm.metric().weightingLevel(mm.weighting().get()) * mm.count().get())
                .sum();
    }

    public double tdi(final Project project){
        return project.factorRatings().values().stream()
                .mapToDouble(fr -> fr.rating().get().value())
                .sum();
    }

    public double vaf(final Project project, final double tdi){
        return ((tdi * 0.01) + 0.65) * project.dbComplexity().dbComplexityFactor().getValue().value();
    }

}
