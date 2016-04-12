package mohawk.co858.metricmodeller.core.calc.impl;

import mohawk.co858.metricmodeller.core.calc.Calculator;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.db.Database;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.project.history.ProjectHistory;

import java.util.Collection;

/**
 * Created by Daniel on 4/12/2016.
 */
public class ExpertiseFactorCalculator implements Calculator{

        @Override
        public OutputParam outputParam(){
            return OutputParam.EXPERTISE_FACTOR;
        }

        @Override
        public double calculate(final Project project){

            final double expertiseFactor = project.expertiseCounts().peopleCount() > 0 ?
                    project.expertiseCounts().values().stream()
                            .mapToDouble(e -> e.count().get() * e.level().get().factor())
                            .sum() / project.expertiseCounts().peopleCount()                    : 1;

            return expertiseFactor;
        }
    }


